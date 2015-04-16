package com.ben.javaengine.configuration;

public enum ConfigurationDescriptor {
	SCREEN_HEIGHT("screen.height", "Screen Height", "Specifies the height of the game screen.", 1080, Integer.class),
	SCREEN_WIDTH("screen.width", "Screen Width", "Specifies the width of the game screen.", 1920, Integer.class),
	CAMERA_FIELD_OF_VIEW("camera.field_of_view", "Field of View", "Specifies the field of view of the in game camera.", 90.0, Double.class);
	
	private String key;
	private String canonicalName;
	private String description;
	private Object defaultValue;
	private Class<? extends Object> parserHint;
	
	private ConfigurationDescriptor(String key, String canonicalName, String description, Object defaultValue, Class<? extends Object> parserHint) {
		this.key = key;
		this.canonicalName = canonicalName;
		this.description = description;
		this.defaultValue = defaultValue;
		this.parserHint = parserHint;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getCanonicalName() {
		return canonicalName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	public Class<? extends Object> getParserHint() {
		return parserHint;
	}
	
	public static ConfigurationDescriptor getConfigurationDescriptor(String key) {
		for(ConfigurationDescriptor desc : values()){
			if(desc.key.equals(key)) {
				return desc;
			}
		}
		return null;
	}
}
