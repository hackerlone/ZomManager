package com.zom.cms.controller.fileupload;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.controller.ExcelAction;
import com.zom.cms.lh.tools.Result;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 控制层 上传excel<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年8月23日 上午9:20:03<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
@Controller
public class UploadAction {
	
	private static Logger logger = Logger.getLogger("R");
	@RequestMapping(value="/common/uploadPage")
	public ModelAndView upload_html() {
		return new ModelAndView("/upload/upload");
	}

	/**上传处理方法*/
    @ResponseBody
    @RequestMapping(value="/common/upload", method = RequestMethod.POST)
    public JSONObject upload(Model model,HttpServletRequest request, HttpServletResponse response,
    		Plupload plupload, @RequestParam String random,HttpSession session,
    		@RequestParam(required=false)Integer doInsert ) {
    	JSONObject json = new JSONObject();
    	String tempFileDir = "/resources/excel/";//pictureService中引用了默认文件路径：file/dust/，这两个地方需要保持一致
    	String folder = "/resources/excel/";//上传的附件全部保存在dust文件夹中，对应的业务功能跟据附件地址转存到对应文件夹，这可目录可定期清空
        String filename =null;
        plupload.setRequest(request);
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String tempPath = basePath + tempFileDir;// 临时文件目录
        
        String savePath = basePath + folder; // 文件保存目录路径
        File dirSaveFile = new File(savePath);// 创建文件夹
        if (!dirSaveFile.exists()){
        	dirSaveFile.mkdirs();
        }
        File dirTempFile = new File(tempPath);// 创建临时文件夹
        if (!dirTempFile.exists()){
            dirTempFile.mkdirs();
        }
        try {
            filename= PluploadUtil.upload(plupload, dirSaveFile, dirTempFile, random);//上传文件
            if (PluploadUtil.isUploadFinish(plupload)) {//判断文件是否上传成功（被分成块的文件是否全部上传完成）
    	        String filePath1 = "\\" + folder + filename;
	        	String realPath1 = basePath + folder+filename;
	        	String realPath = realPath1.replace("\\", "/");
	        	String filePaths = filePath1.replace("\\", "/");
	        	
//	        	System.out.println("filePaths:"+filePaths);
//	        	System.out.println("realPath:"+realPath);
	        	json.put("filePath", realPath);
            }else{
            	return Result.failure(json, "上传失败", "upload_failure");
            }
        } catch (Exception e) {
        	Result.catchError(e, logger, "LH_ERROR-UploadAction-AJAX-/upload-上传附件出现异常", json);
        }
        return Result.success(json);
    }   
}