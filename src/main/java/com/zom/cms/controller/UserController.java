package com.zom.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zom.cms.auth.AuthMethod;
import com.zom.cms.dto.UserDto;
import com.zom.cms.lh.config.BaseTip;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.BmsVer;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.Corporation;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserGroup;
import com.zom.cms.page.Pager;
import com.zom.cms.service.user.IGroupService;
import com.zom.cms.service.user.IUserService;

@Controller
@RequestMapping("/admin/user")
// @AuthClass("login")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	/*
	 * @Autowired private IRoleService roleService;
	 */

	private static Logger logger = Logger.getLogger("R");

	/**
	 * 获取用户分页列表集合信息
	 * 
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public JSONObject getUserList(HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			// List<User> dataList = userService.selectListByCondition(map);

			Integer corpId = (Integer) session.getAttribute("corpId");
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			if (null == corpId)
				corpId = 1;// TODO 临时固定一个值，后期修改
			if (isAdmin != true) {
				map.put("corpId", corpId);
			}
			List<UserDto> dataList = userService.selectUserDtoListByCondition(map);
			int total = userService.selectCountByCondition(map);
			Result.gridData(dataList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/getUserList", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value="/getGroupUserList", method=RequestMethod.POST)
	public JSONObject getGroupUserList(HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
			map.put("corpId", corpId);
			json = userService.selectGroupDtoListByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getConsolerUserList", method=RequestMethod.POST)
	public JSONObject getConsolerUserList(HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
//			json = userService.selectGroupDtoListByUserCondition(json, map);
			map.put("corpId", corpId);
			map.put("rankNum", "0");
			json = userService.selectConsolerDtoListByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getUserConsolerList", method=RequestMethod.POST)
	public JSONObject getUserConsolerList(HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
//			json = userService.selectGroupDtoListByUserCondition(json, map);
			map.put("corpId", corpId);
			map.put("rankNum", "1");
			json = userService.selectConsolerDtoListByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getUserConsoler2List", method=RequestMethod.POST)
	public JSONObject getUserConsoler2List(HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
//			json = userService.selectGroupDtoListByUserCondition(json, map);
			map.put("corpId", corpId);
			map.put("rankNum", "2");
			json = userService.selectConsolerDtoList2ByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getUserConsoler1List", method=RequestMethod.POST)
	public JSONObject getUserConsoler1List(HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
//			json = userService.selectGroupDtoListByUserCondition(json, map);
			map.put("corpId", corpId);
			map.put("rankNum", "1");
			json = userService.selectConsolerDtoList1ByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public JSONObject doDelete(HttpServletRequest request, @RequestParam Long id) {
		JSONObject json = new JSONObject();
		try {
			if (null == id) {
				return Result.failure(json, "请指定需要删除的数据", "id_null");
			}
			userService.delete(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/doDelete", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(@Valid UserDto userDto, BindingResult br, Model model, HttpSession session) {
		JSONObject json = new JSONObject();
		try{
		if (br.hasErrors()) {
			initAddUser(model, 1);
			return Result.failure(json, "", "");
		}
		userDto.setCorpId((Integer) session.getAttribute("corpId"));
		userDto.setRank(0); // set rank to 0 is for user
		User user = userDto.getUser();
		
							// shall be replaced by zone selection
		userService.add(user, userDto.getConsoleIds(), userDto.getGroupIds(), null);
		json.put("userIds", user.getId());
		
		}catch (Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/add", json);
			return Result.failure(json, e.getMessage(), "");
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/addUserGroup", method = RequestMethod.POST)
	public JSONObject addUserGroup(HttpServletRequest request, HttpSession session,
				@RequestParam Long userId,@RequestParam String groupId) {
		JSONObject json = new JSONObject();
		try{
			List<Long> gids = JSONObject.parseArray(groupId, Long.class);
			for (Long long1 : gids) {
				System.out.println("long1:"+long1);
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			User user = userService.load(userId);
			//用户加入组
			userService.addUserGroups(user,gids);

		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delUserGroup", method = RequestMethod.POST)
	public JSONObject delUserGroup(HttpServletRequest request, HttpSession session,
			@RequestParam Long userId,@RequestParam String groupId) {
		JSONObject json = new JSONObject();
		try{
			Map<String, Object> map = ActionUtil.getAllParam(request);
			User user = userService.load(userId);
			List<Long> gids = JSONObject.parseArray(groupId, Long.class);
			//用户退出
			userService.delUserGroups(user,gids);
			
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	
	
	/**
	 * =========================================================================
	 * ================
	 */

	/**
	 * 获取用户分页列表集合信息
	 * 
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@RequestMapping("/users")
	public String listUser(Model model, HttpSession session) {
		// 获取用户总数
		// 获取用户分页列表集合信息
		Pager<UserDto> listUser;
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		Integer corp_id = (Integer) session.getAttribute("corpId");
		if (null == corp_id)
			corp_id = 1;// TODO 临时固定一个值，后期修改
		if (isAdmin == true) {
			listUser = userService.findUser();
		} else {
			listUser = userService.findCorpUser(corp_id);
		}
		
		// 保存用户分页列表集合信息
		model.addAttribute("datas", listUser);
		// Corporation user = (Corporation) session.getAttribute("loginUser");
		initAddUser(model, corp_id);
		return "user/userList";
	}

	/**
	 * 添加用户并跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpSession session) {
		Integer corpId = (Integer) session.getAttribute("corpId");
		// 通过UserDto操作
		model.addAttribute("userDto", new UserDto());
		List<User> consoles = userService.findCorpConsolesList(corpId, 1);
		model.addAttribute("consoles", consoles);
		Corporation user = (Corporation) session.getAttribute("loginUser");
		initAddUser(model, user.getId());
		return "user/add";
	}

	/**
	 * 初始化添加页面的角色和群组列表信息
	 * 
	 * @param model
	 */
	private void initAddUser(Model model, int corp_id) {
		// 获取群组列表信息
		List<Group> listGroup = groupService.listCorpGroup(corp_id);
		//TODO list转json数据
		
		// 存储群组列表信息
		model.addAttribute("groups", listGroup);
		List<User> listConsoles = userService.ListCorpConsoles(corp_id);
		model.addAttribute("console", listConsoles);
		// 获取调度台信息
		// ; List<Group> listGroup = groupService.listGroup();
	}

	/**
	 * 执行添加用户操作
	 * 
	 * @param userDto
	 * @param br
	 * @param model
	 * @return
	 */
