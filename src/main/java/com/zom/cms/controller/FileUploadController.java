package com.zom.cms.controller;



import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zom.cms.model.BmsVer;
import com.zom.cms.service.user.IVersionService;

@Controller
@RequestMapping("/admin/file")
//@AuthClass("login")
public class FileUploadController{
	private IVersionService verService;
	


	public IVersionService getVersionService() {
		return verService;
	}

	@Inject
	public void setVersionService(IVersionService verService) {
		this.verService = verService;
	}



	
	/**
	 * 添加用户并跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String add(Model model, HttpSession session) {


		BmsVer bms= verService.findSpecBmsVer("android");
		
		if(bms==null){
			bms=new BmsVer();
		}
		session.setAttribute("bmsId", bms.getId());
		model.addAttribute("bmsVer", bms);
		model.addAttribute("id",bms.getId());

		return "bmsversion/fileupload";
	}


	/**
	 * 执行添加用户操作
	 * 
	 * @param userDto
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String add(@Valid BmsVer bmsVer,     
            @RequestParam("clientFile") MultipartFile clientFile, HttpSession session) {

	   
        String savePath = session.getServletContext().getRealPath("/upload");//this.getServletContext().getRealPath("/WEB-INF/upload");
        Integer id=(Integer)session.getAttribute("bmsId");
        bmsVer.setId(id);
        BmsVer b=verService.uploadFile(bmsVer, clientFile, savePath);
       
        verService.update(b);

		return "redirect:/admin/version/list";
	}


}
