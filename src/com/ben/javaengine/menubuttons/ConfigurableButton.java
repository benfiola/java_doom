package com.ben.javaengine.menubuttons;

import org.apache.log4j.Logger;

import com.ben.javaengine.configuration.ConfigurationProperty;

public class ConfigurableButton extends AbstractButton {
	private static final Logger LOG = Logger.getLogger(ConfigurableButton.class);
	private ConfigurationProperty property;
	private String newValueString;
	private Object newValue;
	
	
	public ConfigurableButton(ConfigurationProperty property) {
		super();
		this.property = property;
		this.newValueString = property.getValue().toString();
		this.newValue = null;
	}
	
	public ConfigurationProperty getProperty() {
		return property;
	}
	
	public String getNewValueString() {
		return newValueString;
	}
	
	public void setNewValueString(String value) {
		this.newValueString = value;
	}
	
	public Object getNewValue() {
		return newValue;
	}
	
	public void setValue() {
		Object valueObj = getProperty().getDefaultValue();
		try {
			if(property.getParserHint() == Integer.class) {
				valueObj = Integer.parseInt(newValueString);
			} else if(property.getParserHint() == Double.class) {
				valueObj = Double.parseDouble(newValueString);
			}
		} catch(Exception e) {
			LOG.error("Unable to convert " + newValueString + " into an object of class " + property.getParserHint().getSimpleName());
			newValueString = valueObj.toString();
		}		
		newValue = valueObj;
	}
	
}
