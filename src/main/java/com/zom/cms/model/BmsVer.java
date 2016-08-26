package com.zom.cms.model;


/**
 * 角色对象，用来对应可以访问的功能，系统中为了简单值定义了管理员，发布人员和审核人员
 * @author Administrator
 *
 */
public class BmsVer {
	/**
	 * 角色id
	 */
	private int id;
	/**
	 * 角色的名称，中文
	 */
	private String serverVersion;
	/**
	 * 角色的编号，枚举类型
	 */
	private String clientOsType;
	
	private String clientVersion;
	
	private String clientUrl;
	
	private int latestServer;
	
	private int latestClient;
	
	private String clientDescription;
	  
	public BmsVer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getClientOsType() {
		return clientOsType;
	}

	public void setClientOsType(String clientOsType) {
		this.clientOsType = clientOsType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}



	public int getLatestServer() {
		return latestServer;
	}

	public void setLatestServer(int latestServer) {
		this.latestServer = latestServer;
	}

	public int getLatestClient() {
		return latestClient;
	}

	public void setLatestClient(int latestClient) {
		this.latestClient = latestClient;
	}

	public String getClientDescription() {
		return clientDescription;
	}

	public void setClientDescription(String clientDescription) {
		this.clientDescription = clientDescription;
	}


	
}
