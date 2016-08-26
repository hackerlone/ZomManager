package com.zom.cms.model;


/**
 * 角色对象，用来对应可以访问的功能，系统中为了简单值定义了管理员，发布人员和审核人员
 * @author Administrator
 *
 */
public class TuId {
	/**
	 * zone id
	 */
	private int id;
	/**
	 * zone name
	 */
	private String name;
	/**
	 * max uid
	 */
	private Long maxUid;
	
	/**
	 * max talk group Id
	 */
	private Long maxTgid;
	
	/**
	 * current uid
	 */
	private Long curUid;	
	
	/**
	 * current talk group ID
	 */
	private Long curTgid;	
	
	
	

	public Long getCurUid() {
		return curUid;
	}

	public void setCurUid(Long curUid) {
		this.curUid = curUid;
	}

	public Long getCurTgid() {
		return curTgid;
	}

	public void setCurTgid(Long curTgid) {
		this.curTgid = curTgid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMaxUid() {
		return maxUid;
	}

	public void setMaxUid(Long maxUid) {
		this.maxUid = maxUid;
	}

	public Long getMaxTgid() {
		return maxTgid;
	}

	public void setMaxTgid(Long maxTgid) {
		this.maxTgid = maxTgid;
	}
	

	
	
}
