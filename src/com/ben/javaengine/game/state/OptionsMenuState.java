package com.ben.javaengine.game.state;

import java.awt.event.KeyEvent;

import com.ben.javaengine.Controller;
import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.configuration.ConfigurationProperty;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.event.options.EditOptionEvent;
import com.ben.javaengine.game.event.options.NextOptionEvent;
import com.ben.javaengine.game.event.options.PreviousOptionEvent;
import com.ben.javaengine.game.event.options.SelectOptionEvent;
import com.ben.javaengine.options.BackOption;
import com.ben.javaengine.options.ConfigurableOption;
import com.ben.javaengine.options.NavigableOption;
import com.ben.javaengine.options.SaveOption;

public class OptionsMenuState extends AbstractMenuState {
	private boolean isEditing;
	
	public OptionsMenuState() {
		super();
		isEditing = false;
	}
	
	public boolean isEditing() {
		return isEditing;
	}
	
	public void isEditing(boolean toSet) {
		this.isEditing = toSet;
	}
	
	@Override
	public void takeAction(AbstractEvent e) {
		if(e instanceof NextOptionEvent) {
			if(!isEditing()) {
				nextOption();
			}
		} else if(e instanceof PreviousOptionEvent) {
			if(!isEditing()) {
				previousOption();
			}
		} else if(e instanceof SelectOptionEvent) {
			if(options.get(selectedIndex) instanceof NavigableOption) {
				NavigableOption opt = (NavigableOption) options.get(selectedIndex);
				Controller.getInstance().updateState(opt.nextState());	
			} else if(isEditing()) {
				ConfigurableOption confOpt = (ConfigurableOption) options.get(selectedIndex);
 				confOpt.setValue();
			}
			isEditing(!isEditing);
		} else if(e instanceof EditOptionEvent) {
			if(isEditing()) {
				EditOptionEvent event = (EditOptionEvent) e;
				KeyEvent key = event.getEvent();
				ConfigurableOption option = (ConfigurableOption) options.get(selectedIndex);
				String currValue = option.getNewValueString();
				if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if(currValue.length() > 0) {
						option.setNewValueString(currValue.substring(0, currValue.length()-1));
					}
				} else {
					char charInput = key.getKeyChar();
					if(Character.isLetterOrDigit(charInput) || charInput == '.') {
						option.setNewValueString(currValue + charInput);
					}
				}
 			}
		}
	}

	@Override
	protected void populateOptions() {
		Configuration config = new Configuration();
		for(ConfigurationProperty prop : config.getProperties()) {
			options.add(new ConfigurableOption(prop));
		}
		options.add(new BackOption());
		options.add(new SaveOption(this));
	}

}
