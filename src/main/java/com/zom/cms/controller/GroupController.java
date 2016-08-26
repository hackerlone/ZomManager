package com.zom.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.directwebremoting.json.types.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.auth.AuthClass;
import com.zom.cms.dto.GroupDto;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserGroup;
import com.zom.cms.service.user.ICorporationService1;
import com.zom.cms.service.user.IGroupService;
import com.zom.cms.service.user.IUserService;

@RequestMapping("/admin/group")
@Controller
@AuthClass
public class GroupController {
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICorporationService1 corporationService1;
	
	
	private static Logger logger = Logger.getLogger("R");

	@ResponseBody
	@RequestMapping(value="/getGroupList", method=RequestMethod.POST)
	public JSONObject getGroupList(HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
			json = groupService.selectGroupDtoListByCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getGroupList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserGroupList", method=RequestMethod.POST)
	public JSONObject getUserGroupList(HttpServletRequest request, 
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
			System.out.println("usersId"+map.get("usersId"));
			json = groupService.selectGroupDtoListByUserCondition(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/getUserGroupList", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/addGroupUser", method = RequestMethod.POST)
	public JSONObject addGroupUser(HttpServletRequest request, HttpSession session,
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
			groupService.addGroupUsers(groupId, userIds, group.getOwnerId());
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delGroupUser", method = RequestMethod.POST)
	public JSONObject delGroupUser(HttpServletRequest request, HttpSession session,
			@RequestParam String userId,@RequestParam Long groupId) {
		JSONObject json = new JSONObject();
		try{
			List<Long> userIds = JSONObject.parseArray(userId, Long.class);
			for (Long long1 : userIds) {
				System.out.println("long1:"+long1);
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Group group = groupService.load(groupId);
			//用户加入组
			groupService.delGroupUsers(groupId, userIds, group.getOwnerId());
			
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/users", json);
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
			List<UserGroup> users = userService.listGroupUsers(id);
			if(null !=users && users.size()>0) {
				Result.failure(json, "删除的组中还有用户，不能删除!", "");
			}
			 int count = groupService.delete(id);
			 System.out.println(count);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/doDelete", json);
			Result.failure(json, "删除失败", "");
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(@Validated GroupDto groupDto,BindingResult br,HttpSession session) {
		JSONObject json = new JSONObject();
		try{
		Integer mcId= (Integer)session.getAttribute("mcId");
		Integer corpId= (Integer)session.getAttribute("corpId");
		if(br.hasErrors()) {
			return Result.failure(json, "", "");
		}
		//添加群组
		groupDto.setCorpId(mcId);
		groupDto.setDcg(0); //set it to normal user group
		groupDto.setZoneId(1); //it's hardcode here, when zone selection ready, it shall be replaced by zone selection
		groupService.add(groupDto);
		json.put("groupIds", groupDto.getId());
		}catch(Exception e){
			Result.catchError(e, logger, this.getClass().getName() + "/admin/user/add", json);
		}
		return Result.success(json);
	}
	
	/**=========================================================================================*/


	/**
	 * 获取群组列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/groups")
	public String list(Model model,HttpSession session) {
		/*Integer corpId= (Integer)session.getAttribute("corpId");
		Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
		Pager<GroupDto> datas;//获取分页群组列表信息
		if(isAdmin!=true){
			datas = groupService.findGroupCorp(corpId);
		}else{
			datas = groupService.findGroup();
			List<Corporation> corp=(corporationService1.findUser()).getDatas();
			model.addAttribute("corps",corp);
		}
		model.addAttribute("datas",datas);*/
		return "group/groupList";
	}

	/**
	 * 获取公司下的群组列表
	 * @param model
	 * @return
	 */
	/*
	@RequestMapping(value="/groups/{id}",method=RequestMethod.GET)
	public String listGroupCorp(@PathVariable int id, Model model) {
		//获取分页群组列表信息
		
		Pager<GroupDto> datas = groupService.findGroupCorp(id);
		model.addAttribute("datas",datas);
		return "group/list";
	}	
	*/
	/**
	 * 跳转到添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model,HttpSession session) {
		model.addAttribute("groupDto",new GroupDto());
		Integer corpId= (Integer)session.getAttribute("corpId");
		List <User> consoles=userService.ListCorpConsoles(corpId);
		List <User> users=userService.findCorpAllUser(corpId);
		ArrayList list = new ArrayList();
	
	//	User u=new User();
	//	users.set(0, u);
	//	users.add(0,u);
	//	List <UserDto> users=(userService.findCorpUser(corpId)).getDatas();
		model.addAttribute("consoles",consoles);
		model.addAttribute("users",users);
		return "group/add";
	}
	
	/**
	 * 添加群组
	 * @param group
	 * @param br
	 * @return
	 */
	/*@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated GroupDto groupDto,BindingResult br,HttpSession session) {
		Integer mcId= (Integer)session.getAttribute("mcId");
		Integer corpId= (Integer)session.getAttribute("corpId");

       
		if(br.hasErrors()) {
			return "group/add";
		}
		//添加群组
		groupDto.setCorpId(mcId);
		groupDto.setDcg(0); //set it to normal user group
		groupDto.setZoneId(1); //it's hardcode here, when zone selection ready, it shall be replaced by zone selection
		groupService.add(groupDto);
		
		return "redirect:/admin/group/groups";
	}*/
	

	@ResponseBody
	@RequestMapping(value="/updateToggleStatus/{id}", method=RequestMethod.POST)
	public JSONObject updateToggleStatus(@PathVariable Long id) {
		JSONObject json = new JSONObject();
		try {
			groupService.updateStatus(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/group/updateToggleStatus", json);
		}
		return Result.success(json);
	}
	
	
	/**
	 * 跳转到更新界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(HttpSession session, Model model, @RequestParam(required = false) Long id) {
		Group group=groupService.load(id);
		List <Long> userIds = groupService.listGroupUserId(id);
		GroupDto groupDto = new GroupDto(group,userIds);
		String ownerName = userService.load(group.getOwnerId()).getDisplayName();
		groupDto.setOwnerName(ownerName);
		model.addAttribute("groupDto",groupDto);

		
		Integer mcId= (Integer)session.getAttribute("mcId");
		Integer corpId= (Integer)session.getAttribute("corpId");
		if (null == corpId)
			corpId = 1;// TODO 临时固定一个值，后期修改
		List <User> users=userService.findCorpAllUser(corpId);
	//	model.addAttribute(group);

		model.addAttribute("users",users);
		//User admin=userService.load(group.getAdminId());
	//	model.addAttribute("admin",admin);
		/*
		List<Corporation> corp=(corporationService1.findUser()).getDatas();
		model.addAttribute("corps",corp);
		*/
//		List <User> consoles=userService.ListCorpConsoles(group.getCorpId());

		
//		model.addAttribute("consoles",consoles);
		return "group/update";
	}
	
	/**
	 * 更新群组信息
	 * @param id
	 * @param group
	 * @param br
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public JSONObject update(@PathVariable Long id,@Valid GroupDto groupDto,BindingResult br) {
		JSONObject json = new JSONObject();
	
		//根据群组id获取群组信息
		Group ug = groupService.load(id);
        Group group=groupDto.getUser();
	//	ug.setDescr(group.getDescr());
	 	ug.setGroupName(group.getGroupName());

		//更新群组信息
		groupService.update(ug,groupDto.getUserIds());
//
		return Result.success(json);
	}
	
	/**
	 * 删除群组信息(在删除之前先判断群组中是否有用户信息)
	 * @param id 群组id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		//删除群组信息(在删除之前先判断群组中是否有用户信息)
		groupService.delete(id);
		return "redirect:/admin/group/groups";
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable Long id,Model model) {
		Group group=groupService.load(id);
		model.addAttribute(group);
		model.addAttribute("us", userService.listUsersByGid(id));
	//	User admin=userService.load(group.getAdminId());
	//	model.addAttribute("admin",admin);
		User owner=userService.load(group.getOwnerId());
		model.addAttribute("owner",owner);
		return "group/show";
	}
	
	/**
	 * 清除群组中的用户
	 * @param id 群组id
	 * @return
	 */
	@RequestMapping("/clearUsers/{id}")
	public String clearGroupUsers(@PathVariable Long id) {
		groupService.deleteGroupUsers(id);
		return "redirect:/admin/group/groups";
	}
	

	

	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable Long id) {
		groupService.updateStatus(id);
		return "redirect:/admin/group/groups";
	}
}
