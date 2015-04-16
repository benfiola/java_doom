package com.ben.javaengine.configuration;

import org.apache.log4j.Logger;

public class ConfigurationProperty {
	private static final Logger LOG = Logger
			.getLogger(ConfigurationProperty.class);

	private String key;
	private String canonicalName;
	private String description;
	private Object value;
	private Object defaultValue;
	private Class<? extends Object> parserHint;

	public ConfigurationProperty(ConfigurationDescriptor desc) {
		this.key = desc.getKey();
		this.canonicalName = desc.getCanonicalName();
		this.description = desc.getDescription();
		this.defaultValue = desc.getDefaultValue();
		this.parserHint = desc.getParserHint();
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	public Object getValue() {
		Object toReturn = defaultValue;
		if (value != null) {
			toReturn = value;
		}
		return toReturn;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public Class<? extends Object> getParserHint() {
		return parserHint;
	}

	public Integer getValueAsInteger() {
		return (Integer) getValue();
	}

	public Double getValueAsDouble() {
		return (Double) getValue();
	}

	public void setValue(Object value) {
		if (value instanceof String) {
			String strValue = (String) value;
			if (parserHint == Integer.class) {
				this.value = Integer.parseInt(strValue);
			} else if (parserHint == Double.class) {
				this.value = Double.parseDouble(strValue);
			}
		} else if (value instanceof Integer && parserHint == Integer.class) {
			this.value = value;
		} else if (value instanceof Double && parserHint == Double.class) {
			this.value = value;
		} else {
			LOG.error("Unable to set value of ConfigurationProperty " + key
					+ " due to conflicting types.");
		}
	}
}
