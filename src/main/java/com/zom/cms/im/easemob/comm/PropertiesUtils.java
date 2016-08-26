package com.zom.cms.im.easemob.comm;

import java.util.HashMap;  
import java.util.Map;  
import java.util.Properties;  
import java.util.Map.Entry; 

import org.springframework.beans.BeansException;  
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;  
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;  
import org.springframework.util.PropertyPlaceholderHelper;  
  
public class PropertiesUtils extends PropertyPlaceholderConfigurer{  
  
//    private Map<String, Object> location; 
    private static Map<String,String> properties = new HashMap<String,String>();  
    protected void processProperties( ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {  
        // cache the properties  
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(  
                DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);  
        for(Entry<Object,Object> entry:props.entrySet()){  
            String stringKey = String.valueOf(entry.getKey());  
            String stringValue = String.valueOf(entry.getValue());  
            stringValue = helper.replacePlaceholders(stringValue, props);  
            properties.put(stringKey, stringValue);  
        }  
        super.processProperties(beanFactoryToProcess, props);  
    }  
      
//    public static Map<String, String> getProperties() {
//    	Map<String, String> prop = properties;
//        return prop;  
//    }  
      
    public static String getProperty(String key){  
        return properties.get(key);  
    }  
    
//	public void setLocation(Map<String, Object> location) {
//		this.location = location;
//	}  
}  
