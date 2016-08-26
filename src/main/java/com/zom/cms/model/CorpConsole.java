package com.zom.cms.model;

/**
 * 用户组对象，存储用户和组的关联
 * 
 * @author Administrator
 * 
 */
public class CorpConsole {
	private Long id;
	private Long console_id;
	private Long corp_id;

	private User user;
	private Corporation corp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getConsole_id() {
		return console_id;
	}
	public void setConsole_id(Long console_id) {
		this.console_id = console_id;
	}
	public Long getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(Long corp_id) {
		this.corp_id = corp_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Corporation getCorp() {
		return corp;
	}
	public void setCorp(Corporation corp) {
		this.corp = corp;
	}
	

	
}
