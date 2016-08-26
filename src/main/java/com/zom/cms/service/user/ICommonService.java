package com.zom.cms.service.user;

import java.util.List;
import java.util.Map;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：通用-公共基本操作<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华<p>
 * <strong> 编写时间：</strong> 2016年3月1日20:32:42<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0<p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 泛型公共方法类，涉及数据库基本的CRUD  <p>
 */
public interface ICommonService<T> {

	/**======================= 基础：查询方法 开始 ====================================*/
	/**
	 * 根据map中的查询条件获取结果，返回List
	 * @param map
	 * @return List<T> 泛型对象集合
	 */
	public List<T> selectListByCondition(Map<String, Object> map);
	
	/**
	 * 根据map中的查询条件获取结果，返回泛型对象（只返回一条数据,limit 0,1）
	 * @param map
	 * @return T 泛型对象
	 */
	public T selectByCondition(Map<String, Object> map);
	
	/**
	 * 根据map中的查询条件获取结果条数
	 * @param map
	 * @return int 查询结果条数
	 */
	public int selectCountByCondition(Map<String, Object> map);
	
	/**
	 * 根据主键ID查询数据
	 * @param id 主键ID
	 * @return T 泛型对象
	 */
	public T selectByPrimaryKey(Integer id);
	
	/**
	 * 根据序号查询数据（serial）
	 * @param serial 序号
	 * @return T 泛型对象
	 */
	public T selectBySerial(String serial);
	/**======================= 基础：查询方法 结束 ====================================*/

	/**======================= 基础：修改方法 开始 ====================================*/
	
	/**
	 * 根据主键ID修改对象数据</p>
	 * SQL中需要的key：id,updatedBy
	 * @param record 需要修改的对象
	 * @return int 数据库修改条数
	 */
	public int updateByPrimaryKey(T record);
	
	/**
	 * 根据序号修改对象数据</p>
	 * SQL中需要的key：serial,updatedBy
	 * @param record 需要修改的对象
	 * @return int 数据库修改条数
	 */
	public int updateBySerial(T record);
	
	/**
	 * 根据主键ID串修改对象数据</p>
	 * SQL中需要的key：ids,updatedBy
	 * @param record 需要修改的对象（对象的属性值批量更新到ID串对应的记录）
	 * @return int 数据库修改条数
	 */
	public int updateByIds(T record);
	
	/**
	 * 根据自定义条件修改对象数据</p>
	 * UPDATE <include refid="table"/> SET
	 *	<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *	updated_at = NOW(),
	 *	${condition}
	 * @param condition 自定义条件：update XX set ${condition}
	 * @param updatedBy 修改人
	 * @return int 数据库修改条数
	 */
	public int updateByCondition(String condition, String updatedBy);

	/**
	 * 根据主键ID串进行逻辑删除数据</p>
	 * UPDATE <include refid="table"/>
	 *	<set>
	 *		deleted_at = NOW(),
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 * 	</set>
	 *	WHERE id IN( ${ids} )
	 * @param ids 主键ID串
	 * @param updatedBy 修改人
	 * @return int 数据库修改条数
	 */
	public int updateDeletedNowByIds(String ids, String updatedBy);
	
	/**
	 * 根据主键ID串进行取消逻辑删除数据（置空deleted_at字段）</p>
	 * UPDATE <include refid="table"/>
	 *	<set>
	 *		deleted_at = NULL,
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 *	</set>
	 *	WHERE id IN( ${ids} )
	 * @param ids 主键ID串
	 * @param updatedBy 修改人
	 * @return int 数据库修改条数
	 */
	public int updateDeletedNullByIds(String ids, String updatedBy);
	
	/**
	 * 根据主键ID进行逻辑删除数据</p>
	 * UPDATE <include refid="table"/>
	 *	<set>
	 *		deleted_at = NOW(),
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 *	</set>
	 *	WHERE id = #{id,jdbcType=INTEGER}
	 * @param id 主键ID
	 * @param updatedBy 修改人
	 * @return int 数据库修改条数
	 */
	public int updateDeletedNowById(Integer id, String updatedBy);
	