/*	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid UserDto userDto, BindingResult br, Model model, HttpSession session) {
		if (br.hasErrors()) {
			initAddUser(model, 1);
			return "user/add";
		}

		userDto.setCorpId((Integer) session.getAttribute("corpId"));
		userDto.setRank(0); // set rank to 0 is for user
		User user = userDto.getUser();
		user.setZoneId(1); // it's hardcode here, when zone selection ready, it
							// shall be replaced by zone selection
		Long[] groupIds = {(long) 1,(long) 2,(long) 3};
		userDto.setGroupIds(groupIds);
		userService.add(user, userDto.getConsoleIds(), userDto.getGroupIds(), null);

		return "redirect:/admin/user/users";
	}*/

	/**
	 * 根据用户id更新用户信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model, @RequestParam(required = false) Long id) {
		
		// 根据用户id获取用户信息
		User u = userService.load(id);
		UserDto userDto = new UserDto(u, userService.listUserGroupIds(id));
		userDto.setConsoleIds(userService.listUserAmdinIds(u.getId()));
		model.addAttribute("userDto", userDto);

		initAddUser(model, u.getCorpId());
		Integer corpId = (Integer) session.getAttribute("corpId");
		if (null == corpId)
			corpId = 1;// TODO 临时固定一个值，后期修改
		List<User> consoles = userService.findCorpConsolesList(corpId, 1);

		model.addAttribute("consoles", consoles);
		return "user/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public JSONObject update(@PathVariable long id, @Valid UserDto userDto, BindingResult br,Model model,HttpSession session) {
//		Long id = Long.valueOf(ids);
		System.out.println("000000:"+id);
		JSONObject json = new JSONObject();
		try {
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			Corporation session_corporation = (Corporation) session.getAttribute("user");
			if (session_corporation.getId() != id && isAdmin == false) {// 数据所有者和管理员可修改数据
				Result.failure(json, "您没有权限执行此操作", "auth_failure");
			}
			if (br.hasErrors()) {
				String msg = "";
				List<ObjectError> errorList = br.getAllErrors();
				for (ObjectError error : errorList) {
					msg += error.getDefaultMessage();
				}
				return Result.failure(json, msg, "valid_failure");
			}
			User ou = userService.load(id);
			ou.setDisplayName(userDto.getNickname());
			ou.setPhone(userDto.getPhone());
			ou.setAdminId(userDto.getAdmin_id());
			ou.setPriority(userDto.getUserPriority());
			 //ou.setId(id);
			 // 更新用户和角色关联关系、用户和用户组关联关系
//			 Long[] gids=userDto.getGroupIds();
//			 for(int i=0; i<gids.length;i++)
//			 {
//				 System.out.print(gids[i]);
//			 }
			// update user group mapping, will do later
//			userService.update(ou, userDto.getUserIds(), userDto.getGroupIds(), userDto.getConsoleIds());
			userService.update(ou);
			// 更新用户基本信息
			// userService.update(ou);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/corp/update", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value="/updateToggleStatus/{id}", method=RequestMethod.POST)
	public JSONObject updateToggleStatus(@PathVariable Long id) {
		JSONObject json = new JSONObject();
		try {
			userService.updateStatus(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/user/updateToggleStatus", json);
		}
		return Result.success(json);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {

		userService.delete(id);

		return "redirect:/admin/user/users";
	}

	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable Long id) {
		userService.updateStatus(id);
		return "redirect:/admin/user/users";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute(userService.load(id));
		model.addAttribute("gs", userService.listGroupsByUid(id));
		// model.addAttribute("rs", userService.listRolesByUid(id));
		return "user/show";
	}

	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)

	public String updatePwd(Model model, HttpSession session, 
			@RequestParam(required=false) Long id) {
		User user = userService.load(id);
		model.addAttribute(user);
		return "user/updatePwd";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public JSONObject updatePwd(HttpSession session, Long id, String oldpwd, String password) {
		JSONObject json = new JSONObject();
		try {
			Corporation corporation = (Corporation) session.getAttribute("loginUser");
			//Integer corpId= (Integer)session.getAttribute("corpId");
			//if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(isAdmin != true && corporation.getId() != id){//非管理员只能修改自己的密码
				return Result.failure(json, "您没有权限执行此操作", "auth_failure");
			}
			userService.updatePwd(id, oldpwd, password);
		} catch (Exception e) {
			String msg = e.getMessage();
			json.put(BaseTip.key_msg, msg);
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/updatePwd", json);
		}
		return Result.success(json);
	}

	@RequestMapping(value = "/updateSelf", method = RequestMethod.GET)
	@AuthMethod
	public String updateSelf(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loginUser");
		model.addAttribute(new UserDto(u));
		Integer corpId = (Integer) session.getAttribute("corpId");
		List<User> consoles = userService.findCorpConsolesList(corpId, 1);

		model.addAttribute("consoles", consoles);
		return "user/updateSelf";
	}

	@RequestMapping(value = "/updateSelf", method = RequestMethod.POST)
	@AuthMethod
	public String updateSelf(@Valid UserDto userDto, BindingResult br, Model model, HttpSession session) {
		if (br.hasErrors()) {
			return "user/updateSelf";
		}
		User ou = userService.load(userDto.getId());
		ou.setDisplayName(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		userService.update(ou);
		session.setAttribute("loginUser", ou);
		return "redirect:/admin/user/showSelf";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpSession session) {
		Integer corpId = (Integer) session.getAttribute("corpId");
		if(null != corpId){
		Corporation user = (Corporation) session.getAttribute("loginUser");
		Integer uCount = userService.findCorpUserCount(corpId);
		Integer gCount = groupService.findCorpGroupCount(corpId);
		Integer cCount = userService.findCorpAllConsoleCount(corpId);
		session.setAttribute("uCount", uCount);
		session.setAttribute("gCount", gCount);
		session.setAttribute("cCount", cCount);
		session.setAttribute("user", user);
		// model.addAttribute("user",user);
		// model.addAttribute("uCount",uCount);
		}
		return "common/home";
	}

}
