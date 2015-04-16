package com.ben.javaengine.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Configuration {
	private static final Logger LOG = Logger.getLogger(Configuration.class);
	private static final String configPath = "config.properties";
	private HashMap<String, ConfigurationProperty> properties;
	
	public Configuration() {
		init();
	}
	
	public void init() {
		properties = new HashMap<String, ConfigurationProperty>();
		initDefaults();
		overrideFromFile();
	}
	
	private void initDefaults() {
		for(ConfigurationDescriptor descriptor : ConfigurationDescriptor.values()) {
			properties.put(descriptor.getKey(), new ConfigurationProperty(descriptor));
		}
	}
	
	private void overrideFromFile() {
		File configFile = new File(configPath);
		if(configFile.exists() && configFile.canRead()) {
			BufferedReader br = null;
			try {
				String line = null;
				br = new BufferedReader(new FileReader(configFile));
				while((line = br.readLine()) != null) {
					Pattern configPattern = Pattern.compile("(?m)^([1-9a-z_.]+)=([0-9a-z).]+)$");
					Matcher configMatcher = configPattern.matcher(line);
					if(configMatcher.matches()) {
						String key = configMatcher.group(1);
						String value = configMatcher.group(2);
						if(properties.containsKey(key)) {
							ConfigurationProperty prop = properties.get(key);
							prop.setValue(value);
						} else {
							LOG.error("Unable to find property with key " + key);
						}
					}
				}
			} catch(Exception e) {
				LOG.error("Unable to open configuration file at " + configPath, e);
			} finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						LOG.error("Unable to close file");
					}
				}
			}
		}
	}
	
	public void writeToFile() {
		File newConfigFile = new File(configPath + ".tmp");
		File oldConfigFile = new File(configPath);
		FileWriter fw = null;
		try {
			newConfigFile.createNewFile();
			fw = new FileWriter(newConfigFile);
			for(ConfigurationProperty prop : getProperties()) {
				if(!prop.getDefaultValue().equals(prop.getValue())) {
					fw.write(prop.getKey()+"="+prop.getValue().toString()+"\n");
				}
			}
			fw.flush();
			oldConfigFile.delete();
			newConfigFile.renameTo(oldConfigFile);
		} catch(Exception e) {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e1) {
					LOG.error("Unable to close file");
				}
			}
		}
		
	}
	
	public ConfigurationProperty getProperty(ConfigurationDescriptor desc) {
		return getProperty(desc.getKey());
	}
	
	public ConfigurationProperty getProperty(String key) {
		return properties.get(key);
	}
	
	public List<ConfigurationProperty> getProperties() {
		return new ArrayList<ConfigurationProperty>(properties.values());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(ConfigurationProperty prop : properties.values()) {
			sb.append(prop.getKey() + " : " + prop.getValue() + "\n");
		}
		return sb.toString();
	}
}