	/**
	 * 根据主键ID进行取消逻辑删除数据（置空deleted_at字段）</p>
	 * UPDATE <include refid="table"/>
	 *	<set> 
	 *		deleted_at = NULL, 
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 *	</set>
	 *	WHERE id = #{id,jdbcType=INTEGER}
	 * @param id 主键ID
	 * @param updatedBy 修改人
	 * @return int 数据库修改条数
	 */
	public int updateDeletedNullById(Integer id, String updatedBy);

	/**
	 * 根据Map中的条件更新指定字段</p>
	 * UPDATE <include refid="table"/>
	 *	<set>
	 *		<if test="expression1 != null">${expression1},</if>
	 *		<if test="expression2 != null">${expression2},</if>
	 *		<if test="expression3 != null">${expression3},</if>
	 *		<if test="expression4 != null">${expression4},</if>
	 *		<if test="expression5 != null">${expression5},</if>
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 *	</set>
	 *	WHERE id = #{id,jdbcType=INTEGER}
	 * @param map
	 * @return int 数据库修改条数
	 */
	public int updateFieldById(Map<String, Object> map);
	
	/**
	 * 根据Map中的条件更新指定字段</p>
	 * UPDATE <include refid="table"/>
	 *	<set>
	 *		<if test="expression1 != null">${expression1},</if>
	 *		<if test="expression2 != null">${expression2},</if>
	 *		<if test="expression3 != null">${expression3},</if>
	 *		<if test="expression4 != null">${expression4},</if>
	 *		<if test="expression5 != null">${expression5},</if>
	 *		<if test="updatedBy != null">updated_by = #{updatedBy,jdbcType=VARCHAR},</if>
	 *		updated_at = NOW()
	 *	</set>
	 *	WHERE id IN( ${ids} )
	 * @param map
	 * @return int 数据库修改条数
	 */
	public int updateFieldByIds(Map<String, Object> map);
	
	/**
	 * 根据主键ID更新更新对象中的非空字段</p>
	 * SQL中需要的key：id,updatedBy
	 * @param record 需要修改的对象
	 * @return int 数据库修改条数
	 */
	public int updateByPrimaryKeySelective(T record);
	
	/**
	 * 根据serial序号更新更新对象中的非空字段</p>
	 * SQL中需要的key：serial,updatedBy
	 * @param record 需要修改的对象
	 * @return int 数据库修改条数
	 */
	public int updateBySerialSelective(T record);
	
	/**
	 * 根据对象中的ID串字段值批量更新对象中的非空字段</p>
	 * SQL中需要的key：ids,updatedBy
	 * @param record 需要修改的对象（对象的属性值批量更新到ID串对应的记录）
	 * @return int 数据库修改条数
	 */
	public int updateByIdsSelective(T record);
	/**======================= 基础：修改方法 结束 ====================================*/
	
	/**======================= 基础：新增方法 开始 ====================================*/
	
	/**
	 * 插入数据
	 * @param record
	 * @return 数据库新增条数（调用对象的getId方法获取数据库数据ID）
	 */
	public int insert(T record);
	
	/**
	 * 指插入数据
	 * @param list 数据列表
	 * @return 数据库新增条数
	 */
	public int insertBatch(List<T> list);

	/**
	 * 插入数据（只插入非空字段）
	 * @param record
	 * @return 数据库新增条数
	 */
	public int insertSelective(T record);
	/**======================= 基础：新增方法 结束 ====================================*/
	
	/**======================= 基础：删除方法 开始 ====================================*/
	
	/**
	 * 根据主键删除数据</p>
	 * DELETE FROM <include refid="table"/> WHERE id = #{id,jdbcType=INTEGER}
	 * @param id
	 * @return 数据库删除条数
	 */
	public int deleteByPrimaryKey(Integer id);
	
	/**
	 * 根据自定义条件删除数据</p>
	 * DELETE FROM <include refid="table"/> WHERE ${condition}
	 * @param condition 自定义条件
	 * @return 数据库删除条数
	 */
	public int deleteByCondition(String condition);
	
	/**
	 * 根据ID串删除数据</p>
	 * DELETE FROM <include refid="table"/> WHERE id IN (${ids})
	 * @param ids
	 * @return 数据库删除条数
	 */
	public int deleteByIds(String ids);
	/**======================= 基础：删除方法 结束 ====================================*/
	
	
	/**======================= 组合：查询方法 开始 ====================================*/
	/**======================= 组合：查询方法 结束 ====================================*/
	
}
