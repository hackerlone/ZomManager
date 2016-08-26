package com.zom.cms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.model.BmsVer;

public interface IBMSVerDao extends CommonMapper<BmsVer> {
	/**
	 * 获取用户总数
	 * @return 用户总数
	 */
	public int findBmsVerCount();

	
	/**
	 * 获取用户分页列表集合信息
	 * @param pageBounds 分页参数
	 * @return 用户分页列表集合信息
	 */


	public List<BmsVer> findBmsVer(PageBounds pageBounds);
	
	public List<BmsVer> findBmsVers();
	
	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public BmsVer load(@Param("id")Long id);
	


	public void update(BmsVer u);
	


	
	/**
	 * 根据用户id，删除用户
	 * @param id 用户id
	 */
	public void delete(@Param("id") Long id);
	


	public void add(BmsVer ver);
	
	public BmsVer findSpecBmsVer(@Param("osType") String OsType);
	

}
