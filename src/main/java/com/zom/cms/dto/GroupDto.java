package com.zom.cms.dto;

import java.util.Date;
import java.util.List;

import com.zom.cms.model.Group;
import com.zom.cms.model.User;

public class GroupDto {
	/**
	 * 组id
	 */
	private Long id;
	private String groupName;

	// private String adminName;
	private List<User> adminNames;
	private List<Long> userIds;
	private List<Long> allUserIds;
	private int corpId;
	private Long ownerId;
	private String ownerName;
	private int dcg; // 0 is no, 1 is yes
	private int rank;
	private int status;
	private Date createDate;
	private int zoneId = 1;
	private String phone;
	private int isJoin;
	
	private String groupUserName;
	private String groupUserPhone;

	
	
	
	
	public String getGroupUserName() {
		return groupUserName;
	}

	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
	}

	public String getGroupUserPhone() {
		return groupUserPhone;
	}

	public void setGroupUserPhone(String groupUserPhone) {
		this.groupUserPhone = groupUserPhone;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Long> getAllUserIds() {
		return allUserIds;
	}

	public void setAllUserIds(List<Long> allUserIds) {
		this.allUserIds = allUserIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDcg() {
		return dcg;
	}

	public void setDcg(int dcg) {
		this.dcg = dcg;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public int getCorpId() {
		return corpId;
	}

	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public List<User> getAdminNames() {
		return adminNames;
	}

	public void setAdminNames(List<User> adminNames) {
		this.adminNames = adminNames;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/*
	 * public String getAdminName() { return adminName; }
	 * 
	 * public void setAdminName(String adminName) { this.adminName = adminName;
	 * }
	 */
	public Group getUser() {
		Group u = new Group();
		u.setId(this.id);

		u.setGroupName(this.groupName);
		u.setCorpId(this.corpId);
		u.setOwnerId(this.ownerId);
		u.setRank(this.rank);
		u.setDcg(this.dcg);
		u.setStatus(this.status);
		u.setCreateDate(this.createDate);
		u.setZoneId(this.zoneId);

		return u;
	}

	/**
	 * 通过用户对象构造GroupDto
	 * 
	 * @param group
	 */
	public GroupDto(Group group) {
		this.setId(group.getId());
		this.setGroupName(group.getGroupName());
		this.setCorpId(group.getCorpId());
		this.setOwnerId(group.getOwnerId());
		this.setRank(group.getRank());
		this.setDcg(group.getDcg());
		this.setStatus(group.getStatus());
		this.setCreateDate(group.getCreateDate());
		this.setZoneId(group.getZoneId());

	}

	/**
	 * 通过用户对象、角色id集合、群组id集合构造GroupDto对象
	 * 
	 * @param user
	 * @param roleIds
	 * @param groupIds
	 */
	public GroupDto(Group group, String adminName, List<User> adminNames) {
		this.setId(group.getId());

		this.setGroupName(group.getGroupName());
		// this.setAdminName(adminName);
		this.setAdminNames(adminNames);
		this.setCorpId(group.getCorpId());
		this.setOwnerId(group.getOwnerId());
		this.setRank(group.getRank());
		this.setDcg(group.getDcg());
		this.setStatus(group.getStatus());
		this.setCreateDate(group.getCreateDate());
		this.setZoneId(group.getZoneId());
		this.setIsJoin(group.getIsJoin());

	}

	/**
	 * 通过用户对象、角色id集合、群组id集合构造GroupDto对象
	 * 
	 * @param user
	 * @param roleIds
	 * @param groupIds
	 */
	public GroupDto(Group group, List<Long> ids) {
		this.setId(group.getId());

		this.setGroupName(group.getGroupName());

		this.setCorpId(group.getCorpId());
		this.setOwnerId(group.getOwnerId());
		this.setRank(group.getRank());
		this.setDcg(group.getDcg());
		this.setStatus(group.getStatus());
		this.setUserIds(ids);
		this.setCreateDate(group.getCreateDate());
		this.setZoneId(group.getZoneId());

	}

	private Long[] list2Array(List<Long> datas) {
		Long[] nums = new Long[datas.size()];
		for (int i = 0; i < datas.size(); i++) {
			nums[i] = datas.get((int) i);
		}
		return nums;
	}

	public GroupDto() {
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
