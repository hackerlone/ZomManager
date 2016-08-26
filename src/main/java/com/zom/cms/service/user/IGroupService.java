package com.zom.cms.service.user;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.dto.GroupDto;
import com.zom.cms.model.Group;
import com.zom.cms.page.Pager;


public interface IGroupService {
	public enum MyTsType{  PROFILE,GROUP  }
	/**
	 * 获取分页群组列表信息
	 * @return 分页群组列表信息
	 */
	public Pager<GroupDto> findGroup();

	/**
	 * 获取分页公司群组列表信息
	 * @return 分页公司群组列表信息
	 */
	public Pager<GroupDto> findGroupCorp(int id);
	
	/**
	 * 添加群组
	 * @param group 群组对象
	 */
	public Group add(GroupDto groupDto);
	
	/**
	 * 获取群组列表信息
	 * @return 群组列表信息
	 */
	public List<Group> listGroup();
	
	/**
	 * 获取公司下群组列表信息
	 * @return 群组列表信息
	 */
	public List<Group> listCorpGroup(int corp_id);

	/**
	 * 删除群组信息(在删除之前先判断群组中是否有用户信息)
	 * @param id 群组id
	 */
	public int delete(Long id);
	
	/**
	 * 更新群组信息
	 * @param group 群组对象
	 */
	public int update(Group group);

	/**
	 * 根据群组id获取群组信息
	 * @param id 群组id
	 * @return 群组
	 */
	public Group load(Long id);
	
	/**
	 * 删除群组中的用户信息
	 * @param gid 群组id
	 */
	public int deleteGroupUsers(Long gid);

	


	

	


	
	public void addGroupUsers(Long gid, List<Long>uids,Long ownerId);

	public Long lastInsertGroupId();

	public Group updateOneMyTs(Group group, long gid, MyTsType type, boolean doSave,
			int value);

	List<Long> listGroupUserId(Long gid);

	void update(Group group, List<Long>userIds);

	public void updateStatus(Long id);

	public int findCorpGroupCount(int corp_id);

	public void deleteUserGroups(Long uid);
	
	
	public JSONObject selectGroupDtoListByCondition(JSONObject json, Map<String, Object> map);
	public List<Group> selectListByCondition(Map<String, Object> map);
	public Group selectByCondition(Map<String, Object> map);
	public int selectCountByCondition(Map<String, Object> map);
	public Group selectByPrimaryKey(Integer id);

	public JSONObject selectGroupDtoListByUserCondition(JSONObject json, Map<String, Object> map);

	public void delGroupUsers(Long gid, List<Long> uids, Long ownerId);

	public void deleteByCorpId(Integer corpId);
	
}
