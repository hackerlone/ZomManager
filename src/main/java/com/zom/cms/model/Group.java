package com.zom.cms.model;

import java.util.Date;


/**
 * 用户组对象，使用该对象来获取可以发布文章的栏目信息
 * @author Administrator
 *
 */
public class Group {
	/**
	 * 组id
	 */
	private Long id;
	private Long groupTs;
	private Long ownerId;
	private String groupName;
	private Long pttid;
	private Date pttTime;
	private String groupNameEasemob;
	private Date createDate;
  //  private Long adminId;
	private int corpId;
	private int rank;
	private int dcg; //normal group or special group for individual call
	private int status; 
	private int preconfig;
	private int zoneId=1;
	private int isJoin;
	
	
	
	
	


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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupTs() {
		return groupTs;
	}
	public void setGroupTs(Long groupTs) {
		this.groupTs = groupTs;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getPttid() {
		return pttid;
	}
	public void setPttid(Long pttid) {
		this.pttid = pttid;
	}
	public Date getPttTime() {
		return pttTime;
	}
	public void setPttTime(Date pttTime) {
		this.pttTime = pttTime;
	}

	public String getGroupNameEasemob() {
		return groupNameEasemob;
	}
	public void setGroupNameEasemob(String groupNameEasemob) {
		this.groupNameEasemob = groupNameEasemob;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getCorpId() {
		return corpId;
	}
	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}
	public int getDcg() {
		return dcg;
	}
	public void setDcg(int dcg) {
		this.dcg = dcg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPreconfig() {
		return preconfig;
	}
	public void setPreconfig(int preconfig) {
		this.preconfig = preconfig;
	}
	public  Group(String groupname, Long ownerid,int corpId,int rank,Long consoleId){
		this.groupName = groupname;
		this.ownerId=ownerid;
		this.corpId=corpId;
		this.rank = rank;
	

		
		
	}
	public  Group(){

		
		
	}
	
	
}
