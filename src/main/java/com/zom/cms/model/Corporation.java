package com.zom.cms.model;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Date;
public class Corporation {
	private Integer id;
	private String username;
	private String corpName;
	private String corpPassword;
	private String email;
	private String phone;

	private Date registerDate;
	private Date expireDate;
	private Date lastLogonDate;
	private String lastLogonIp;
	private int permissionLevel;
	private int priorityLevel;
	private int maxUser;
	private int maxGroup;
	private int maxConsole;
	private int maxUserGroup;
	private int status;
	private String zoneId;
	
	
	

	



	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Corporation() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getUsername() {
		return username;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpPassword() {
		return corpPassword;
	}
	public void setCorpPassword(String corpPassword) {
		this.corpPassword = corpPassword;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLastLogonIp() {
		return lastLogonIp;
	}
	public void setLastLogonIp(String lastLogonIp) {
		this.lastLogonIp = lastLogonIp;
	}
	public int getPermissionLevel() {
		return permissionLevel;
	}
	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	public int getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public int getMaxUser() {
		return maxUser;
	}
	public void setMaxUser(int maxUser) {
		this.maxUser = maxUser;
	}
	public int getMaxGroup() {
		return maxGroup;
	}
	public void setMaxGroup(int maxGroup) {
		this.maxGroup = maxGroup;
	}
	public int getMaxConsole() {
		return maxConsole;
	}
	public void setMaxConsole(int maxConsole) {
		this.maxConsole = maxConsole;
	}
	public int getMaxUserGroup() {
		return maxUserGroup;
	}
	public void setMaxUserGroup(int maxUserGroup) {
		this.maxUserGroup = maxUserGroup;
	}
	
	
}
