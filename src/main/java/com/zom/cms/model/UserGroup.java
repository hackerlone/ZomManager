package com.zom.cms.model;

/**
 * 用户组对象，存储用户和组的关联
 * 
 * @author Administrator
 * 
 */
public class UserGroup {
	private Long id;
	private User user;
	private Group group;

	private Long groupId;
	private Long userId;
	private Long groupRight;
	private int zoneId=1;
	
	

	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public Long getGroupRight() {
		return groupRight;
	}
	public void setGroupRight(Long groupRight) {
		this.groupRight = groupRight;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
