package com.desksoft.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	
	private static Properties configurations;
	
	{
		configurations = new Properties();
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		
		configurations.putAll(props);
	}
	
	/**
	 * Returns the system properties
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return configurations;
	}
	
	/**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>null</code> if the property is not found.
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static String getProperty(String key) {
    	return configurations.getProperty(key);
    }
	
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns the
     * default value argument if the property is not found.
     *
     * @param   key            the hashtable key.
     * @param   defaultValue   a default value.
     *
     * @return  the value in this property list with the specified key value.
     */
    public static String getProperty(String key, String defaultValue) {
    	return configurations.getProperty(key, defaultValue);
    }
    
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public static Object get(Object key) {
    	return configurations.get(key);
    }
    
}
