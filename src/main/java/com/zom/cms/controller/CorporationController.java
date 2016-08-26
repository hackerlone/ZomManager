package com.zom.cms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zom.cms.auth.AuthClass;
import com.zom.cms.auth.AuthMethod;
import com.zom.cms.lh.config.BaseTip;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.Corporation;
import com.zom.cms.model.ZoneIdAssign;
import com.zom.cms.service.user.ICorpZoneService;
import com.zom.cms.service.user.ICorporationService1;
import com.zom.cms.service.user.IUserService;

@Controller
@RequestMapping("/admin/corp")
@AuthClass("login")
public class CorporationController {
	@Autowired
	private IUserService userService;
	/*@Autowired
	private IGroupService groupService;*/
	@Autowired
	private ICorporationService1 corporationService;
	@Autowired
	private ICorpZoneService corpZoneService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 获取用户分页列表集合信息
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@RequestMapping("/users")
	public ModelAndView listUser(ModelMap modelMap) {
		//Pager<Corporation> listUser = corporationService.findUser();// 获取用户分页列表集合信息
		//modelMap.put("datas", listUser);// 保存用户分页列表集合信息
		
		List<ZoneIdAssign> zoneAssign = corporationService.selectAllAssign();
		modelMap.put("zoneAssign", zoneAssign);
		return new ModelAndView("corp/corpList", modelMap);
	}
	
	@RequestMapping("/userAddOrUpate")
	public ModelAndView userAddOrUpate(ModelMap modelMap, @RequestParam Integer id) {
		Corporation corp = corporationService.load(id);
		modelMap.put("corp", corp);
		return new ModelAndView("corp/corpAddOrUpdate", modelMap);
	}

