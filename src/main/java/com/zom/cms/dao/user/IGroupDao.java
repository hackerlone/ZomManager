package com.zom.cms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.model.Group;

public interface IGroupDao extends CommonMapper<Group> {
	/**
	 * 获取群组列表信息
	 * @return 群组列表信息
	 */
	public List<Group> listGroup();

	/**
	 * 获取群组列表信息
	 * @return 群组列表信息
	 */
	public List<Group> listCorpGroup(@Param("corpId")int id);
	
	/**
	 * 获取分页群组列表信息
	 * @return 分页群组列表信息
	 */
	public List<Group> findGroup(PageBounds pageBounds);
	
	/**
	 * 获取公司下分页群组列表信息
	 * @return 分页群组列表信息
	 */
	public List<Group> findCorpGroup(PageBounds pageBounds,@Param("corpId")int id);
	
	/**
	 * 添加群组
	 * @param group 群组对象
	 */
	public int add(Group group);
	
	/**
	 * 获取群组总数信息
	 * @return 群组总数
	 */
	public int findGroupCount();
	
	/**
	 * 获取公司下群组总数信息
	 * @return 群组总数
	 */
	public int findGroupCorpCount(@Param("corpId")int id);	
	/**
	 * 根据群组id获取群组信息
	 * @param gid 群组id
	 * @return 群组信息
	 */
	public Group load(@Param("id") Long id);
	
	/**
	 * 根据群组id删除群组信息
	 * @param gid 群组id
	 */
	public int delete(@Param("groupId") Long gid);

	/**
	 * 更新群组信息
	 * @param group 群组对象
	 * @return 更新是否成功
	 */
	public int update(Group group);

	public List<Group> listGroupsByUid(@Param("uid") Long id);
  
	public Long lastInsertGroupId();
	/**
	 * 获取组的环信名
	 * @return string name
	 */
	public String findGroupEasemobNameById(@Param("id")Long id);

	public int selectCountByUserCondition(Map<String, Object> map);

	public List<Group> selectListByUserCondition(Map<String, Object> map);

	public void deleteByCorpId(Integer corpId);	



}
