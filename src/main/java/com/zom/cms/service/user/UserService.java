package com.zom.cms.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.dao.user.IConsolerUserDao;
import com.zom.cms.dao.user.IGroupDao;
import com.zom.cms.dao.user.IRoleDao;
import com.zom.cms.dao.user.IUserDao;
import com.zom.cms.dto.GroupDto;
import com.zom.cms.dto.UserDto;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserAdmin;
import com.zom.cms.model.UserGroup;
import com.zom.cms.page.PageBoundsUtil;
import com.zom.cms.page.Pager;
import com.zom.cms.service.ShortDataService;

@Service
public class UserService implements IUserService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private IUserDao userDao;
	private IRoleDao roleDao;
	private IGroupDao groupDao;
	private IConsolerUserDao consolerUserDao;
	@Autowired
	private ShortDataService sdService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private IdService idService;
	@Autowired
	private ConsolerUserService consolerUserService;

	private static final Logger logger = Logger.getLogger(UserService.class);

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	
	public ConsolerUserService getConsolerUserService() {
		return consolerUserService;
	}
	@Inject
	public void setConsolerUserService(ConsolerUserService consolerUserService) {
		this.consolerUserService = consolerUserService;
	}

	/**
	 * @param user
	 */
	public static String decryptPassword(User user) {
		String encrypt_passwd = user.getUserPassword();
		String decrypt_passwd = "";
		int[] tmpNum = new int[encrypt_passwd.length() / 2];

		tmpNum[0] = (Integer.valueOf(encrypt_passwd.substring(0, 2), 16)) ^ 0xEE;
		decrypt_passwd += (char) tmpNum[0];
		for (int i = 1; i < encrypt_passwd.length() / 2; i++) {
			tmpNum[i] = (Integer.valueOf(encrypt_passwd.substring(i * 2, i * 2 + 2), 16)) ^ (Integer.valueOf(encrypt_passwd.substring((i - 1) * 2, (i - 1) * 2 + 2), 16));
			decrypt_passwd += (char) tmpNum[i];
		}

		return decrypt_passwd;
	}

	private void encryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		String tmp_passwd = user.getUserPassword();
		String encrypt_passwd = "";
		int[] tmpNum = new int[tmp_passwd.length()];

		tmpNum[0] = tmp_passwd.charAt(0) ^ 0xEE;
		encrypt_passwd += String.format("%02x", tmpNum[0]);
		for (int i = 1; i < tmp_passwd.length(); i++) {
			tmpNum[i] = tmp_passwd.charAt(i) ^ tmpNum[i - 1];
			encrypt_passwd += String.format("%02x", tmpNum[i]);
		}
		user.setUserPassword(encrypt_passwd);
	}

	public void addUserGroups(User user, List<Long> gid) {
		for (Long long1 : gid) {
			addUserGroup(user, long1);
		}
	}

	public void delUserGroups(User user, List<Long> gid) {
		for (Long long1 : gid) {
			delUserGroup(user, long1);
		}
	}

	private void addUserGroup(User user, Long gid) {
		// 1、检查组对象是否存在，如果不存在，就抛出异常
		Group group = groupDao.load(gid);
		if (group == null)
			throw new CmsException("要添加用户的组对象不存在");
		// 2、检查用户 组对象是否已经存在，如果存在，就不添加
		UserGroup ug = userDao.loadUserGroup(user.getId(), group.getId());
		if (ug != null)
			return;
		userDao.addUserGroup(user.getId(), gid, Long.valueOf(user.getPriority()));
		// update Group Ts
		groupService.updateOneMyTs(group, group.getId(), IGroupService.MyTsType.GROUP, true, 1);
		groupService.update(group);
	}

	private void delUserGroup(User user, Long gid) {
		// 1、检查组对象是否存在，如果不存在，就抛出异常
		Group group = groupDao.load(gid);
		if (group == null)
			throw new CmsException("要操作用户的组对象不存在");
		userDao.deleteUserGroup(user.getId(), gid);
		// update Group Ts
		groupService.updateOneMyTs(group, group.getId(), IGroupService.MyTsType.GROUP, true, 1);
		groupService.update(group);
	}

	@Override
	public void add(User user, List<Long> consoleIds, Long[] gids, List<Long> userIds) {
		// userDao.add(user);
		User tu = new User();
		if (user.getRank() != 0) {
			tu = userDao.loadByUsername(user.getLogonName());
			user.setDevice("android");
			if (tu != null)
				throw new CmsException("添加的用户登录名已经存在，不能添加");
			// check if phone exists
			tu = userDao.loadByPhone(user.getPhone());
			if (tu != null)
				throw new CmsException("手机号码已存在，不能添加");
		} else {
			tu = userDao.loadByPhone(user.getPhone());
			user.setDevice("web");
			if (tu != null)
				throw new CmsException("手机号码已存在，不能添加");
		}
		user.setDevice("web");
		user.setLogonState("offline");
		user.setTsGroup(0l);
		user.setTsProfile(0l);
		user.setAdmTs(0l);
		user.setSalt("ssss");
		user.setStatus(1);

		user.setPreconfig(1);
		user.setDefaultGrp(0l);

		user.setRegisterDate(new Date());
		user.setLastLogonDate(new Date());
		// TODO 密码导入冲突
		encryptPassword(user);
		// generate the UID and DCG ID according to the zone number
		Long id = idService.getZoneCurUid(user.getZoneId());
		user.setId(id);
		user.setDcgId(id);

		userDao.add(user);
		// rank ==0 is for user
		if (user.getRank() == 0) {
			// auto generate an unique username
			// TODO 登录名与 导入组创建者冲突
			user = userDao.loadByPhone(user.getPhone());
			if (null == user.getLogonName()) {
				String username = "u" + Integer.toString(user.getCorpId()) + user.getId().toString();
				user.setLogonName(username);
			}
		} else {
			user = userDao.loadByUsername(user.getLogonName());
		}

		user.setPushId("u" + (user.getId()).toString());
		user.setPushPassword("u" + user.getId().toString());

		if (gids != null) {
			// 添加用户组对象
			for (Long gid : gids) {
				addUserGroup(user, gid);
			}
		}

		// 创造一个专属group，用来打individual call。

		Group group = new Group("temp", user.getId(), user.getCorpId(), 0, user.getAdminId());
		group.setCreateDate(new Date());
		GroupDto groupDto = new GroupDto(group);
		groupDto.setDcg(1); // set it to 专属group
		groupDto.setOwnerName(user.getPushId());
		groupDto.setRank(user.getRank());
		groupDto.setId(id); // set group ID for DCG
		try {
			groupService.add(groupDto);
		} catch (Exception e) {
			// TODO: handle exception
		}

		updateOneMyTs(user, user.getId(), MyTsType.PROFILE, false, 1);
		updateOneMyTs(user, user.getId(), MyTsType.GROUP, false, 1);

		// add user Ids for rank 1 and rank 2 console
		if (userIds != null) {
			for (Long uid : userIds) {
				addUserAdmin(uid, user.getId());
				// update the TS for the Admin TS
				User tmp = userDao.load(uid);
				updateOneMyTs(tmp, uid, MyTsType.ADMIN, true, 1);

			}
		}

		// add admin mapping for BMS and Rank1 console
		if (consoleIds != null) {
			for (Long cid : consoleIds) {
				addUserAdmin(user.getId(), cid);
			}

			//
			updateOneMyTs(user, user.getId(), MyTsType.ADMIN, false, 1);

		}
		userDao.update(user);

		// 此处用来通知数据库的监听者，以后再补代码

	}

	public void addUserAdmin(Long uid, Long adminId) {
		userDao.addUserAdmin(uid, adminId);
	}

	@Override
	public void delete(Long id) {
		// TODO 需要进行用户是否有文章的判断

		// 1、删除用户 admin 关系
		deleteUserAdmins(id);
		deleteAdminUsers(id);

		// 2 删除用户 组关系
		groupService.deleteUserGroups(id);

		// 删除DCG
		User u = userDao.load(id);
		groupService.delete(u.getDcgId());
		// 3、删除用户
		updateOneMyTs(u, u.getId(), MyTsType.PROFILE, true, 1);
		userDao.delete(id);

	}

	@Override
	public void update(User user, List<Long> uids, Long[] gids, List<Long> consoleIds) {
		User tu;
		boolean updateMyConsole = false;
		if (user.getRank() != 0) {
			tu = userDao.loadByUsername(user.getLogonName());
			if ((tu != null) && (tu.getId().longValue() != user.getId().longValue())) {
				throw new CmsException("登录名已使用，请用其他名字");
			}
			user.setDevice("web");
		} else {
			tu = userDao.loadByPhone(user.getPhone());
			if ((tu != null) && (tu.getId().longValue() != user.getId().longValue())) {
				throw new CmsException("手机号码已使用，请用其他手机号码");
			}

			user.setDevice("android");
		}

		boolean updateGrp = false;
		updateOneMyTs(user, user.getId(), MyTsType.PROFILE, false, 1);

		if (user.getRank() == 0) {
			List<UserGroup> listUserGroup = userDao.listUserGroupIds(user.getId());
			List<Long> egids = new ArrayList<Long>();
			for (UserGroup usergroup : listUserGroup) {
				egids.add(usergroup.getGroupId());
			}
			if (gids == null) {
				for (Long gid : egids) {
					userDao.deleteUserGroup(user.getId(), (long) gid);
					updateGrp = true;
					Group g = groupDao.load(gid);
					if (g != null) {
						groupService.updateOneMyTs(g, gid, IGroupService.MyTsType.GROUP, true, 1);

					}
				}
			} else {

				// 2、判断，如果egids中不存在gids就要进行添加

				for (Long gid : gids) {
					if (!egids.contains(gid)) {
						addUserGroup(user, (long) gid);
						Group group = groupDao.load(gid);
						updateGrp = true;
						groupService.updateOneMyTs(group, group.getId(), IGroupService.MyTsType.GROUP, true, 1);

					}
				}

				for (Long egid : egids) {
					if (!ArrayUtils.contains(gids, egid)) {
						Group group = groupDao.load(egid);
						if (group == null)
							throw new CmsException("要添加用户的组对象不存在");

						userDao.deleteUserGroup(user.getId(), (long) egid);
						updateGrp = true;
						// update Group Ts
						groupService.updateOneMyTs(group, group.getId(), IGroupService.MyTsType.GROUP, true, 1);

					}
				}
				if (updateGrp == true) {
					updateOneMyTs(user, user.getId(), MyTsType.GROUP, true, 1);
				}

			}

		}
		// update console list for BMS and Rank 1 console
		List<Long> oldCids = userDao.listUserAmdinIds(user.getId());

		if (consoleIds != null) {

			// 2、判断，如果oldCids中不存在consoleIds就要进行添加

			for (Long cid : consoleIds) {
				if (!oldCids.contains(cid)) {
					addUserAdmin((long) user.getId(), cid);

					updateMyConsole = true;
				}
			}

			// 如果新 consoleids 中不包括老的，就要删除
			for (Long oldCid : oldCids) {
				if (!consoleIds.contains(oldCid)) {

					updateMyConsole = true;
					userDao.deleteUserAdmin(oldCid, user.getId());

				}
			}

		} else if (user.getRank() != 2) {
			if (oldCids.size() > 0) {
				updateMyConsole = true;
				userDao.deleteUserAdmins(user.getId());
			}

		}
		if (updateMyConsole == true) {
			updateOneMyTs(user, user.getId(), MyTsType.ADMIN, false, 1);

		}

		userDao.update(user);
		// update user list for Rank 1 and Rank 2 console
		if (user.getRank() != 0) { // no need update for consolse
			if (uids == null) {
				// get all user IDs under that admin
				List<User> fullUsers = userDao.listFullUsersByConsoleId(user.getId());
				for (User u : fullUsers) {
					updateOneMyTs(u, u.getId(), MyTsType.ADMIN, true, 1);

				}
				if (fullUsers != null)
					deleteAdminUsers(user.getId());

			} else {

				List<Long> oldUids = userDao.listUsersIdByConsoleId(user.getId());

				// 2、判断，如果egids中不存在gids就要进行添加

				for (Long uid : uids) {
					if (!oldUids.contains(uid)) {
						addUserAdmin(uid, (long) user.getId());
						User u = userDao.load(uid);
						updateOneMyTs(u, u.getId(), MyTsType.ADMIN, true, 1);

					}
				}

				for (Long oldUid : oldUids) {
					if (!uids.contains(oldUid)) {

						userDao.deleteUserAdmin(user.getId(), oldUid);
						User u = userDao.load(oldUid);
						updateOneMyTs(u, u.getId(), MyTsType.ADMIN, true, 1);

					}
				}

			}

		}

	}

	@Override
	public void updateStatus(Long id) {
		User user = userDao.load(id);
		if (user != null) {
			int status = user.getStatus();
			if (status == 1) { // is on disable state
				user.setStatus(0); // set it to enable state
			} else if (status == 0) {
				user.setStatus(1); // set it to disable state
			}
			updateOneMyTs(user, id, MyTsType.PROFILE, false, 1);
			userDao.update(user);
		}

	}

	/*
	 * common function to update UserDto
	 */
	public List<UserDto> updateUserDto(List<User> users) {
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for (int i = 0; i < users.size(); i++) {

			UserDto userDto = new UserDto(users.get(i), listUserGroupIds(users.get(i).getId()));
			userDto.setGroupNames(userDao.listUserGroupNames(users.get(i).getId()));

			// set admin name string for the normal users
			// don't do for the console.
			/*
			 * if(users.get(i).getRank()!=2){ User
			 * user=load(users.get(i).getAdminId()); if(user!=null){
			 * userDto.setAdminName(user.getDisplayName()); } }
			 */
			if (users.get(i).getRank() != 2) {
				userDto.setAdminNames(userDao.listConsoleNameByIds(users.get(i).getId()));
			} else {
				userDto.setAdminNames(userDao.listUsersNameByConsoleId(users.get(i).getId()));
			}

			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	/*
	 * common function to update UserDto
	 */
	@Override
	public List<String> listAdminNames(Long uid) {
		return userDao.listConsoleNameByIds(uid);

	}

	/*
	 * common function to update UserDto
	 */
	@Override
	public List<Long> listUserAmdinIds(Long uid) {
		return userDao.listUserAmdinIds(uid);

	}

	/**
	 * 获取用户分页列表集合信息
	 */
	@Override
	public Pager<UserDto> findUser() {
		// 获取用户总数
		int count = userDao.findUserCount();

		// 封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		// 获取用户分页列表集合信息
		List<User> list = userDao.findUser(pageBounds);
		Pager<UserDto> pages = new Pager<UserDto>(count, updateUserDto(list));
		return pages;
	}

	/**
	 * 获取公司下用户分页列表集合信息
	 * 
	 * @return 获取用户分页列表集合信息
	 */
	@Override
	public Pager<UserDto> findCorpUser(int corp_id) {
		// 获取用户总数
		int count = userDao.findCorpUserCount(corp_id);

		// 封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		// 获取用户分页列表集合信息
		List<User> list = userDao.findCorpUser(pageBounds, corp_id);

		// 封装用户分页的Pager对象
		Pager<UserDto> pages = new Pager<UserDto>(count, updateUserDto(list));
		return pages;
	}

	@Override
	public int findCorpUserCount(int corp_id) {
		return userDao.findCorpUserCount(corp_id);
	}

	@Override
	public int findCorpAllConsoleCount(int corp_id) {
		return userDao.findCorpAllConsoleCount(corp_id);
	}

	/**
	 * 获取公司下用户列表集合信息
	 * 
	 * @return 获取用户列表集合信息
	 */
	@Override
	public List<User> findCorpAllUser(int corp_id) {

		// 获取用户分页列表集合信息
		List<User> list = userDao.findCorpAllUser(corp_id);

		return list;
	}

	/**
	 * 获取console分页列表集合信息
	 */
	@Override
	public Pager<UserDto> findConsoles(int rank) {
		// 获取用户总数
		int count = userDao.findConsoleCount(rank);

		// 封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		// 获取用户分页列表集合信息
		List<User> list = userDao.findConsole(pageBounds, rank);
		Pager<UserDto> pages = new Pager<UserDto>(count, updateUserDto(list));
		return pages;
	}

	/**
	 * 获取公司下用户分页列表集合信息
	 * 
	 * @return 获取用户分页列表集合信息
	 */
	public Pager<UserDto> findCorpConsoles(int corp_id, int rank) {
		// 获取用户总数
		int count = userDao.findCorpConsoleCount(corp_id, rank);

		// 封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		// 获取用户分页列表集合信息
		List<User> list = userDao.findCorpConsole(pageBounds, corp_id, rank);

		// 封装用户分页的Pager对象
		Pager<UserDto> pages = new Pager<UserDto>(count, updateUserDto(list));
		return pages;
	}

	/*
	 * Return all console list at specified rank, not at page
	 */
	@Override
	public List<User> findCorpConsolesList(int corp_id, int rank) {
		// 获取用户分页列表集合信息
		List<User> list = userDao.findCorpConsoleList(corp_id, rank);

		return list;

	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 *            用户id
	 * @return 用户信息
	 */
	@Override
	public User load(Long id) {
		return userDao.load(id);
	}

	public List<User> ListCorpConsoles(int id) {
		return userDao.listCorpConsoles(id);
	}

	/**
	 * 根据用户id获取此用户所对应所有的群组id集合
	 * 
	 * @param id
	 *            用户id
	 * @return 获取此用户所对应所有的群组id集合
	 */
	@Override
	public List<Long> listUserGroupIds(Long id) {
		List<UserGroup> listUserGroup = userDao.listUserGroupIds(id);
		List<Long> ids = new ArrayList<Long>();
		for (UserGroup usergroup : listUserGroup) {
			ids.add(usergroup.getGroupId());
		}
		return ids;
	}

	@Override
	public List<Group> listGroupsByUid(Long id) {
		return groupDao.listGroupsByUid(id);
	}

	@Override
	public List<UserGroup> listGroupUsers(Long gid) {
		return userDao.listGroupUsers(gid);
	}

	/**
	 * 更新用户基本信息
	 * 
	 * @param user
	 *            用户对象
	 */
	@Override
	public List<User> listUsersByConsoleId(Long id) {
		return userDao.listUsersByConsoleId(id);

	}

	@Override
	public List<Long> listUsersIdByConsoleId(Long id) {

		return userDao.listUsersIdByConsoleId(id);

	}

	@Override
	public List<Long> listSecMyAdmins(Long uid) {
		return userDao.listUserAmdinIds(uid);
	}

	@Override
	public void updatePwd(Long uid, String oldPwd, String newPwd) {

		User u = userDao.load(uid);
		User tmp = new User();
		tmp.setUserPassword(oldPwd);
		encryptPassword(tmp);
		if (!tmp.getUserPassword().equals(u.getUserPassword())) {
			throw new CmsException("原始密码输入不正确");
		}
		u.setUserPassword(newPwd);
		encryptPassword(u);
		// u.setUserPassword(SecurityUtil.md5(newPwd));
		userDao.update(u);

	}

	/**
	 * 更新用户基本信息
	 * 
	 * @param user
	 *            用户对象
	 */
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	public List<User> listUsersByGid(Long id) {
		return userDao.listUsersByGid(id);
	}

	public List<User> listConsolesByCorpId(int copId) {

		return userDao.listConsolesByCorpId(copId);
	}

	// delete admin's all user
	@Override
	public void deleteAdminUsers(Long admId) {
		userDao.deleteAdminUsers(admId);
	}

	// delete all the admin user map for that user
	@Override
	public void deleteUserAdmins(Long uid) {
		userDao.deleteUserAdmins(uid);
	}

	@Override
	public void deleteUserAdmin(Long admId, Long uid) {
		userDao.deleteUserAdmin(admId, uid);
	}

	@Override
	public List<String> listUserGroupNames(Long uid) {
		return userDao.listUserGroupNames(uid);
	}

	@Override
	public User updateOneMyTs(User user, long uid, MyTsType type, boolean doSave, int value) {
		User tmpUser = userDao.load(uid);

		User result = null;
		long tmpValue = 0;
		if (tmpUser == null) {

			logger.error("Invalid UID=" + uid);
			return null;
		}
		switch (type) {
		case PROFILE:
			if (user == null) {

				tmpUser.setTsProfile(tmpUser.getTsProfile() + value);
				result = tmpUser;
			} else { // if((tmpValue=user.getTsProfile())==tmpUser.getTsProfile()){
				tmpValue = user.getTsProfile();
				user.setTsProfile((tmpValue + value));
				result = user;
			}
			break;

		case GROUP:
			if (user == null) {
				tmpUser.setTsGroup(tmpUser.getTsGroup() + value);
				result = tmpUser;
			} else {// if((tmpValue=user.getTsGroup())==tmpUser.getTsGroup()){
				tmpValue = user.getTsGroup();
				user.setTsGroup(tmpValue + 1);
				result = user;
			}
			break;
		case ADMIN:
			if (user == null) {
				tmpUser.setAdmTs(tmpUser.getAdmTs() + value);
				result = tmpUser;
			} else {// if((tmpValue=user.getTsGroup())==tmpUser.getTsGroup()){
				tmpValue = user.getAdmTs();
				user.setAdmTs(tmpValue + 1);
				result = user;
			}
			break;
		default:

		}
		if ((doSave == true) && (result != null)) {
			// updateUser(result);
			userDao.update(result);
		}
		return result;

	}

	// Pager<UserDto> pages = new Pager<UserDto>(count,updateUserDto(list));
	public List<UserDto> selectUserDtoListByCondition(Map<String, Object> map) {
		List<User> userList = userDao.selectListByCondition(map);
		return updateUserDto(userList);
	}

	public List<User> selectListByCondition(Map<String, Object> map) {
		return userDao.selectListByCondition(map);
	}

	public User selectByCondition(Map<String, Object> map) {
		return userDao.selectByCondition(map);
	}

	public int selectCountByCondition(Map<String, Object> map) {
		return userDao.selectCountByCondition(map);
	}

	public User selectByPrimaryKey(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	/**
	 * 获取console分页列表集合信息
	 */
	@Override
	public JSONObject findConsoles(JSONObject json, Map<String, Object> map) {
		// 获取用户总数
		// corp_id,rank
		// int count = userDao.findConsoleCount(rank);
		/*
		 * //封装PageBounds对象 PageBounds pageBounds =
		 * PageBoundsUtil.PageBoundsOrderExtend("id.desc"); //获取用户分页列表集合信息
		 * List<User> list = userDao.findConsole(pageBounds,rank);
		 * Pager<UserDto> pages = new Pager<UserDto>(count,updateUserDto(list));
		 */

		int total = userDao.selectCountByCondition(map);
		List<User> userList = userDao.selectListByCondition(map);
		List<UserDto> dataList = updateUserDto(userList);
		return Result.gridData(dataList, total, json);
	}

	@Override
	public JSONObject selectGroupDtoListByUserCondition(JSONObject json, Map<String, Object> map) {
		int total = userDao.selectCountByUserCondition(map);// 获取公司下群组总数
		List<User> list = userDao.selectListByUserCondition(map);// 获取用户分页列表集合信息
		for (User user : list) {
			System.out.println(user.getIsJoin());
		}
		List<UserDto> dataList = new ArrayList<UserDto>();
		for (User user : list) {
			UserDto userDto = new UserDto(user);
			dataList.add(userDto);
		}
		return Result.gridData(dataList, total, json);
	}

	@Override
	public List<UserGroup> listGroupUserId(Long id) {
		// TODO Auto-generated method stub
		return userDao.listGroupUserId(id);
	}

	@Override
	public List<User> listGroupAllUsers(Long id) {
		// TODO Auto-generated method stub
		return userDao.listGroupAllUsers(id);
	}

	@Override
	public List<User> listAdminAllUsers(Long id) {
		// TODO Auto-generated method stub
		return userDao.listAdminAllUsers(id);
	}

	@Override
	public User loadByUsername(String string) {
		// TODO Auto-generated method stub
		return userDao.loadByUsername(string);
	}

	@Override
	public void deleteByCorpId(Integer corpId) {
		userDao.deleteByCorpId(corpId);

	}

	@Override
	public void deleteByAdminId(Long id) {
		// TODO Auto-generated method stub
		userDao.deleteByAdminId(id);
	}

	@Override
	public JSONObject selectConsolerDtoListByUserCondition(JSONObject json, Map<String, Object> map) {
		int total = userDao.selectCountByUserCondition(map);// 获取公司下群组总数
		List<User> list = userDao.selectConsolerDtoListByUserCondition(map);// 获取用户分页列表集合信息
		List<UserDto> dataList = new ArrayList<UserDto>();
		for (User user : list) {
			UserDto userDto = new UserDto(user);
			dataList.add(userDto);
		}
		return Result.gridData(dataList, total, json);
	}

	@Override
	public void addConsolerUsers(Long adminId, List<Long> uids) {
		if (uids == null)
			return; // No any users
		for (int i = 0; i < uids.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminId", adminId);
			map.put("userId",  uids.get(i));
			UserAdmin userAdmin =  userDao.loadUserAdmin(map);
			if(null != userAdmin){continue;}
			userDao.addUserAdmin(uids.get(i), adminId);
		}
	}

	@Override
	public void delConsolerUsers(Long gid, List<Long> uids) {
		if (uids == null)
			return; // No any users
		for (int i = 0; i < uids.size(); i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("adminId", gid);
//			map.put("userId",  uids.get(i));
//			UserAdmin userAdmin =  userDao.loadUserAdmin(map);
//			if(null != userAdmin){continue;}
			userDao.deleteUserAdmin(gid, uids.get(i));
		}
	}

	@Override
	public JSONObject selectConsolerDtoList2ByUserCondition(JSONObject json, Map<String, Object> map) {
		int total = userDao.selectCountByUserCondition(map);// 获取公司下群组总数
		List<User> list = userDao.selectConsolerDtoList2ByUserCondition(map);// 获取用户分页列表集合信息
		List<UserDto> dataList = new ArrayList<UserDto>();
		for (User user : list) {
			UserDto userDto = new UserDto(user);
			dataList.add(userDto);
		}
		return Result.gridData(dataList, total, json);
	}

	@Override
	public JSONObject selectConsolerDtoList1ByUserCondition(JSONObject json, Map<String, Object> map) {
		int total = userDao.selectCountByUserCondition(map);// 获取公司下群组总数
		List<User> list = userDao.selectConsolerDtoList1ByUserCondition(map);// 获取用户分页列表集合信息
		List<UserDto> dataList = new ArrayList<UserDto>();
		for (User user : list) {
			UserDto userDto = new UserDto(user);
			dataList.add(userDto);
		}
		return Result.gridData(dataList, total, json);
	}

	

}
