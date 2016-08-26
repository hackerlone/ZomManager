package com.zom.cms.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zom.cms.model.BmsVer;
import com.zom.cms.page.Pager;

public interface IVersionService {

	public Pager<BmsVer>  findBmsVer();
	public BmsVer findSpecBmsVer(String osType);

	public int delete(long id);
	

	public int update(BmsVer bv);
	

	public int add(BmsVer bv);
	

	public BmsVer load(long id);
	public List<BmsVer>  findBmsVers() ;
	public BmsVer uploadFile(BmsVer bmsVer, MultipartFile clientFile,String savePath);
	
	
	
	public List<BmsVer> selectListByCondition(Map<String, Object> map);
	public BmsVer selectByCondition(Map<String, Object> map);
	public int selectCountByCondition(Map<String, Object> map);
	public BmsVer selectByPrimaryKey(Integer id);
	
}
