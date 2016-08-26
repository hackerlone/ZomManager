package com.zom.cms.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.auth.AuthMethod;
import com.zom.cms.dto.UserDto;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.Corporation;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.page.Pager;
import com.zom.cms.service.user.ConsolerUserService;
import com.zom.cms.service.user.IConsolerUserService;
import com.zom.cms.service.user.IGroupService;
import com.zom.cms.service.user.IUserService;

@Controller
@RequestMapping("/admin/console")
//@AuthClass("login")
public class ConsoleController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IConsolerUserService consolerUserService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/getConsoleList", method=RequestMethod.POST)
	public JSONObject getGroupList(HttpServletRequest request,HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
			map.put("rank",1);
			json = userService.findConsoles(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/console/getConsoleList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getConsoleList2", method=RequestMethod.POST)
	public JSONObject getGroupList2(HttpServletRequest request,HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
			map.put("rank",2);
			json = userService.findConsoles(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/console/getConsoleList2", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method=RequestMethod.POST)
	public JSONObject doDelete(HttpServletRequest request, @RequestParam Long id) {
		JSONObject json = new JSONObject();
		try {
			if(null == id){
				return Result.failure(json, "请指定需要删除的数据", "id_null");
			}
			userService.delete(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/doDelete", json);
		}
		return Result.success(json);
	}
	//执行添加一阶控制台
	@ResponseBody
	@RequestMapping(value = "/addrank1", method = RequestMethod.POST)
	public JSONObject add(@Valid UserDto userDto, BindingResult br, Model model,HttpSession session) {
		JSONObject json = new JSONObject();
		try{
		if (br.hasErrors()) {
			initAddUser(model,1);
			return Result.failure(json, "", "");
		}
		userDto.setCorpId((Integer)session.getAttribute("corpId"));
		User user=userDto.getUser();
		user.setPriority(userDto.getPriority());  //here is for console priority
		user.setRank(1);  //rank 1
		userService.add(user, userDto.getConsoleIds(),
				userDto.getGroupIds(),userDto.getUserIds());
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/add", json);
		}
		return Result.success(json);
	}
	//执行添加二阶控制台
	@ResponseBody
	@RequestMapping(value = "/addrank2", method = RequestMethod.POST)
	public JSONObject add2(@Valid UserDto userDto, BindingResult br, Model model,HttpSession session) {
		JSONObject json = new JSONObject();
		try{
		if (br.hasErrors()) {
			initAddUser(model,1);
			return Result.failure(json, "", "");
		}
		userDto.setCorpId((Integer)session.getAttribute("corpId"));
		User user=userDto.getUser();
		user.setRank(2);  //rank 2
		user.setPriority(userDto.getPriority());  //here is for console priority
		
		userService.add(user, userDto.getConsoleIds(),
				userDto.getGroupIds(),userDto.getUserIds());
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/add", json);
		}
		return Result.success(json);
	}
	
	
	
	/**=========================================================================================*/
	
	@ResponseBody
	@RequestMapping(value = "/addConsolerUser", method = RequestMethod.POST)
	public JSONObject addConsolerUser(HttpServletRequest request, HttpSession session,
				@RequestParam String userId,@RequestParam Long groupId) {
		JSONObject json = new JSONObject();
		try{
			System.out.println(userId);
			List<Long> userIds = JSONObject.parseArray(userId, Long.class);
			for (Long long1 : userIds) {
				System.out.println("long1:"+long1);
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Group group = groupService.load(groupId);
			//用户加入组
//			groupService.addGroupUsers(groupId, userIds, group.getOwnerId());
			userService.addConsolerUsers(groupId, userIds);
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delConsolerUser", method = RequestMethod.POST)
	public JSONObject delConsolerUser(HttpServletRequest request, HttpSession session,
			@RequestParam String userId,@RequestParam Long groupId) {
		JSONObject json = new JSONObject();
		try{
			List<Long> userIds = JSONObject.parseArray(userId, Long.class);
		
			Map<String, Object> map = ActionUtil.getAllParam(request);
			//用户加入组
			userService.delConsolerUsers(groupId, userIds);
			
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/addConsoler1To2", method = RequestMethod.POST)
	public JSONObject addConsoler1To2(HttpServletRequest request, HttpSession session,
				@RequestParam String logonName,@RequestParam String adminIds) {
		JSONObject json = new JSONObject();
		try{
			System.out.println(logonName);
			List<Long> adminIdss = JSONObject.parseArray(adminIds, Long.class);
			for (Long long1 : adminIdss) {
				System.out.println("long1:"+long1);
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			//一阶控制台加入二阶控制台
			consolerUserService.addConsoler1To2(logonName,adminIdss);
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delConsoler1To2", method = RequestMethod.POST)
	public JSONObject delConsoler1To2(HttpServletRequest request, HttpSession session,
			@RequestParam String logonName,@RequestParam String adminIds) {
		Integer corpId= (Integer)session.getAttribute("corpId");
		if (null == corpId)
			corpId = 1;// TODO 临时固定一个值，后期修改
		JSONObject json = new JSONObject();
		try{
			System.out.println(logonName);
			List<Long> adminIdss = JSONObject.parseArray(adminIds, Long.class);
			for (Long long1 : adminIdss) {
				System.out.println("long1:"+long1);
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			//一阶控制台退出二阶控制台
			consolerUserService.delConsoler1To2(logonName,adminIdss);
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/addConsoler2To1", method = RequestMethod.POST)
	public JSONObject addConsoler2To1(HttpServletRequest request, HttpSession session,
			@RequestParam Long adminId,@RequestParam String userNamess) {
		Integer corpId= (Integer)session.getAttribute("corpId");
		if (null == corpId)
			corpId = 1;// TODO 临时固定一个值，后期修改
		JSONObject json = new JSONObject();
		try{
			List<String> userNames = JSONObject.parseArray(userNamess, String.class);
			
			Map<String, Object> map = ActionUtil.getAllParam(request);
			//一阶控制台加入二阶控制台
			consolerUserService.addConsoler2To1(adminId,userNames);
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delConsoler2To1", method = RequestMethod.POST)
	public JSONObject delConsoler2To1(HttpServletRequest request, HttpSession session,
			@RequestParam Long adminId,@RequestParam String userNamess) {
		JSONObject json = new JSONObject();
		try{
			List<String> userNames = JSONObject.parseArray(userNamess, String.class);
			
			Map<String, Object> map = ActionUtil.getAllParam(request);
			//一阶控制台加入二阶控制台
			consolerUserService.delConsoler2To1(adminId,userNames);
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	
	
	
	
	/**
	 * 获取用户分页列表集合信息
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@RequestMapping("/level1users")
	public String listUser(Model model, HttpSession session) {	
		//获取用户总数
		// 获取用户分页列表集合信息
		Pager<UserDto> listUser;
		int rank =1;
		Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		Integer corp_id= (Integer)session.getAttribute("corpId");
		if (null == corp_id)
			corp_id = 1;// TODO 临时固定一个值，后期修改
	  if(isAdmin==true){
		  listUser=userService.findConsoles(rank);
	  }else {
		  listUser=userService.findCorpConsoles(corp_id,rank);
	  }

		// 保存用户分页列表集合信息
		model.addAttribute("datas", listUser);
		boolean isRank1=true;
		model.addAttribute("isRank1", isRank1);
		initAddUser(model,corp_id);

		return "console/consoleList";
	}
	
	@RequestMapping("/level2users")
	public String listRank2User(Model model, HttpSession session) {
		
		//获取用户总数

		// 获取用户分页列表集合信息
		Pager<UserDto> listUser;
		int rank =2;
		Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		Integer corp_id= (Integer)session.getAttribute("corpId");
		if (null == corp_id)
			corp_id = 1;// TODO 临时固定一个值，后期修改
	  if(isAdmin==true){
		  listUser=userService.findConsoles(rank);
	  }else {
		  listUser=userService.findCorpConsoles(corp_id,rank);
	  }

		// 保存用户分页列表集合信息
		model.addAttribute("datas", listUser);
		boolean isRank1=false;
		model.addAttribute("isRank1", isRank1);
		initAddUser(model,corp_id);
		return "console/consoleList";
	}

	/**
	 * 添加用户并跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addrank1", method = RequestMethod.GET)
	public String add(Model model, HttpSession session) {
		// 通过UserDto操作
		Integer corpId= (Integer)session.getAttribute("corpId");
		model.addAttribute("userDto", new UserDto());
		Corporation user = (Corporation) session.getAttribute("loginUser");
		List <User> users=userService.findCorpAllUser(corpId);
		
        
		model.addAttribute("users",users);
		
		List <User> consoles = userService.findCorpConsolesList(corpId, 2);
		model.addAttribute("consoles", consoles);

		initAddUser(model,user.getId());
		boolean isRank1=true;
		model.addAttribute("isRank1", isRank1);

		return "console/add";
	}
	
	@RequestMapping(value = "/addrank2", method = RequestMethod.GET)
	public String add2(Model model, HttpSession session) {
		// 通过UserDto操作
		Integer corpId= (Integer)session.getAttribute("corpId");
		model.addAttribute("userDto", new UserDto());
		Corporation user = (Corporation) session.getAttribute("loginUser");
		List <User> users=userService.findCorpAllUser(corpId);
		
        
		model.addAttribute("users",users);
		List <User> consoles = userService.findCorpConsolesList(corpId, 1);
		model.addAttribute("consoles", consoles);

		initAddUser(model,user.getId());
		boolean isRank1=false;
		model.addAttribute("isRank1", isRank1);
		return "console/add";
	}

	/**
	 * 初始化添加页面的角色和群组列表信息
	 * 
	 * @param model
	 */
	private void initAddUser(Model model,int corp_id) {
		// 获取群组列表信息
		List<Group> listGroup = groupService.listCorpGroup(corp_id);
		// 存储群组列表信息
		model.addAttribute("groups", listGroup);
		
	//	List<User> listConsoles = userService.ListCorpConsoles(corp_id);
		//model.addAttribute("console", listConsoles);
		// 获取调度台信息
	//;	List<Group> listGroup = groupService.listGroup();
	}

	/**
	 * 执行添加用户操作
	 * 
	 * @param userDto
	 * @param br
	 * @param model
	 * @return
	 */
/*	@RequestMapping(value = "/addrank1", method = RequestMethod.POST)
	public String add(@Valid UserDto userDto, BindingResult br, Model model,HttpSession session) {
		if (br.hasErrors()) {
			initAddUser(model,1);
			return "console/add";
		}

		userDto.setCorpId((Integer)session.getAttribute("corpId"));
		User user=userDto.getUser();
		user.setPriority(userDto.getPriority());  //here is for console priority
		user.setRank(1);  //rank 1
		userService.add(user, userDto.getConsoleIds(),
				userDto.getGroupIds(),userDto.getUserIds());
		return "redirect:/admin/console/level1users";
	}
	
	@RequestMapping(value = "/addrank2", method = RequestMethod.POST)
	public String add2(@Valid UserDto userDto, BindingResult br, Model model,HttpSession session) {
		if (br.hasErrors()) {
			initAddUser(model,1);
			return "console/add";
		}

		userDto.setCorpId((Integer)session.getAttribute("corpId"));
		User user=userDto.getUser();
		user.setRank(2);  //rank 2
		user.setPriority(userDto.getPriority());  //here is for console priority
		
		userService.add(user, userDto.getConsoleIds(),
				userDto.getGroupIds(),userDto.getUserIds());
		return "redirect:/admin/console/level2users";
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
		UserDto userDto= new UserDto(u, userService.listUserGroupIds(id));
		userDto.setPriority(u.getPriority());
		userDto.setConsoleIds(userService.listUserAmdinIds(id));
		List <Long> uIdsList=userService.listUsersIdByConsoleId(id);
		Integer rank = u.getRank();
		boolean isRank1=true;
        if(rank==1){
    		Integer corpId= (Integer)session.getAttribute("corpId");
    		if (null == corpId)
    			corpId = 1;// TODO 临时固定一个值，后期修改
    		List <User> users=userService.findCorpAllUser(corpId);
    		model.addAttribute("users",users);
    		List <User> consoles = userService.findCorpConsolesList(corpId, 2);
    		
    		model.addAttribute("consoles",consoles);
    		
    	
    		//update the 二阶主管台
        }else{
        	isRank1=false;
        	Integer corpId= (Integer)session.getAttribute("corpId");
    		if (null == corpId)
    			corpId = 1;// TODO 临时固定一个值，后期修改
    		List <User> users=userService.findCorpConsolesList(corpId, 1);
    		model.addAttribute("users",users);
        }
		
        model.addAttribute("isRank1", isRank1);
        model.addAttribute("logonName", u.getLogonName());
        model.addAttribute("userId",id);

		userDto.setUserIds(uIdsList);
		model.addAttribute(
				"userDto",
				userDto
				);			
		initAddUser(model,u.getCorpId());
		return "console/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public JSONObject update(@PathVariable long id, @Valid UserDto userDto, BindingResult br,Model model,HttpSession session) {
		JSONObject json = new JSONObject();
		if (br.hasErrors()) {
			System.out.println(br.hasErrors());
	
		}
		User ou = userService.load(id);
		Integer rank = ou.getRank();
		ou.setDisplayName(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
//		System.out.printf("adminId=%d\n", userDto.getAdmin_id());
		ou.setAdminId(userDto.getAdmin_id());
		ou.setPriority(userDto.getPriority());

		
	//	model.addAttribute(group);

		//update user group mapping, will do later
		userService.update(ou, userDto.getUserIds(), userDto.getGroupIds(),userDto.getConsoleIds());
		// 更新用户基本信息
     	//userService.update(ou);
	/*	if(rank==1){
			return "redirect:/admin/console/level1users";
		}else{
			return "redirect:/admin/console/level2users";
		}
		*/
		return Result.success(json);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id,HttpSession session) {
		User u=userService.load(id);
		//userService.deleteAdminUsers(id);
		userService.delete(id);
		
		if(u.getRank()==1){
			return "redirect:/admin/console/level1users";
		}else{
			return "redirect:/admin/console/level2users";
		}
		
	}
	/*@ResponseBody
	@RequestMapping(value="/updateToggleStatus/{id}", method=RequestMethod.POST)
	public JSONObject updateToggleStatus(@PathVariable Long id) {
		JSONObject json = new JSONObject();
		try {
			userService.updateStatus(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/user/updateToggleStatus", json);
		}
		return Result.success(json);
	}*/
	@ResponseBody
	@RequestMapping(value="/updateToggleStatus/{id}", method=RequestMethod.POST)
	public JSONObject updateToggleStatus(@PathVariable Long id) {
		JSONObject json = new JSONObject();
		try {
			userService.updateStatus(id);
			User u=userService.load(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/user/updateToggleStatus", json);
		}
		return Result.success(json);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		User user= userService.load(id);
		model.addAttribute(user);
		Integer rank = user.getRank();
		boolean isRank1=true;
        if(rank==2){
        	isRank1=false;
    		
        }
        model.addAttribute("isRank1", isRank1);
		List<String> adminNames= userService.listAdminNames(user.getId());
		model.addAttribute("adminNames", adminNames);
		
	    List <User> users= userService.listUsersByConsoleId(id);
		model.addAttribute("users", users);
	//	model.addAttribute("rs", userService.listRolesByUid(id));
		return "console/show";
	}

	@RequestMapping("/showSelf")
	@AuthMethod
	public String showSelf(Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute(user);
		Integer rank = user.getRank();
		boolean isRank1=true;
        if(rank==2){
        	isRank1=false;
    		
        }
        model.addAttribute("isRank1", isRank1);
		List<String> adminNames= userService.listAdminNames(user.getId());
		model.addAttribute("adminNames", adminNames);
		model.addAttribute("gs", userService.listGroupsByUid(user.getId()));
		
		return "console/show";
	}

	@RequestMapping(value = "/updatePwd/{id}", method = RequestMethod.GET)

	public String updatePwd(@PathVariable Long id,Model model, HttpSession session) {
		User u = userService.load(id);
		model.addAttribute(u);
		return "console/updatePwd";
	}

	@RequestMapping(value = "/updatePwd/{id}", method = RequestMethod.POST)

	public String updatePwd(Long id, String oldPwd, String password) {
		userService.updatePwd(id, oldPwd, password);
		User u=userService.load(id);
		if(u.getRank()==1){
			return "redirect:/admin/console/level1users";
		}else{
			return "redirect:/admin/console/level2users";
		}
	}

	@RequestMapping(value = "/updateSelf", method = RequestMethod.GET)
	@AuthMethod
	public String updateSelf(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loginUser");
		model.addAttribute(new UserDto(u));
		return "console/updateSelf";
	}

	@RequestMapping(value = "/updateSelf", method = RequestMethod.POST)
	@AuthMethod
	public String updateSelf(@Valid UserDto userDto, BindingResult br,
			Model model, HttpSession session) {
		if (br.hasErrors()) {
			return "console/updateSelf";
		}
		User ou = userService.load(userDto.getId());
		ou.setDisplayName(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		userService.update(ou);
		session.setAttribute("loginUser", ou);
		return "redirect:/admin/console/showSelf";
	}



	@RequestMapping("/home")
	public String home(Model model) {
		return "common/home";
	}

}
