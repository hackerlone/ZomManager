package com.zom.cms.service.user;

import java.util.List;
import java.util.Map;

import com.zom.cms.model.Corporation;
import com.zom.cms.model.ZoneIdAssign;
import com.zom.cms.page.Pager;

public interface ICorporationService1 {//extends ICommonService<Corporation> 
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * @param user 用户对象
	 * @param rids 用户的所有角色信息
	 * @param gids 用户的所有组信息
	 * @return 
	 */
	public void add(Corporation user,int length);
	
	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public Corporation load(int id);

	/**
	 * 删除用户，注意需要把用户和角色和组的对应关系删除
	 * 如果用户存在相应的文章不能删除
	 * @param id
	 */
	public void delete(int id);

	
	/**
	 * 更新用户基本信息
	 * @param user 用户对象
	 */
	public void update(Corporation user);
	
	
	/**
	 * 更新密码方法
	 * @param uid
	 * @param oldPwd
	 * @param newPwd
	 */
	public void updatePwd(int uid,String oldPwd,String newPwd);

	/**
	 * 获取用户分页列表集合信息
	 * @return 获取用户分页列表集合信息
	 */
	public Pager<Corporation> findUser();

	
	public Corporation login(String username, String password);
	
	/**
	 * 更新公司的状态
	 * @param id
	 */
	public void updateStatus(int id);
	

	public List<Corporation> selectListByCondition(Map<String, Object> map);
	public Corporation selectByCondition(Map<String, Object> map);
	public int selectCountByCondition(Map<String, Object> map);
	public Corporation selectByPrimaryKey(Integer id);

	public List<ZoneIdAssign> selectAllAssign();

	public Corporation loadByPhone(String phone);
}
