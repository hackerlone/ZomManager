package com.zom.cms.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.basic.util.Captcha;
import com.zom.cms.basic.util.SecurityUtil;
import com.zom.cms.lh.tools.CommonGenerator;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.Corporation;
//import com.zom.cms.service.user.ICorporationService;
import com.zom.cms.service.user.ICorporationService1;
import com.zom.cms.web.CmsSessionContext;

@Controller
public class LoginController {
	@Autowired
	private ICorporationService1 corporationService;

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap modelMap, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("cmsPath", request.getContextPath());
		modelMap.put("paramJson", json);
		return new ModelAndView("admin/login", modelMap);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login1(String username, String password, String checkcode, Model model, HttpSession session) {
		String cc = (String) session.getAttribute("cc");
		if (!checkcode.equals(cc)) {
			model.addAttribute("error", "验证码出错，请重新输入");
			return "admin/login";
		}
		Corporation loginUser = corporationService.login(username, password);
		session.setAttribute("loginUser", loginUser);
		// List<UserRole> urs = userService.listUserRoles(loginUser.getId());
		/*
		 * // 获取角色列表集合 List<Role> rs = roleService.listRole(urs);
		 */
		boolean isAdmin = false;
		if (loginUser.getPermissionLevel() >= 1) {
			isAdmin = true;
		}
		session.setAttribute("isAdmin", isAdmin);
		if(!isAdmin){
			session.setAttribute("corpId", loginUser.getId());
			session.setAttribute("corpName", loginUser.getCorpName());
		}
		session.setAttribute("mcId", loginUser.getId());
		/*
		 * if (!isAdmin) { session.setAttribute("allActions", getAllActions(rs,
		 * session)); session.setAttribute("isAudit", isRole(rs,
		 * RoleType.ROLE_AUDIT)); session.setAttribute("isPublish", isRole(rs,
		 * RoleType.ROLE_PUBLISH)); }
		 */
		session.removeAttribute("cc");
		CmsSessionContext.addSessoin(session);
		return "redirect:/admin";
	}

	@ResponseBody
	@RequestMapping(value = "/doBackLogin", method = RequestMethod.POST)
	public JSONObject doLogin(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String checkcode
			,@RequestParam(required=false) Integer quickLogin) {
		JSONObject json = new JSONObject();
		try {
			//TODO FIXME 快捷登陆，方便测试，后期需要删除
			if(null != quickLogin){
				Corporation loginUser = corporationService.load(1);
				if(null == loginUser){
					return Result.failure(json, "账号或密码输入错误", "account_invalid");
				}
				Integer userId = loginUser.getId();
				session.setAttribute("loginUser", loginUser);
				session.setAttribute("user", loginUser);
				session.setAttribute("isAdmin", true);
				
//				session.setAttribute("corpId", userId);
//				session.setAttribute("corpName", loginUser.getCorpName());
				
				session.setAttribute("mcId", userId);
				session.removeAttribute("cc");
				return Result.success(json);
			}
			String cc = (String) session.getAttribute("cc");
			if (!checkcode.equals(cc)) {
				return Result.failure(json, "验证码出错，请重新输入", "code_invalid");
			}
			
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("username", username);
			map.put("password", SecurityUtil.md5(password));
			Corporation user = corporationService.selectByCondition(map);
			if(null == user){
				return Result.failure(json, "账号或密码输入不正确", "account_invalid");
			}
			Integer status = user.getStatus();
			if(null != status){
				if(status == 2){
					return Result.failure(json, "该账号已被删除", "account_deleted");
				}else if(status == 0){
					return Result.failure(json, "用户已经停用，请与零壹众公司联系", "account_disabled");
				}
			}
			Integer userId = user.getId();
			session.setAttribute("loginUser", user);
			session.setAttribute("user", user);
			boolean isAdmin = false;
			if (user.getPermissionLevel() >= 1) {
				isAdmin = true;
			}
			session.setAttribute("isAdmin", isAdmin);
			if(!isAdmin){
				session.setAttribute("corpId", userId);
				session.setAttribute("corpName", user.getCorpName());
			}
			session.setAttribute("mcId", userId);

			session.removeAttribute("cc");
			CmsSessionContext.addSessoin(session);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/doLogin", json);
		}
		return Result.success(json);
	}

	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp, HttpSession session) throws IOException {
		resp.setContentType("image/jpg");
		int width = 200;
		int height = 30;
		Captcha c = Captcha.getInstance();
		c.set(width, height);
		String checkcode = c.generateCheckcode();
		session.setAttribute("cc", checkcode);
		OutputStream os = resp.getOutputStream();
		ImageIO.write(c.generateCheckImg(checkcode), "jpg", os);
	}
}
