package com.zom.cms.service.user;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.dto.UserDto;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserAdmin;
import com.zom.cms.model.UserGroup;
import com.zom.cms.page.Pager;

public interface IUserService {
	public enum MyTsType{  PROFILE,GROUP,ADMIN  }
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * @param user 用户对象
	 * @param rids 用户的所有角色信息
	 * @param gids 用户的所有组信息
	 */
	public void add(User user,List<Long> rids,Long[]gids,List<Long> userIds);
	
	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public User load(Long id);
	

	/**
	 * 根据用户id获取此用户所对应所有的群组id集合
	 * @param id 用户id
	 * @return 获取此用户所对应所有的群组id集合
	 */
	public List<Long> listUserGroupIds(Long id);
	
	/**
	 * 删除用户，注意需要把用户和角色和组的对应关系删除
	 * 如果用户存在相应的文章不能删除
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
	 * 对于group而已同样要做这个操作
	 * @param user
	 * @param rids
	 * @param gids
	 */
	public void update(User user, List<Long> uids, Long[] gids, List<Long> consoleIds);
	


	
	/**
	 * 更新用户基本信息
	 * @param user 用户对象
	 */
	public void update(User user);
	
	
	/**
	 * 更新密码方法
	 * @param uid
	 * @param oldPwd
	 * @param newPwd
	 */
	public void updatePwd(Long uid,String oldPwd,String newPwd);
	/**
	 * 更新用户的状态
	 * @param id
	 */
	public void updateStatus(Long id);
	/**
	 * 获取用户分页列表集合信息
	 * @return 获取用户分页列表集合信息
	 */
	public Pager<UserDto> findUser();
	
	/**
	 * 获取公司下用户分页列表集合信息
	 * @return 获取用户分页列表集合信息
	 */
	public Pager<UserDto> findCorpUser(int corp_id);
	
	
	
	/**
	 * 获取用户的所有组信息
	 * @param id
	 * @return
	 */
	public List<Group> listGroupsByUid(Long id);
	
	
	
	public List<UserGroup> listGroupUsers(Long gid);

	

	public List<User> listUsersByGid(Long id);
	
	public List<User> listConsolesByCorpId(int id);
	public List<User> ListCorpConsoles(int id);

	public User updateOneMyTs(User user, long uid, MyTsType type, boolean doSave,
			int value);
	
	/**
	 * 获取Console分页列表集合信息
	 * @return 获取Console分页列表集合信息
	 */
	public Pager<UserDto> findConsoles(int rank);
	
	public List<User> findCorpConsolesList(int corp_id,int rank);
	
	/**
	 * 获取公司下Console分页列表集合信息
	 * @return 获取Console分页列表集合信息
	 */
	public Pager<UserDto> findCorpConsoles(int corp_id,int rank);

	public List<User> findCorpAllUser(int corp_id);

	public List<User> listUsersByConsoleId(Long id);

	public List<Long> listUsersIdByConsoleId(Long id);

	public void deleteAdminUsers(Long uid);

	public void deleteUserAdmin(Long admId, Long uid);
	
	public List<String> listUserGroupNames(Long uid);

	public List<Long> listSecMyAdmins(Long uid);

	public List<String> listAdminNames(Long uid);

	public List<Long> listUserAmdinIds(Long uid);

	public int findCorpUserCount(int corp_id);

	public int findCorpAllConsoleCount(int corp_id);

	public void deleteUserAdmins(Long uid);
	
	
	
	public List<UserDto> selectUserDtoListByCondition(Map<String, Object> map);
	public List<User> selectListByCondition(Map<String, Object> map);
	public User selectByCondition(Map<String, Object> map);
	public int selectCountByCondition(Map<String, Object> map);
	public User selectByPrimaryKey(Integer id);
	public JSONObject findConsoles(JSONObject json, Map<String, Object> map);

	public void addUserGroups(User user, List<Long> gids);

	public void delUserGroups(User user, List<Long> gids);

	public JSONObject selectGroupDtoListByUserCondition(JSONObject json, Map<String, Object> map);

	public List<UserGroup> listGroupUserId(Long id);

	public List<User> listGroupAllUsers(Long id);

	public List<User> listAdminAllUsers(Long id);

	public User loadByUsername(String string);

	public void addUserAdmin(Long userId, Long adminId);

	public void deleteByCorpId(Integer corpId);

	public void deleteByAdminId(Long id);

	public JSONObject selectConsolerDtoListByUserCondition(JSONObject json, Map<String, Object> map);

	void addConsolerUsers(Long adminId, List<Long> uids);

	void delConsolerUsers(Long gid, List<Long> uids);

	public JSONObject selectConsolerDtoList2ByUserCondition(JSONObject json, Map<String, Object> map);

	public JSONObject selectConsolerDtoList1ByUserCondition(JSONObject json, Map<String, Object> map);




	
}
