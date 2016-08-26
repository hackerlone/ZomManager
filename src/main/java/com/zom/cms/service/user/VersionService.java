package com.zom.cms.service.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.dao.user.IBMSVerDao;
import com.zom.cms.model.BmsVer;
import com.zom.cms.model.CmsException;
import com.zom.cms.page.PageBoundsUtil;
import com.zom.cms.page.Pager;

@Service
public class VersionService implements IVersionService {
	@Autowired
	private IBMSVerDao verDao;

	@Override
	public Pager<BmsVer>  findBmsVer() {
		//获取用户总数
		int count = verDao.findBmsVerCount();

		//封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		//获取用户分页列表集合信息
		List<BmsVer> list = verDao.findBmsVer(pageBounds);
	
		//封装用户分页的Pager对象
		Pager<BmsVer> pages = new Pager<BmsVer>(count,list);
		return pages;	
		//return null;
	}
	
	@Override
	public List<BmsVer>  findBmsVers() {
		//获取用户总数
		int count = verDao.findBmsVerCount();

		//封装PageBounds对象
	//	PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		//获取用户分页列表集合信息
		List<BmsVer> list = verDao.findBmsVers();
	
		//封装用户分页的Pager对象
		//Pager<BmsVer> pages = new Pager<BmsVer>(count,list);
		return list;	
		//return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		verDao.delete((long) id);
		return 0;
	}

	@Override
	public int update(BmsVer bv) {
		// TODO Auto-generated method stub
        try{
        	verDao.update(bv);
       } catch (Exception e){
        	throw new CmsException("更新版本控制");
       }
      
	 return 0;
	
	}

	@Override
	public int add(BmsVer bv) {
		// TODO Auto-generated method stub
		verDao.add(bv);
		return 0;
	}

	@Override
	public BmsVer load(long id) {
		// TODO Auto-generated method stub
		return verDao.load(id);
	}
	@Override
	public BmsVer findSpecBmsVer(String osType) {
		// TODO Auto-generated method stub
		 return verDao.findSpecBmsVer(osType);
	}

	@Override
	public BmsVer uploadFile(BmsVer bmsVer, MultipartFile clientFile, String savePath) {
		// TODO Auto-generated method stub
  //      String savePath = session.getServletContext().getRealPath("/upload");//this.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
         try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
             ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
                    //得到上传的文件名称，
                    String filename = clientFile.getOriginalFilename();

            
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                     filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = clientFile.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    
                    final String accessKey = "BhnDjnV7dG417qsN";
                    final String screctKey = "bCvCJZyjcYQndNg5eQQWOPbMGf9WWz";
                    //关于这个endPoint，可以参考：http://bbs.aliyun.com/read/149100.html?spm=5176.7189909.0.0.YiwiFw
                    String endPoint = "http://oss-cn-beijing.aliyuncs.com";

                    //创建上传Object的Metadata  
                    File file1=   new File(savePath + "\\" + filename);
                    InputStream fileContent = new FileInputStream(file1);

                    ObjectMetadata objectMetadata=new ObjectMetadata();  
                   

                    long len1= file1.length();
                    String key= "APPLatestVersion/"+filename;
                        //初始化 OSSClient
                    OSSClient ossClient = new OSSClient(endPoint, accessKey, screctKey);
                    
                    
                    
                    objectMetadata.setContentLength(fileContent.available());  
                    objectMetadata.setCacheControl("no-cache");  
                    objectMetadata.setHeader("Pragma", "no-cache");  
                    objectMetadata.setContentDisposition("inline;filename=" + file1.getName());  
                    
                    ossClient.putObject("bugle", key, fileContent,objectMetadata);
                    
                    
                    
                    //the putObject function did not return the url link in OSS, therefore just hard code
                    //the link by the rule.
                    String urllink= "http://bugle.oss-cn-beijing.aliyuncs.com/APPLatestVersion/"+filename;
                    bmsVer.setClientUrl(urllink);

                    //upload this file to backup folder, in case that we use one name for all apk
                   
                    File file2=   new File(savePath + "\\" + filename);
                    InputStream fileContent1 = new FileInputStream(file2);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    key="AppBackup/"+df.format(new Date())+"_"+filename; 
                    objectMetadata.setContentDisposition("inline;filename=" + df.format(new Date())+"_"+filename);  

                    ossClient.putObject("bugle", key, fileContent1,objectMetadata);

                    
                    //remove the temporary file
                    file1.delete();
        }   catch (Exception e) {
        	throw new CmsException("上传文件失败");

        }
                                                   		
		 return bmsVer; //return the BMSVersion for the update
	}
	
	
	
	
	public List<BmsVer> selectListByCondition(Map<String, Object> map) {
		return verDao.selectListByCondition(map);
	}
	public BmsVer selectByCondition(Map<String, Object> map) {
		return verDao.selectByCondition(map);
	}
	public int selectCountByCondition(Map<String, Object> map) {
		return verDao.selectCountByCondition(map);
	}
	public BmsVer selectByPrimaryKey(Integer id) {
		return verDao.selectByPrimaryKey(id);
	}
	
}
