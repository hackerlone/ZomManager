package com.zom.cms.model;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层com.zom.cms.model/ConsolerUser.java <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明  4951048@qq.com<p>
 * <strong> 编写时间：</strong>2016年8月18日 下午4:19:44<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
public class ConsolerUser {
	private int id;
	private String consoleDisplayName;
	private Long consoleId;
	private String userName;
	private String userPwd;
	public ConsolerUser(int id, String consoleDisplayName, Long consoleId, String userName, String userPwd) {
		super();
		this.id = id;
		this.consoleDisplayName = consoleDisplayName;
		this.consoleId = consoleId;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public ConsolerUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConsoleDisplayName() {
		return consoleDisplayName;
	}
	public void setConsoleDisplayName(String consoleDisplayName) {
		this.consoleDisplayName = consoleDisplayName;
	}
	public Long getConsoleId() {
		return consoleId;
	}
	public void setConsoleId(Long consoleId) {
		this.consoleId = consoleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
}
