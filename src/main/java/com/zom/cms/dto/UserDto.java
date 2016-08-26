package com.zom.cms.dto;


import java.util.List;

import com.zom.cms.model.User;

public class UserDto {
	private Long id;
	/**
	 * 用户登录名称
	 */
	private String username;
	/**
	 * 用户登录密码
	 */
	private String password;
	/**
	 * 用户的中文名称
	 */
	private String nickname;
	/**
	 * 用户的邮件
	 */
	/**
	 * 用户的中文名称
	 */
	private Long admin_id;
	/**
	 * 用户的联系电话
	 */
	private String phone;
	/**
	 * 用户的状态：0表示停用，1表示启用
	 */
	private int status;
	/**
	 * 角色id
	 */
	private Integer[] roleIds;
	/**
	 * 用户等级
	 */
	private int rank;
	private int isJoin;
	/**
	 * 组id
	 */
	

	
	
	private Long[] groupIds;
	
	public int getIsJoin() {
		return isJoin;
	}


	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}
	/**
	 * 用户优先级
	 */
	private int userPriority;
	
	/**
	 * 用户可选的consoles name
	 */
	private List<String> adminNames;
	


	/**
	 * 用户的默认管理台名字
	 */
	private String adminName;
	
	private int priority;
	
	private List<Long> userIds;
	private List<Long> allUserIds;
	private List<String>groupNames;
	private List<Long> consoleIds;
	private List<Long> secAdminIds;
	
	
	public List<Long> getSecAdminIds() {
		return secAdminIds;
	}


	public void setSecAdminIds(List<Long> secAdminIds) {
		this.secAdminIds = secAdminIds;
	}
	/**
	 * Corp ID
	 */
	private int corpId;
	

	public List<Long> getConsoleIds() {
		return consoleIds;
	}


	public void setConsoleIds(List<Long> consoleIds) {
		this.consoleIds = consoleIds;
	}


	public List<String> getGroupNames() {
		return groupNames;
	}


	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}


	public List<Long> getUserIds() {
		return userIds;
	}


	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}


	public List<Long> getAllUserIds() {
		return allUserIds;
	}


	public void setAllUserIds(List<Long> allUserIds) {
		this.allUserIds = allUserIds;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	
	public int getCorpId() {
		return corpId;
	}


	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}








	public List<String> getAdminNames() {
		return adminNames;
	}


	public void setAdminNames(List<String> adminNames) {
		this.adminNames = adminNames;
	}


	public int getUserPriority() {
		return userPriority;
	}


	public void setUserPriority(int userPriority) {
		this.userPriority = userPriority;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public Long getId() {
		return id;
	}


	public Long getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Integer[] getRoleIds() {
		return roleIds;
	}


	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}


	public Long[] getGroupIds() {
		return groupIds;
	}


	public void setGroupIds(Long[] groupIds) {
		this.groupIds = groupIds;
	}


	public User getUser() {
		User u = new User();
		u.setId(this.id);
//	 	u.setRank(this.getRank());
		u.setId(this.getId());
		u.setDisplayName(this.nickname);
		u.setPriority(this.userPriority);
		u.setStatus(this.status);
	//	this.setPassword(user.getPassword());
		u.setPhone(this.getPhone());
		u.setAdminId(this.admin_id);
		u.setUserPassword(this.password);
		u.setCorpId(this.corpId);
		u.setLogonName(username);
		u.setIsJoin(this.isJoin);
		return u;
	}
	
	/**
	 * 通过用户对象构造UserDto
	 * @param user
	 */
	public UserDto(User user) {
//	 	this.setRank(user.getRank());
		this.setId(user.getId());
		this.setNickname(user.getDisplayName());
	//	this.setPassword(user.getPassword());
		this.setPhone(user.getPhone());
		this.setAdmin_id(user.getAdminId());
		this.setPassword(user.getUserPassword());
		this.setUserPriority(user.getPriority());
		this.setCorpId(user.getCorpId());
//		this.setRank(user.getRank());
		this.setStatus(user.getStatus());
		this.setUsername(user.getLogonName());
		this.setIsJoin(user.getIsJoin());
		
	}
	
	/**
	 * 通过用户对象、角色id集合、群组id集合构造UserDto对象
	 * @param user
	 * @param roleIds
	 * @param groupIds
	 */
	public UserDto(User user,Long[] groupIds) {
//		this.setRank(user.getRank());
		this.setId(user.getId());
		this.setNickname(user.getDisplayName());
		this.setPassword(user.getUserPassword());
		this.setPhone(user.getPhone());
		this.setAdmin_id(user.getAdminId());
		this.setStatus(user.getStatus());
		this.setUsername(user.getLogonName());
		this.setGroupIds(groupIds);
		this.setCorpId(user.getCorpId());
		this.setUserPriority(user.getPriority());
	}
	public UserDto(User user,List<Long> groupIds) {
		this.setRank(user.getRank());
		this.setId(user.getId());
		this.setNickname(user.getDisplayName());
		this.setPassword(user.getUserPassword());
		this.setPhone(user.getPhone());
		this.setAdmin_id(user.getAdminId());
		this.setCorpId(user.getCorpId());
		this.setStatus(user.getStatus());
		this.setUsername(user.getLogonName());
		this.setGroupIds(list2Array(groupIds));
	//	this.setRoleIds(list2Array(roleIds));
		this.setUserPriority(user.getPriority());
	}
	public UserDto(User user,List<Long> groupIds,List<String> adminNames,String adminName) {
//		this.setRank(user.getRank());
		this.setId(user.getId());
		this.setNickname(user.getDisplayName());
		this.setPassword(user.getUserPassword());
		this.setPhone(user.getPhone());
		this.setAdmin_id(user.getAdminId());
		this.setCorpId(user.getCorpId());
		this.setStatus(user.getStatus());
		this.setUsername(user.getLogonName());
		this.setGroupIds(list2Array(groupIds));
	//	this.setRoleIds(list2Array(roleIds));
		this.setUserPriority(user.getPriority());
		this.setAdminName(adminName);
		this.setAdminNames(adminNames);
	}
	private Long[] list2Array(List<Long> datas) {
		Long[] nums = new Long[datas.size()];
		for(int i=0;i<datas.size();i++) {
			nums[i] = datas.get((int)i);
		}
		return nums;
	}
	public UserDto() {
		admin_id=0l;
	}
}
