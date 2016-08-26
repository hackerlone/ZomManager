package com.zom.cms.im.easemob.comm;

/**
 * Constants
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {
	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = "https";
	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getProperty("API_SERVER_HOST");
	// API_SERVER_PORT
	//public static String API_SERVER_PORT = PropertiesUtils.getProperty("API_SERVER_PORT");
	// APPKEY
	public static String APPKEY = PropertiesUtils.getProperty("APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getProperty("APP_CLIENT_SECRET");
	// APP_ADMIN_USERNAME
	public static String ORG_ADMIN_USERNAME = PropertiesUtils.getProperty("ORG_ADMIN_USERNAME");
	// APP_ADMIN_PASSWORD
	public static String ORG_ADMIN_PASSWORD = PropertiesUtils.getProperty("ORG_ADMIN_PASSWORD");
	//Eric TODO: remove the following constants:
//	public static String APP_ADMIN_TOKEN = PropertiesUtils.getProperty("APP_ADMIN_TOKEN");
//	public static String APP_ADMIN_TOKEN_EXPIREAT = PropertiesUtils.getProperty("APP_ADMIN_TOKEN_EXPIREAT");
// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "1234456";
	public static String SERVER_VERSION = PropertiesUtils.getProperty("SERVER_VERSION");
	public static String SUPPORT_CVER = PropertiesUtils.getProperty("SUPPORT_CVER");
	public static String INTEGRATION_TEST = PropertiesUtils.getProperty("INTEGRATION_TEST");
	public static String EASEMOB_REGISTER = PropertiesUtils.getProperty("EASEMOB_REGISTER");
	public static String SD_SEND = PropertiesUtils.getProperty("SD_SEND");
	public static String SMS_SEND = PropertiesUtils.getProperty("SMS_SEND");
	public static String HOME_ID = PropertiesUtils.getProperty("HOME_ID");
//	public static String SUPPORTED_VERSION = PropertiesUtils.getProperty("SUPPORTED_VERSION");
//	public static String LATEST_VERSION = PropertiesUtils.getProperty("LATEST_VERSION");

}
