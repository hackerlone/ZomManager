package com.zom.cms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.model.Role;
import com.zom.cms.model.TuId;
import com.zom.cms.model.User;
import com.zom.cms.model.UserRole;

public interface ITuIdDao{
	/**
	 * 查找系统zones number
	 */
	public int findZonesCount();
	
	/**
	 * 获取公司下所有zone TUID 信息
	 * @return TuID list
	 */
	public List<TuId> findZones(PageBounds pageBounds);
	
	/**
	 * 添加zone TUID
	 * @param role 角色对象
	 */
	
	/**
	 * 根据指定zoneid获取zone Tuid信息
	 * @param zone 
	 */
	public TuId load(@Param("id") int id);
	
	/**
	 * 更新zone Tuid
	 * @param tuid zone下的TG UID 信息
	 */
	public int update(TuId tuid);
	
	/**
	 * 更新zone Tuid
	 * @param tuid zone下的TG UID 信息
	 */
	public int updateUid(@Param("id") int id);
	
	
	/**
	 * 更新zone Tuid
	 * @param tuid zone下的TG UID 信息
	 */
	public int updateTgid( @Param("id") int id);
	
	/**
	 * 删除角色信息(在删除角色之前需要判断此角色中是否含有用户信息)
	 * @param id 角色id
	 */
	public int delete(@Param("zone") int zone);
	

}
