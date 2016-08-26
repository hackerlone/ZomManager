package com.zom.cms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.model.Corporation;
import com.zom.cms.model.Group;
import com.zom.cms.model.RoleType;
import com.zom.cms.model.User;
import com.zom.cms.model.UserGroup;
import com.zom.cms.model.UserRole;
import com.zom.cms.model.ZoneIdAssign;

public interface ICorporationDao extends CommonMapper<Corporation> {// extends CommonMapper<Corporation> 
	/**
	 * 获取用户总数
	 * @return 用户总数
	 */
	public int findUserCount();
	
	/**
	 * 获取用户分页列表集合信息
	 * @param pageBounds 分页参数
	 * @return 用户分页列表集合信息
	 */
	public List<Corporation> findUser(PageBounds pageBounds);
	
	/**
	 * 根据用户id获取此用户所对应所有的角色id集合
	 * @param id 用户id
	 * @return 获取此用户所对应所有的角色id集合
	 */
	public List<UserRole> listUserRoleIds(@Param("userId") int userId);
	
	/**
	 * 根据用户id获取此用户所对应所有的群组id集合
	 * @param id 用户id
	 * @return 获取此用户所对应所有的群组id集合
	 */
	public List<UserGroup> listUserGroupIds(@Param("userId") int userId);
	
	/**
	 * 获取用户的所有角色信息
	 * @param userId
	 * @return
	 */
	public List<UserRole> listUserRoles(int userId);
	
	
	/**
	 * 获取用户的所有组信息
	 * @param userId
	 * @return
	 */
	public List<Group> listUserGroups(int userId);
	
	/**
	 * 根据用户和角色获取用户角色的关联对象
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRole loadUserRole(@Param("userId")int userId,@Param("roleId")int roleId);
	/**
	 * 根据用户和组获取用户组关联对象
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public UserGroup loadUserGroup(@Param("userId")int userId,@Param("groupId")int groupId);
	/**
	 * 根据用户名获取用户对象
	 * @param loginName
	 * @return
	 */
	public Corporation loadByUsername(@Param("username") String username);
	
	/**
	 * 根据用户名获取用户对象
	 * @param phone
	 * @return
	 */
	public Corporation loadByPhone(@Param("phone") String phone);
	
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
	public List<UserGroup> listGroupUsers(@Param("groupId") int gid);
	/**
	 * 添加用户角色关联信息
	 * @param userId 用户id
	 * @param roleId 角色id
	 */
	public void addUserRole(@Param("userId")int userId,@Param("roleId")int roleId);
	/**
	 * 添加用户群组关联信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 */
	public void addUserGroup(@Param("userId")int userId,@Param("groupId")int groupId);
	
	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public Corporation load(@Param("id")int id);
	
	/**
	 * 删除用户角色关联关系
	 * @param uid 用户id
	 * @param rid 角色id
	 */
	public void deleteUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
	/**
	 * 删除用户群组关联关系
	 * @param uid 用户id
	 * @param gid 群组id
	 */
	public void deleteUserGroup(@Param("userId")int userId,@Param("groupId")int groupId);
	
	/**
	 * 更新用户基本信息
	 * @param user 用户对象
	 */
	public void update(Corporation u);
	
	/**
	 * 根据用户id，删除用户、角色关联关系（删除用户时，删除全部此用户和角色关联关系）
	 * @param uid 用户id
	 */
	public void deleteUserRoles(@Param("userId") int userId);
	/**
	 * 根据用户id，删除用户、群组关联关系（删除用户时，删除全部此用户和群组关联关系）
	 * @param gid 
	 */
	public void deleteUserGroups(@Param("userId") int userId);
	
	/**
	 * 根据用户id，删除用户
	 * @param id 用户id
	 */
	public void delete(@Param("id") int userId);
	
	/**
	 * 删除群组中的用户关联关系
	 * @param gid 群组信息
	 */
	public int deleteGroupUsers(@Param("groupId") int groupId);
	
	/**
	 * 删除角色中的用户信息(角色和用户关系)
	 * @param roleId 角色id
	 */
	public int deleteRoleUsers(@Param("roleId") int roleId);

	public Integer add(Corporation user);

	public List<User> listUsersByRid(@Param("roleId") int id);

	public List<User> listUsersByGid(@Param("groupId") int id);
	
	List<Corporation> selectListByCondition(Map<String, Object> map);
	Corporation selectByCondition(Map<String, Object> map);
	int selectCountByCondition(Map<String, Object> map);
	Corporation selectByPrimaryKey(Integer id);

	public List<ZoneIdAssign> selectAllAssign();

	public void addCorpZone(Map<String, Object> map);
}
