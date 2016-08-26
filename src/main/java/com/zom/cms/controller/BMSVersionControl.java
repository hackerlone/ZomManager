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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.BmsVer;
import com.zom.cms.model.Corporation;
import com.zom.cms.service.user.IVersionService;

@Controller
@RequestMapping("/admin/version")
// @AuthClass("login")
public class BMSVersionControl {
	@Autowired
	private IVersionService verService;

	private static Logger logger = Logger.getLogger("R");

	@ResponseBody
	@RequestMapping(value = "/getVersionList", method = RequestMethod.POST)
	public JSONObject getVersionList(HttpServletRequest request,HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer corpId= (Integer)session.getAttribute("corpId");
			Boolean isAdmin= (Boolean)session.getAttribute("isAdmin");
			if(null == corpId)corpId = 1;//TODO 临时固定一个值，后期修改
			if(isAdmin != true){
				map.put("corpId", corpId);
			}
			
			List<BmsVer> dataList = verService.selectListByCondition(map);
			int total = verService.selectCountByCondition(map);
			Result.gridData(dataList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/version/getVersionList", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public JSONObject doDelete(HttpServletRequest request, @RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			if (null == id) {
				return Result.failure(json, "请指定需要删除的数据", "id_null");
			}
			verService.delete(id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/version/doDelete", json);
		}
		return Result.success(json);
	}

	/**
	 * ==============================================================================================
	 */

	/**
	 * 获取用户分页列表集合信息
	 * 
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@RequestMapping("/list")
	public ModelAndView listVersions(ModelMap modelMap, HttpSession session) {
		//Pager<BmsVer> bmsVerList = verService.findBmsVer();// 保存用户分页列表集合信息
		//model.addAttribute("datas", bmsVerList);
		 return new ModelAndView("bmsversion/bmsVerList", modelMap);
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
		model.addAttribute("isAdd", "add");
		// 通过UserDto操作
		model.addAttribute("bmsVer", new BmsVer());

		return "bmsversion/add";
	}

	/**
	 * 执行添加用户操作
	 * 
	 * @param userDto
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid BmsVer bmsVer, BindingResult br, Model model, HttpSession session) {
		if (br.hasErrors()) {
			// initAddUser(model,1);
			return "bmsversion/add";
		}

		verService.add(bmsVer);
		return "redirect:/admin/version/list";
	}

	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model, 
			@RequestParam(required=false) Integer id) {
		// 根据用户id获取用户信息
		BmsVer u = verService.load(id);
		model.addAttribute("bmsVer", u);
		return "bmsversion/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public JSONObject update(Model model,HttpSession session, 
			@PathVariable int id, @Valid BmsVer bv, BindingResult br) {
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
			BmsVer bvv = verService.load(id);
			bvv.setClientDescription(bv.getClientDescription());
			bvv.setClientOsType(bv.getClientOsType());
			bvv.setClientUrl(bv.getClientUrl());
			bvv.setClientVersion(bv.getClientVersion());
			bvv.setLatestClient(bv.getLatestClient());
			bvv.setServerVersion(bv.getServerVersion());
			bvv.setLatestServer(bv.getLatestServer());
			verService.update(bvv);
			return Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/admin/corp/update", json);
		}
		return Result.success(json);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		verService.delete(id);
		return "redirect:/admin/version/list";
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		BmsVer u = verService.load(id);
		model.addAttribute("bmsVer", u);
		return "bmsversion/show";
	}

}
