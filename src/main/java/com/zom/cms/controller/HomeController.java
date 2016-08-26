package com.zom.cms.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zom.cms.auth.AuthClass;
import com.zom.cms.model.Corporation;
import com.zom.cms.service.user.ICorporationService1;
import com.zom.cms.service.user.IGroupService;
import com.zom.cms.service.user.IUserService;

@Controller
@RequestMapping("/admin/xx")
@AuthClass("login")
public class HomeController {
	private IGroupService groupService;
	private IUserService userService;
	private ICorporationService1 corporationService1;

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IGroupService getGroupService() {
		return groupService;
	}

	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public ICorporationService1 getCorporationService1() {
		return corporationService1;
	}

	@Inject
	public void setCorporationService1(ICorporationService1 corporationService1) {
		this.corporationService1 = corporationService1;
	}

	/**
	 * 获取用户分页列表集合信息
	 * 
	 * @param model
	 * @return 获取用户分页列表集合信息
	 */
	@RequestMapping("/home")
	public String listUser(Model model, HttpSession session) {
		// 通过UserDto操作
		Integer corpId = (Integer) session.getAttribute("corpId");
		Corporation user = (Corporation) session.getAttribute("loginUser");
		if (null != corpId) {
			Integer uCount = userService.findCorpUserCount(corpId);
			session.setAttribute("uCount", uCount);
		}
		model.addAttribute("user", user);
		// model.addAttribute("uCount",uCount);

		return "common/home.jsp";
	}
}
