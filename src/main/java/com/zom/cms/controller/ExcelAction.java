package com.zom.cms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelStyleType;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zom.cms.auth.AuthClass;
import com.zom.cms.dto.GroupDto;
import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.lh.tools.Check;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.lh.utils.ExcelUtils;
import com.zom.cms.model.ConsolerUser;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserAdmin;
import com.zom.cms.model.UserGroup;
import com.zom.cms.service.user.IConsolerUserService;
import com.zom.cms.service.user.ICorporationService1;
import com.zom.cms.service.user.IGroupService;
import com.zom.cms.service.user.IUserService;
import com.zom.cms.service.user.UserService;
import com.zom.cms.utils.search.ExcelReader;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 控制层 导入导出excel<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年8月23日 上午9:20:03<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
@Controller
@AuthClass("login")
@RequestMapping("/admin")
public class ExcelAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private ICorporationService1 corporationService;
	@Autowired
	private IConsolerUserService consolerUserService;

	private static Logger logger = Logger.getLogger("R");

	public static void main(String[] args) {
		// new ExcelAction().testExport(null, null, null);
	}

	private void buildData(List<User> userList, List<GroupDto> groupList, List<User> console1List, List<User> console2List, Integer corpId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("corpId", corpId);
		// 用户对象封装
		map.put("rank", "0");
		List<User> users = userService.selectListByCondition(map);
		for (User user : users) {
			user.setLogonName(user.getLogonName());
			
			String userPassword = UserService.decryptPassword(user);
			user.setUserPassword(userPassword);
			userList.add(user);
		}
		map.clear();
		map.put("corpId", corpId);
		// 组-用户对象封装
		List<Group> groupDtos = groupService.selectListByCondition(map);
		for (Group group : groupDtos) {
			GroupDto groupDto = new GroupDto(group);
			/* groupDto.setId(111L); */
			User user = userService.load(group.getOwnerId());
			System.out.println("###"+user.getLogonName());
			groupDto.setOwnerName(user.getLogonName());
			groupDto.setPhone(user.getPhone());
			List<User> user2 = userService.listGroupAllUsers(group.getId());
			String logonName = new String();
			for (User user3 : user2) {
				logonName += (user3.getLogonName() + ":" + user3.getPhone() + ",\n");
			}
			// System.out.println(udisplayName.length());
			// String uName = udisplayName.substring(0,
			// udisplayName.length()-1);
			groupDto.setGroupUserName(logonName);
			groupList.add(groupDto);
		}
		// 一阶调度台封装
		map.put("rank", 1);
		List<User> users2 = userService.selectListByCondition(map);
		for (User user : users2) {
			String managerUser = new String();
			String managerUserPwd = new String();
			List<ConsolerUser> consolerUsers = consolerUserService.selectByconsolerId(user.getId());
			for (ConsolerUser consolerUser : consolerUsers) {
				managerUserPwd += consolerUser.getUserName() + ":" + consolerUser.getUserPwd() + ",\n";
			}
			user.setManagerUser(managerUserPwd);
			user.setManagerUserPwd(managerUserPwd);
			console1List.add(user);
		}
		// 二阶调度台封装
		map.put("rank", 2);
		List<User> user2s = userService.selectListByCondition(map);
		for (User user : user2s) {
			String managerUser = new String();
			String managerUserPwd = new String();
			List<User> users3 = userService.listAdminAllUsers(user.getId());
			for (User user2 : users3) {
				managerUser += user2.getDisplayName() + ":" + user2.getPhone() + ",\n";
			}
			List<ConsolerUser> consolerUsers = consolerUserService.selectByconsolerId(user.getId());
			for (ConsolerUser consolerUser : consolerUsers) {
				managerUserPwd += consolerUser.getUserName() + ":" + consolerUser.getUserPwd() + ",\n";
			}
			user.setManagerUser(managerUser);
			user.setManagerUserPwd(managerUserPwd);
			console2List.add(user);
		}

	}

	/**
	 * 导出Excel表格
	 */
	@ResponseBody
	@RequestMapping(value = "/testExport", method = RequestMethod.GET)
	public JSONObject testExport(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			 Integer corpId = (Integer) session.getAttribute("corpId");
			Date date = new Date();
			String path = request.getSession().getServletContext().getRealPath("/resources/temple/template.xlsx");

			TemplateExportParams params = new TemplateExportParams(path, true);
			params.setHeadingRows(1);
			params.setHeadingStartRow(1);
			params.setStyle(ExcelStyleType.BORDER.getClazz());
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> userList = new ArrayList<User>();
			List<GroupDto> groupList = new ArrayList<GroupDto>();
			List<User> console1List = new ArrayList<User>();
			List<User> console2List = new ArrayList<User>();

			buildData(userList, groupList, console1List, console2List, 1);

			map.put("userList", userList);
			map.put("groupList", groupList);
			map.put("consoleList1", console1List);
			map.put("consoleList2", console2List);

			Workbook workbook = ExcelExportUtil.exportExcel(params, map);

			String filename = String.valueOf(date.getTime());
			// String
			// paths=request.getSession().getServletContext().getRealPath("/resources/excel");
			// String outPutPath = paths+"\\" + filename + ".xlsx";
			// FileOutputStream fos = new FileOutputStream(paths);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((filename + ".xlsx").getBytes(), "iso-8859-1"));
			response.setBufferSize(1024);
			OutputStream fos = response.getOutputStream();
			workbook.write(fos);
			fos.close();
			Result.success(json);
			// json.put("msg", paths1);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/corp/doDelete", json);
		}
		return Result.success(json);
	}

	@RequestMapping(value = "/testExport1", method = RequestMethod.GET)
	public String exportXlsByT(ModelMap modelMap) {
		Map<Integer, Map<String, Object>> sheetMap = new HashMap<Integer, Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = userService.selectListByCondition(null);
		map.put("userList", userList);

		modelMap.put(TemplateExcelConstants.FILE_NAME, "课程信息");
		modelMap.put(TemplateExcelConstants.PARAMS, new TemplateExportParams("G:/template.xlsx"));
		modelMap.put(TemplateExcelConstants.MAP_DATA, sheetMap);
		modelMap.put(TemplateExcelConstants.CLASS, User.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA, userList);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}

	/**
	 * 导出Excel表格
	 */
	@ResponseBody
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public JSONObject exportExcel(Model model, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Workbook workbook = ExcelUtils.exportExcel(list, ExcelType.HSSF);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/corp/doDelete", json);
		}
		return Result.success(json);
	}

	/**
	 * 级联删除
	 */
	private void delDataByCorpId(Integer corpId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("corpId", corpId);
		List<User> users = userService.selectListByCondition(map);
		for (User user : users) {
			List<Group> groups = groupService.selectListByCondition(map);
			for (Group group : groups) {
				groupService.deleteGroupUsers(group.getId());//组-用户关联
			}
			if(user.getRank() == 2){
				userService.deleteByAdminId(user.getId());//二阶---一阶调度台关联
			}
			if(user.getRank() != 0){
				consolerUserService.deleteByConsolerId(user.getId()); //调度台-用户关联	
			}
			userService.deleteUserAdmins(user.getId());
		}
		groupService.deleteByCorpId(corpId);//删除组	
		userService.deleteByCorpId(corpId);//删除用户
		
	}
	/**
	 * 导入Excel表格
	 */
	@ResponseBody
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public JSONObject importExcel(Model model, HttpServletRequest request, 
			HttpSession session) {
		JSONObject json = new JSONObject();
		Map<String,Object> map = ActionUtil.getAllParam(request);
		String filePath = (String) map.get("filePaths");
		try {
			System.out.println(filePath);
			impotExcelData(session, json,filePath);
			
			
			
//			InputStream is = new FileInputStream("G:/列表.xlsx");
//	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
//	        // 循环工作表Sheet
//	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
//	        	XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
//	            if (xssfSheet == null) {
//	                continue;
//	            }
//	            // 循环行Row
//	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++){
//	            	XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//	                if (xssfRow == null) {
//	                    continue;
//	                }
//	                // 获取列Cel 0开始
//	                System.out.println(xssfRow.getCell(1));
//	            }
//	        }
			
			
			
			
//			ImportParams params = new ImportParams();
//			params.setHeadRows(2);
//			params.setTitleRows(2);
//			params.setSheetNum(1);
//			params.setNeedSave(true);
////			params.setStartRows(0);
//			List<User> lists = ExcelImportUtil.importExcel(new File(
//			            "G:/列表.xlsx"), User.class, params);
//			 System.out.println(lists.size());
//			for (User user : lists) {
//				System.out.println(user.getDisplayName());
//				System.out.println(user.getLogonName());
//			}
			
//			List<Map<String, Object>> lists = ExcelImportUtil.importExcel(
//					new File("G:/列表.xlsx"), Map.class, params);

		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/admin/corp/doDelete", json);
			return Result.failure(json, e.getMessage(), null);
		}
		return Result.success(json,"导入完成",null);
	}

	private JSONObject impotExcelData(HttpSession session, JSONObject json,String filePath) throws FileNotFoundException {
		Integer corpId = (Integer) session.getAttribute("corpId");
		InputStream is = new FileInputStream(filePath);
		delDataByCorpId(corpId);
		//用户信息
		List<String[]> userString = ExcelReader.readExcelContent(is,0);
		for (String[] strings : userString) {
			User user = new User();
			user.setRank(0);
			user.setCorpId(corpId);
			if(null == strings[1]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[2]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[3]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[4]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[5]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[6]){return Result.failure(json, "数据格式错误", "data erroe");}
			user.setLogonName(strings[1]);
			user.setDisplayName(strings[2]);
			user.setStatus(Integer.valueOf(strings[3]));
			user.setUserPassword(strings[4]);
			user.setPhone(String.valueOf(strings[5]));
			user.setPriority(Integer.valueOf(strings[6]));
			userService.add(user, null, null, null);
		}
		
		InputStream is2 = new FileInputStream("G:/列表.xlsx");
		//一阶调度台
		List<String[]> consolerString = ExcelReader.readExcelContent(is2,2);
		for (String[] strings : consolerString) {
			User user = new User();
			user.setRank(1);
			user.setCorpId(corpId);
			if(null == strings[1]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[2]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[3]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[4]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[5]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[6]){return Result.failure(json, "数据格式错误", "data erroe");}
//			user.setId(Long.valueOf(strings[1]));
			user.setLogonName(strings[2]);
			user.setDisplayName(strings[3]);
			user.setStatus(Integer.valueOf(strings[4]));
			user.setUserPassword(strings[5]);
			user.setPhone(strings[6]);
			user.setPriority(Integer.valueOf(strings[7]));
			userService.add(user, null, null, null);
			if(strings[8].length() > 0){
				String ss = strings[8].substring(0, strings[8].length()-1);
				String[] values = ss.split(",");
				for (int i = 0; i < values.length; i++) {//管辖的成员 关联帐号
					ConsolerUser consolerUser = new ConsolerUser();
					String[] value = values[i].split(":");
					String userName = value[0];
					String userPwd = value[1];
					consolerUser.setUserName(userName);
					consolerUser.setUserPwd(userPwd);
					System.out.println(strings[2]);
					consolerUser.setConsoleDisplayName(strings[2]);
					consolerUser.setConsoleId(user.getId());//一介调度台id
					consolerUserService.add(consolerUser);
				}
			}
		}
		
		InputStream is3 = new FileInputStream("G:/列表.xlsx");
		//二阶调度台
		List<String[]> consoler2String = ExcelReader.readExcelContent(is3,4);
		for (String[] strings : consoler2String) {
			User user = new User();
			user.setRank(2);
			user.setCorpId(corpId);
			if(null == strings[1]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[2]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[3]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[4]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[5]){return Result.failure(json, "数据格式错误", "data erroe");}
			if(null == strings[6]){return Result.failure(json, "数据格式错误", "data erroe");}
			user.setLogonName(strings[1]);
			user.setDisplayName(strings[2]);
			user.setStatus(Integer.valueOf(strings[3]));
			user.setUserPassword(strings[4]);
			user.setPhone(strings[5]);
			user.setPriority(Integer.valueOf(strings[6]));
			userService.add(user, null, null, null);
			if(strings[7].length() > 0){//管理的一介调度台
				String ss = strings[7].substring(0, strings[7].length()-1);
				String[] values = ss.split(",");
				for (int i = 0; i < values.length; i++) {
					UserAdmin userAdmin = new UserAdmin();
					String[] value = values[i].split(":");
					String userName = value[0];
					String userPhone = value[1];
					//User adminUser = userService.loadByUsername(strings[1]);
					User seluser = userService.loadByUsername(userName);
					if(null != seluser){
						userService.addUserAdmin(seluser.getId(),user.getId());
					}
				}
			}
		}
		
		InputStream is4 = new FileInputStream("G:/列表.xlsx");
		//二阶调度台 关联帐号
		List<String[]> consoler2UserString = ExcelReader.readExcelContent(is4,5);
		for (String[] strings : consoler2UserString) {//二阶调度台 管理的帐号
			if(strings[2].length() > 0){
				String ss = strings[2].substring(0, strings[2].length()-1);
				String[] values = ss.split(",");
				for (int i = 0; i < values.length; i++) {
					ConsolerUser consolerUser = new ConsolerUser();
					String[] value = values[i].split(":");
					String userName = value[0];
					String userPwd = value[1];
					consolerUser.setUserName(userName);
					consolerUser.setUserPwd(userPwd);
					consolerUser.setConsoleDisplayName(strings[2]);
					User user = userService.loadByUsername(strings[1]);
					if(null != user){
						consolerUser.setConsoleId(user.getId());
						consolerUserService.add(consolerUser);
					}
				}
			}
		}
		
		InputStream is1 = new FileInputStream("G:/列表.xlsx");
		//组信息
		List<String[]> groupString = ExcelReader.readExcelContent(is1,1);
		for (String[] strings : groupString) {
			GroupDto groupDto = new GroupDto();
			if(null == strings[1]){return Result.failure(json, "数据格式错误", "data error");}
			if(null == strings[2]){return Result.failure(json, "数据格式错误", "data error");}
			if(null == strings[3]){return Result.failure(json, "数据格式错误", "data error");}
			if(null == strings[4]){return Result.failure(json, "数据格式错误", "data error");}
//			groupDto.setId(Long.valueOf(strings[1]));
			groupDto.setGroupName(strings[2]);
			groupDto.setStatus(Integer.valueOf(strings[3]));
			System.out.println(strings[4]+"  *** ");
			User user1 = userService.loadByUsername(strings[4]);
			if(null != user1){
				groupDto.setOwnerId(user1.getId());//
			}
			groupDto.setCorpId(corpId);
			Group group = groupService.add(groupDto);
			if(strings[6].length() > 0){
				String ss = strings[6].substring(0, strings[6].length()-1);
				String[] values = ss.split(",");
				for (int i = 0; i < values.length; i++) {
					UserGroup userGroup = new UserGroup();
					String[] value = values[i].split(":");
					String userName = value[0];
					String userPhone = value[1];
					User user = userService.loadByUsername(userName);
					
					List<Long> gids = new ArrayList<Long>();
					gids.add(group.getId());
					if(null != user){
						userService.addUserGroups(user, gids);
					}
				}
			}
		}
		return json;
	}

	

}
