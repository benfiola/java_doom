package com.ben.javaengine.game.state;

import java.awt.event.KeyEvent;

import com.ben.javaengine.Controller;
import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.configuration.ConfigurationProperty;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.event.menu.NextOptionEvent;
import com.ben.javaengine.game.event.menu.PreviousOptionEvent;
import com.ben.javaengine.game.event.menu.SelectOptionEvent;
import com.ben.javaengine.game.event.menu.options.EditOptionEvent;
import com.ben.javaengine.menubuttons.ConfigurableButton;
import com.ben.javaengine.menubuttons.NavigableButton;
import com.ben.javaengine.menubuttons.options.BackButton;
import com.ben.javaengine.menubuttons.options.SaveButton;

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
			if(options.get(selectedIndex) instanceof NavigableButton) {
				NavigableButton opt = (NavigableButton) options.get(selectedIndex);
				Controller.getInstance().updateState(opt.getNextState());	
			} else if(isEditing()) {
				ConfigurableButton confOpt = (ConfigurableButton) options.get(selectedIndex);
 				confOpt.setValue();
			}
			isEditing(!isEditing);
		} else if(e instanceof EditOptionEvent) {
			if(isEditing()) {
				EditOptionEvent event = (EditOptionEvent) e;
				KeyEvent key = event.getEvent();
				ConfigurableButton option = (ConfigurableButton) options.get(selectedIndex);
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
			options.add(new ConfigurableButton(prop));
		}
		options.add(new BackButton());
		options.add(new SaveButton(this));
	}

}
