package com.zom.cms.lh.tools;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zom.cms.model.Corporation;
import com.zom.cms.service.user.CorporationService1;
import com.zom.cms.lh.tools.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：控制层工具类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.ActionUtil <p>
 */
public class ActionUtil {
	
	/**
	 * 检查Session是否存在user，存在即返回，不存在则返回null
	 * @param session
	 * @return User session-user
	 */
	public static Corporation checkSession4User(HttpSession session){
		Corporation user = null;
		Object userObj = session.getAttribute("user");
		if(null != userObj){
			user = (Corporation)userObj;
		}
		return user;
	}
	public static Corporation checkSession4User(HttpServletRequest request){
		return checkSession4User(request.getSession());
	}
	
	/**
	 * 检查Session是否存在userId，存在即返回，不存在则返回null
	 * @param session
	 * @return int session-userId
	 */
	public static int checkSession4UserId(HttpSession session){
		int userId = 0;
		Object userIdObj = session.getAttribute("userId");
		if(null != userIdObj){
			userId = (Integer)userIdObj;
		}
		return userId;
	}
	public static int checkSession4UserId(HttpServletRequest request){
		return checkSession4UserId(request.getSession());
	}
	
	/**
	 * 检查Session是否存在adminId，存在即返回，不存在则返回null
	 * @param session
	 * @return int session-adminId
	 */
	public static int checkSession4AdminId(HttpSession session){
		int adminId = 0;
		Object adminIdObj = session.getAttribute("adminId");
		if(null != adminIdObj){
			adminId = (Integer)adminIdObj;
		}
		return adminId;
	}
	public static int checkSession4AdminId(HttpServletRequest request){
		return checkSession4AdminId(request.getSession());
	}
	
	/**
	 * 检查Session是否存在userId，存在则数据库中查询最新user对象返回，不存在则返回null
	 * @param session
	 * @param userService 用户Service
	 * @return User DB-user
	 */
	public static Corporation loadUserBySessionUserId(HttpSession session, CorporationService1 userService){
		Corporation user = null;
		Object userIdObj = session.getAttribute("userId");
		if(null != userIdObj){
			Integer userId = (Integer)userIdObj;
			user = userService.load(userId);
		}
		return user;
	}
	public static Corporation loadUserBySessionUserId(HttpServletRequest request, CorporationService1 userService){
		return loadUserBySessionUserId(request.getSession(), userService);
	}
	
	/**
	 * 得到request中的所有参数（包括分页相关参数）</p>
	 * Pagination.getOrderByAndPage(RequestUtil.getRequestParam(request), request);
	 * @param request
	 * @return map
	 */
	public static Map<String, Object> getAllParam(HttpServletRequest request){
		return Pagination.getOrderByAndPage(RequestUtil.getRequestParam(request), request);
	}
	
	
	
	/** 具体业务逻辑对应方法  */
	
}