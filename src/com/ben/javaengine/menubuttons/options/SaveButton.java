package com.ben.javaengine.menubuttons.options;

import org.apache.log4j.Logger;

import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.configuration.ConfigurationProperty;
import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.MainMenuState;
import com.ben.javaengine.game.state.OptionsMenuState;
import com.ben.javaengine.menubuttons.AbstractButton;
import com.ben.javaengine.menubuttons.ConfigurableButton;
import com.ben.javaengine.menubuttons.NavigableFooterButton;

public class SaveButton extends NavigableFooterButton {
	private static final Logger LOG = Logger.getLogger(SaveButton.class);
	
	private OptionsMenuState state;
	public SaveButton(OptionsMenuState state) {
		super("Save");
		this.state = state;
	}

	@Override
	public AbstractState getNextState() {
		Configuration config = new Configuration();
		for(AbstractButton opt : state.getOptions()) {
			if(opt instanceof ConfigurableButton) {
				ConfigurableButton configOpt = (ConfigurableButton) opt;
				ConfigurationProperty prop = configOpt.getProperty();
				try {
					ConfigurationProperty toChange = config.getProperty(prop.getKey());
					toChange.setValue(configOpt.getNewValue());
				} catch(Exception e) {
					LOG.error("Error parsing new value.", e);
					return new OptionsMenuState();
				}
			}
		}
		config.writeToFile();
		return new MainMenuState();
	}
}
