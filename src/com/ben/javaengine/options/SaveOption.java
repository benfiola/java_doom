package com.ben.javaengine.options;

import org.apache.log4j.Logger;

import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.configuration.ConfigurationProperty;
import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.MainMenuState;
import com.ben.javaengine.game.state.OptionsMenuState;

public class SaveOption extends NavigableFooterOption {
	private static final Logger LOG = Logger.getLogger(SaveOption.class);
	
	private OptionsMenuState state;
	public SaveOption(OptionsMenuState state) {
		super("Save");
		this.state = state;
	}

	@Override
	public AbstractState nextState() {
		Configuration config = new Configuration();
		for(AbstractOption opt : state.getOptions()) {
			if(opt instanceof ConfigurableOption) {
				ConfigurableOption configOpt = (ConfigurableOption) opt;
				ConfigurationProperty prop = configOpt.getProperty();
				try {
					if(configOpt.getNewValue() != null && !prop.getDefaultValue().equals(configOpt.getNewValue())) {
						ConfigurationProperty toChange = config.getProperty(prop.getKey());
						toChange.setValue(configOpt.getNewValue());
					}
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
