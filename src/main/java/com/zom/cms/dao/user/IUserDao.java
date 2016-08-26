package com.zom.cms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.model.ConsolerUser;
import com.zom.cms.model.Group;
import com.zom.cms.model.RoleType;
import com.zom.cms.model.User;
import com.zom.cms.model.UserAdmin;
import com.zom.cms.model.UserGroup;
import com.zom.cms.model.UserRole;

public interface IUserDao extends CommonMapper<User> {
	/**
	 * 获取用户总数
	 * @return 用户总数
	 */
	public int findUserCount();
	
	/**
	 * 获取公司下用户总数
	 * @return 用户总数
	 */
	public int findCorpUserCount(@Param("corpId")int corp_id);
	
	/**
	 * 获取用户分页列表集合信息
	 * @param pageBounds 分页参数
	 * @return 用户分页列表集合信息
	 */
	public List<User> findUser(PageBounds pageBounds);
	
	/**
	 * 获取用公司下户分页列表集合信息
	 * @param pageBounds 分页参数
	 * @return 用户分页列表集合信息
	 */
	public List<User> findCorpUser(PageBounds pageBound,@Param("corpId") int corpId);
	
	/**
	 * 根据用户id获取此用户所对应所有的角色id集合
	 * @param id 用户id
	 * @return 获取此用户所对应所有的角色id集合
	 */
	public List<UserRole> listUserRoleIds(@Param("userId") Long userId);
	
	/**
	 * 根据用户id获取此用户所对应所有的群组id集合
	 * @param id 用户id
	 * @return 获取此用户所对应所有的群组id集合
	 */
	public List<UserGroup> listUserGroupIds(@Param("userId") Long userId);
	
	/**
	 * 获取用户的所有角色信息
	 * @param userId
	 * @return
	 */
	public List<UserRole> listUserRoles(Long userId);
	
	
	/**
	 * 获取用户的所有组信息
	 * @param userId
	 * @return
	 */
	public List<Group> listUserGroups(Long userId);
	
	/**
	 * 根据用户和角色获取用户角色的关联对象
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRole loadUserRole(@Param("userId")Long userId,@Param("roleId")int roleId);
	/**
	 * 根据用户和组获取用户组关联对象
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public UserGroup loadUserGroup(@Param("userId")Long userId,@Param("groupId")Long groupId);
	/**
	 * 根据用户电话获取用户对象
	 * @param phone
	 * @return
	 */
	public User loadByPhone(@Param("phone") String phone);
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadByUsername(@Param("logonName") String logonName);
	
	/**
	 * 根据角色id获取用户列表
	 * @param roleId 角色id
	 * @return 用户角色关联信息
	 */
	public List<UserRole> listRoleUsers(@Param("roleId") int roleId);
	/**
	 * 根据角色类型获取用户对象
	 * @param roleType
	 * @return
	 */
	public List<User> listRoleUsers(RoleType roleType);
	/**
	 * 根据群组id获取群组下用户的信息
	 * @param gid 群组id
	 * @return 群组下用户的信息
	 */
	public List<UserGroup> listGroupUsers(@Param("groupId") Long gid);

	/**
	 * 添加用户群组关联信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 */
	public void addUserGroup(@Param("userId")Long userId,@Param("groupId")Long groupId,@Param("groupRight") Long groupRight);
	
	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public User load(@Param("id")Long id);
	

	/**
	 * 删除用户群组关联关系
	 * @param uid 用户id
	 * @param gid 群组id
	 */
	public void deleteUserGroup(@Param("userId")Long userId,@Param("groupId")Long groupId);
	
	/**
	 * 更新用户基本信息
	 * @param user 用户对象
	 */
	public void update(User u);
	
	/**
	 * 更新用户密码
	 * @param user 用户对象
	 */
	public void updatePwd(User u);

	
	/**
	 * 根据用户id，删除用户
	 * @param id 用户id
	 */
	public void delete(@Param("userId") Long userId);
	
	/**
	 * 删除群组中的用户关联关系
	 * @param gid 群组信息
	 */
	public int deleteGroupUsers(@Param("groupId") Long groupId);
	

	public void add(User user);
	
	public List<User> listUsersByGid(@Param("groupId") Long id);
	
	public List<User> listConsolesByCorpId(int id);
	public List<User> listCorpConsoles(@Param("corpId")int corpId);

	public int findConsoleCount(@Param("rank")int rank);

	public List<User> findConsole(PageBounds pageBounds,@Param("rank")int rank);

	public int findCorpConsoleCount(@Param("corpId")int corp_id,@Param("rank")int rank);

	public List<User> findCorpConsole(PageBounds pageBound,@Param("corpId") int corpId,@Param("rank")int rank);
	public List<User> findCorpConsoleList(@Param("corpId") int corpId,@Param("rank")int rank);

	
	
	public List<UserGroup> listGroupUserId(@Param("gid") Long id);

	public List<User> findCorpAllUser(@Param("corpId") int corpId);

	public void addUserAdmin(@Param("userId") Long userId, @Param("adminId") Long adminId);
	
	public List<User>listUsersByConsoleId(@Param("adminId") Long adminId);
	public List<User>listFullUsersByConsoleId(@Param("adminId") Long adminId);

	public List<Long>listUsersIdByConsoleId(@Param("adminId") Long adminId);

	public List<String> listConsoleNameByIds(@Param("userId") Long userId);


	public void deleteAdminUsers(@Param("adminId") Long adminId);
	public void deleteUserAdmins(@Param("userId") Long userId);
	public void deleteUserAdmin(@Param("adminId") Long adminId,@Param("userId") Long userId);
	public void deleteUserGroups(@Param("userId") Long userId);
	public List<String> listUserGroupNames(@Param("userId") Long userId);

	public List<Long> listUserAmdinIds(@Param("userId") Long userId);
	public List<Long> listIPriControlled(@Param("userId") Long userId);
	public List<String> listUsersNameByConsoleId(@Param("adminId") Long adminId);

	public int findCorpAllConsoleCount(int corp_id);

	public int selectCountByUserCondition(Map<String, Object> map);

	public List<User> selectListByUserCondition(Map<String, Object> map);

	public List<User> listGroupAllUsers(Long id);

	public List<User> listAdminAllUsers(Long id);

	public void deleteByCorpId(Integer corpId);

	public void deleteByAdminId(Long id);

	public List<User> selectConsolerDtoListByUserCondition(Map<String, Object> map);

	public void addConsolerUsers(Long long1, Long adminId);

	public List<User> selectConsolerDtoList2ByUserCondition(Map<String, Object> map);

	public List<User> selectConsolerDtoList1ByUserCondition(Map<String, Object> map);


	public UserAdmin loadUserAdmin(Map<String, Object> map);


	



	

}