	@ResponseBody
	@RequestMapping(value="/getCorporationList", method=RequestMethod.POST)
	public JSONObject getCorporationList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
//			map.put("zone", "1");
			List<Corporation> dataList = corporationService.selectListByCondition(map);
			int total = corporationService.selectCountByCondition(map);
			Result.gridData(dataList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/getCorporationList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method=RequestMethod.POST)
	public JSONObject doDelete(HttpServletRequest request, @RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			if(null == id){
				return Result.failure(json, "请指定需要删除的数据", "id_null");
			}
			corporationService.delete(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/doDelete", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value="/updateToggleStatus/{id}", method=RequestMethod.POST)
	public JSONObject updateToggleStatus(@PathVariable int id) {
		JSONObject json = new JSONObject();
		try {
			corporationService.updateStatus(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/updateToggleStatus", json);
		}
		return Result.success(json);
	}
	
	@AuthMethod
	@RequestMapping(value = "/pwdUpdate", method = RequestMethod.GET)
	public String updatePwd(Model model, HttpSession session, 
			@RequestParam(required=false) Integer id) {
		//Corporation u = (Corporation) session.getAttribute("loginUser");
		//session.removeAttribute("loginUser");
		//session.setAttribute("loginUser", u);
		//model.addAttribute("loginUser",u);
		//model.addAttribute(u);
		if(null == id){
			Corporation corporation = (Corporation) session.getAttribute("user");
			id = corporation.getId();
		}
		Corporation corp = corporationService.load(id);
		if(null != corp){
			model.addAttribute(corp);
		}
		return "corp/pwdUpdate";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public JSONObject updatePwd(HttpSession session, int id, String oldpwd, String password) {
		JSONObject json = new JSONObject();
		try {	
			Corporation corporation = (Corporation) session.getAttribute("loginUser");
			//Integer corpId= (Integer)session.getAttribute("corpId");
			//if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(isAdmin != true && corporation.getId() != id){//非管理员只能修改自己的密码
				return Result.failure(json, "您没有权限执行此操作", "auth_failure");
			}
			corporationService.updatePwd(id, oldpwd, password);
		} catch (Exception e) {
			String msg = e.getMessage();
			json.put(BaseTip.key_msg, msg);
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/updatePwd", json);
		}
		return Result.success(json);
	}
	
	/**
	 * 根据用户id更新用户信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model,ModelMap modelMap,
			@RequestParam(required=false) Integer id) {
		try {
			// 根据用户id获取用户信息
			if(null == id){
				Corporation corporation = (Corporation) session.getAttribute("user");
				id = corporation.getId();
			}
			Corporation corp = corporationService.load(id);
		
			if(null != corp){
				model.addAttribute(corp);
			}
			List<ZoneIdAssign> zoneAssign = corporationService.selectAllAssign();
			modelMap.put("zoneAssign", zoneAssign);
		} catch (Exception e) {
			e.printStackTrace();
			//Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/update", json);
		}
		return "corp/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public JSONObject update(Model model,HttpSession session, 
			@PathVariable int id, @Valid  Corporation corp, BindingResult br) {
		JSONObject json = new JSONObject();
		try {
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			Corporation session_corporation = (Corporation) session.getAttribute("user");
			if(session_corporation.getId() != id && isAdmin == false){//数据所有者和管理员可修改数据
				Result.failure(json, "您没有权限执行此操作", "auth_failure");
			}
			if (br.hasErrors()) {
				String msg = "";
				List<ObjectError> errorList = br.getAllErrors();
	            for(ObjectError error : errorList){
	            	msg += error.getDefaultMessage();
	            }
				return Result.failure(json, msg, "valid_failure");
			}
			Corporation corporation = corporationService.load(id);
			corporation.setCorpName(corp.getCorpName());		
			corporation.setPhone(corp.getPhone());
			corporation.setEmail(corp.getEmail());
			corporation.setMaxUser(corp.getMaxUser());
			corporation.setMaxGroup(corp.getMaxGroup());
			corporation.setMaxConsole(corp.getMaxConsole());
			corporation.setMaxUserGroup(corp.getMaxUserGroup());
			corporationService.update(corporation);// 更新用户基本信息
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/update", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value = "/freshCurrentCorporation/{id}", method = RequestMethod.POST)
	public JSONObject update(HttpSession session, @PathVariable int id) {
		JSONObject json = new JSONObject();
		try {
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(isAdmin == true){//非管理员只能查看自己的数据
				session.setAttribute("corpId", id);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/update", json);
		}
		return Result.success(json);
	}
	
	@AuthMethod
	@RequestMapping("/showSelf")
	public String showSelf(Model model, HttpSession session) {
		if(!model.containsAttribute("loginUser")){
			Corporation corporation = (Corporation) session.getAttribute("loginUser");
			model.addAttribute(corporation);
		}
		return "corp/show";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(HttpServletRequest request, 
			@Valid Corporation corporation, BindingResult br, Model model) {
		JSONObject json = new JSONObject();
		if (br.hasErrors()) { 
//			return "corp/add";
		}
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			corporationService.add(corporation,180);
			Integer corpId = corporation.getId();
			Corporation corporation2 = corporationService.loadByPhone(corporation.getPhone());
			
			List<Integer> zoneIds = JSONObject.parseArray(corporation.getZoneId(), Integer.class);
			for (Integer zoneId : zoneIds) {
				map.clear();
				map.put("corpId", corpId);
				map.put("zoneId", zoneId);
				corpZoneService.addCorpZone(map);
			}
		}catch(Exception e){
			return Result.failure(json, e.getMessage(), "");
		}
		return Result.success(json,"添加公司信息成功!");
	}
	
	/**=========================================================================================*/

	/**
	 * 添加用户并跳转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,ModelMap modelMap) {
		// 通过UserDto操作
		List<ZoneIdAssign> zoneAssign = corporationService.selectAllAssign();
		modelMap.put("zoneAssign", zoneAssign);
		model.addAttribute("corp", new Corporation());
		return "corp/add";
	}


	/**
	 * 执行添加用户操作
	 * @param userDto
	 * @param br
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid Corporation corporation, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "corp/add";
		}
		
		corporationService.add(corporation,180);
		return "redirect:/admin/corp/users";
	}*/

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		corporationService.delete(id);
		return "redirect:/admin/corp/users";
	}

	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable  int id) {
		corporationService.updateStatus(id);
		return "redirect:/admin/corp/users";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		model.addAttribute(corporationService.load(id));
		return "corp/show";
	}

	/*@RequestMapping(value = "/updateSelf", method = RequestMethod.GET)
	@AuthMethod
	public String updateSelf(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loginUser");
		model.addAttribute(new UserDto(u));
		return "corp/updateSelf";
	}

	@RequestMapping(value = "/updateSelf", method = RequestMethod.POST)
	@AuthMethod
	public String updateSelf(@Valid UserDto userDto, BindingResult br,
			Model model, HttpSession session) {
		if (br.hasErrors()) {
			return "corp/updateSelf";
		}
		User ou = userService.load(userDto.getId());
	//	ou.setNickname(userDto.getNickname());
	//	ou.setPhone(userDto.getPhone());
	//	ou.setEmail(userDto.getEmail());
		userService.update(ou);
		session.setAttribute("loginUser", ou);
		return "redirect:/admin/corp/showSelf";
	}*/

	@RequestMapping("/home")
	public String home(Model model) {
		return "common/home";
	}
	
	

	
}
