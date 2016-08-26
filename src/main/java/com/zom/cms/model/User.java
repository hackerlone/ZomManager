package com.zom.cms.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.jeecgframework.poi.excel.annotation.Excel;

public class User {
	private Long id;
	/**
	 * 用户登录名称
	 */

	private String displayName;
	/**
	 * 用户登录密码
	 */

	private String userPassword;
	/**
	 * 用户的中文名称
	 */

	private String clientVersion;
	/**
	 * 用户的设备
	 */
	private String device;
	/**
	 * 用户的联系电话
	 */

	private String phone;
	/**
	 ** 用户环信ID
	 */
     private String pushId;
     
	/**
	 * 用户环信密码
	 */
     private String pushPassword;

	/**
	 * 用户头像地址
	 */
	private String imgUrl;
	

	/**
	 * 用户注册时间
	 */
	private Date registerDate;
	

	/**
	 * 用户上次登录时间
	 */
	private Date lastLogonDate;
	
	/**
	 * 用户上次访问时间
	 */
	private Date lastAccessDate;
	/**
	 * 用户登录IP
	 */
	private String lastLogonIp;
	
	/**
	 * 用户登录状态
	 */

	private String logonState;
	
	/**
	 * 
	 */
	private String salt;
	

	
	
	/**
	 * 用户profile timestamp
	 */
	private Long tsProfile;
	
	/**
	 * group timestamp
	 */
	private Long tsGroup;
	
	/**
	 * 用户级别
	 */

	private int rank;
	
	/**
	 * 用户默认的admin console id
	 */
	private Long adminId;	
	
	/**
	 * 用户默认的group ID，被用来打individual call
	 */
	private Long dcgId;	
	
	/**
	 * 用户默认的corp id
	 */
	private int corpId;
	
	private int priority;
	
	/*
	 * 用户登录名，为console用户新增加的域，
	 * 对于普通BMS用户，系统会为他自动生成一个名字
	 * u+corporation id+ uid
	 */
	private String logonName;
	
	private int status;
	
	private int preconfig;
	private Long defaultGrp;
	private Long admTs;
	
	private int zoneId=1;
	private int isJoin;
	
	private String managerUser;
	private String managerUserPwd;
 


	
	public String getManagerUserPwd() {
		return managerUserPwd;
	}

	public void setManagerUserPwd(String managerUserPwd) {
		this.managerUserPwd = managerUserPwd;
	}

	public String getManagerUser() {
		return managerUser;
	}

	public void setManagerUser(String managerUser) {
		this.managerUser = managerUser;
	}

	public int getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public Long getAdmTs() {
		return admTs;
	}

	public void setAdmTs(Long admTs) {
		this.admTs = admTs;
	}

	public int getPreconfig() {
		return preconfig;
	}

	public void setPreconfig(int preconfig) {
		this.preconfig = preconfig;
	}

	public Long getDefaultGrp() {
		return defaultGrp;
	}

	public void setDefaultGrp(Long defaultGrp) {
		this.defaultGrp = defaultGrp;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotNull(message="用户名不能为空")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@NotNull(message="用户密码不能为空")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getPushPassword() {
		return pushPassword;
	}

	public void setPushPassword(String pushPassword) {
		this.pushPassword = pushPassword;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getLastLogonIp() {
		return lastLogonIp;
	}

	public void setLastLogonIp(String lastLogonIp) {
		this.lastLogonIp = lastLogonIp;
	}

	public String getLogonState() {
		return logonState;
	}

	public void setLogonState(String logonState) {
		this.logonState = logonState;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}



	public Long getTsProfile() {
		return tsProfile;
	}

	public void setTsProfile(Long tsProfile) {
		this.tsProfile = tsProfile;
	}

	public Long getTsGroup() {
		return tsGroup;
	}

	public void setTsGroup(Long tsGroup) {
		this.tsGroup = tsGroup;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getDcgId() {
		return dcgId;
	}

	public void setDcgId(Long dcgId) {
		this.dcgId = dcgId;
	}

	public int getCorpId() {
		return corpId;
	}

	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}

	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	
	
}
