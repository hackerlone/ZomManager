package com.zom.cms.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zom.cms.dao.user.IConsolerUserDao;
import com.zom.cms.dao.user.IUserDao;
import com.zom.cms.model.ConsolerUser;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.service.user.IGroupService.MyTsType;

@Service("consolerUserService")
public class ConsolerUserService implements IConsolerUserService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private IConsolerUserDao consolerUserDao;
	private IUserDao userDao;
	@Autowired
	private UserService userService;
	
	
	
	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IConsolerUserDao getConsolerUserDao() {
		return consolerUserDao;
	}

	@Inject
	public void setConsolerUserDao(IConsolerUserDao consolerUserDao) {
		this.consolerUserDao = consolerUserDao;
	}

	@Override
	public List<ConsolerUser> selectByconsolerId(Long id) {
		// TODO Auto-generated method stub
		return consolerUserDao.selectByconsolerId(id);
	}

	@Override
	public void add(ConsolerUser consolerUser) {
		consolerUserDao.add(consolerUser);
	}

	@Override
	public void deleteByConsolerId(Long id) {
		// TODO Auto-generated method stub
		consolerUserDao.deleteByConsolerId(id);
	}

	@Override
	public void addConsoler1To2(String logonName, List<Long> adminIds) {
		if (adminIds == null)
			return; // No any users
		for (Long long1 : adminIds) {
			User adminUser = userDao.load(long1);
			User users = userDao.loadByUsername(logonName);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminId", long1);
			map.put("userName", users.getLogonName());
			ConsolerUser c = consolerUserDao.loadUserConsole(map);
			if(null!=c){
				continue;
			}
			ConsolerUser consolerUser = new ConsolerUser();
			consolerUser.setConsoleDisplayName(adminUser.getDisplayName());
			consolerUser.setConsoleId(long1);
			consolerUser.setUserName(users.getLogonName());
			consolerUser.setUserPwd(users.getUserPassword());
			consolerUserDao.addConsoler1To2(consolerUser);
		}
	}

	@Override
	public void delConsoler1To2(String logonName, List<Long> adminIds) {
		if (adminIds == null)
			return; // No any users
		for (Long consoleId : adminIds) {
			ConsolerUser consolerUser = new ConsolerUser();
			consolerUser.setConsoleId(consoleId);
			consolerUser.setUserName(logonName);
			consolerUserDao.delConsoler1To2(consolerUser);
		}
	}
	@Override
	public void addConsoler2To1(Long adminId, List<String> userNames) {
		if (userNames == null)
			return; // No any users
		for (String string : userNames) {
			User adminUser = userDao.load(adminId);
			User users = userDao.loadByUsername(string);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminId", adminId);
			map.put("userName",string);
			ConsolerUser c = consolerUserDao.loadUserConsole(map);
			if(null!=c){
				continue;
			}
			ConsolerUser consolerUser = new ConsolerUser();
			consolerUser.setConsoleDisplayName(adminUser.getDisplayName());
			consolerUser.setConsoleId(adminId);
			consolerUser.setUserName(users.getLogonName());
			consolerUser.setUserPwd(users.getUserPassword());
			consolerUserDao.addConsoler1To2(consolerUser);
		}
	}
	@Override
	public void delConsoler2To1(Long adminId, List<String> userNames) {
		if (userNames == null)
			return; // No any users
		for (String string : userNames) {
			ConsolerUser consolerUser = new ConsolerUser();
			consolerUser.setConsoleId(adminId);
			consolerUser.setUserName(string);
			consolerUserDao.delConsoler1To2(consolerUser);
		}
	
	}
	
	

	

}
