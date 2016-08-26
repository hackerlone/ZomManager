package com.zom.cms.service.user;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.basic.util.SecurityUtil;
import com.zom.cms.dao.user.ICorporationDao;
import com.zom.cms.dao.user.IUserDao;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.Corporation;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.ZoneIdAssign;
import com.zom.cms.page.PageBoundsUtil;
import com.zom.cms.page.Pager;

@Service
public class CorporationService1 implements ICorporationService1 {// extends
																	// CommonService<Corporation>{//
	@Autowired
	private ICorporationDao corpDao;
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	@Override
	public void add(Corporation coporation, int length) {
		Corporation tu = corpDao.loadByUsername(coporation.getUsername());
		if (tu != null)
			throw new CmsException("添加的用户对象已经存在，不能添加");

		tu = corpDao.loadByPhone(coporation.getPhone());
		if (tu != null)
			throw new CmsException("用户手机已被注册，不能添加");
		Calendar rightNow = Calendar.getInstance();

		rightNow.add(Calendar.DAY_OF_YEAR, length);// 日期加10天

		coporation.setRegisterDate(new Date());
//		coporation.setExpireDate(rightNow.getTime());
		coporation.setLastLogonDate(new Date());
		coporation.setStatus(1); // 始能用户
		try {
			coporation.setCorpPassword(SecurityUtil.md5(coporation.getCorpPassword()));
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("密码加密失败:" + e.getMessage());
		}
		corpDao.add(coporation);
	}

	@Override
	public void delete(int id) {
		// TODO 需要进行用户是否有文章的判断

		// find all users in the corp
		List<User> users = userDao.findCorpAllUser(id);
		// delete all user group map and all admins
		for (User u : users) {

			userService.delete(u.getId());

		}

		// find all admin in the corp

		List<User> admins = userDao.listCorpConsoles(id);
		for (User a : admins) {
			userService.delete(a.getId());
		}

		// find all group in the corp
		List<Group> groups = groupService.listCorpGroup(id);
		for (Group g : groups) {
			groupService.deleteGroupUsers(g.getId());
			groupService.delete(g.getId());
		}

		// 3、删除公司用户
		corpDao.delete(id);
	}

	/*
	 * @Override public void update(User user, Integer[] rids, Integer[] gids) {
	 * //1、获取用户已经存在的组id和角色id List<UserRole> listUserRole =
	 * userDao.listUserRoleIds(user.getId()); List<Integer> erids = new
	 * ArrayList<Integer>(); for(UserRole userrole: listUserRole){
	 * erids.add(userrole.getRoleId()); }
	 * 
	 * List<UserGroup> listUserGroup = userDao.listUserGroupIds(user.getId());
	 * List<Integer> egids = new ArrayList<Integer>(); for(UserGroup usergroup:
	 * listUserGroup){ egids.add(usergroup.getGroupId()); }
	 * 
	 * //2、判断，如果erids中不存在rids就要进行添加 for(Integer rid:rids) {
	 * if(!erids.contains(rid)) { addUserRole(user, rid); } } for(Integer
	 * gid:gids) { if(!egids.contains(gid)) { addUserGroup(user,gid); } }
	 * 
	 * //3、进行删除 for(Integer erid:erids) { if(!ArrayUtils.contains(rids, erid)) {
	 * userDao.deleteUserRole(user.getId(), erid); } }
	 * 
	 * for(Integer egid:egids) { if(!ArrayUtils.contains(gids, egid)) {
	 * userDao.deleteUserGroup(user.getId(), egid); } } }
	 * 
	 * @Override public void updateStatus(int id) { User u = userDao.load(id);
	 * if(u==null) throw new CmsException("修改状态的用户不存在"); if(u.getStatus()==0)
	 * u.setStatus(1); else u.setStatus(0); userDao.update(u); }
	 */
	/**
	 * 获取用户分页列表集合信息
	 */

	@Override
	public Pager<Corporation> findUser() {
		// 获取用户总数
		int count = corpDao.findUserCount();
		// System.out.printf("count=%d", count);
		// 封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		// 获取用户分页列表集合信息
		List<Corporation> list = corpDao.findUser(pageBounds);
		// 封装用户分页的Pager对象
		Pager<Corporation> pages = new Pager<Corporation>(count, list);
		return pages;
	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 *            用户id
	 * @return 用户信息
	 */
	@Override
	public Corporation load(int id) {
		return corpDao.load(id);
	}

	@Override
	public Corporation login(String username, String password) {
		Corporation user = corpDao.loadByUsername(username);
		if (user == null)
			throw new CmsException("用户名不正确");
		if (user.getStatus() == 2)
			throw new CmsException("该账号已被删除");
		try {
			if (!SecurityUtil.md5(password).equals(user.getCorpPassword())) {
				throw new CmsException("密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("密码加密失败:" + e.getMessage());
		}

		if (user.getStatus() == 0)
			throw new CmsException("用户已经停用，请与零壹众公司联系");
		return user;
	}

	/**
	 * 更新用户基本信息
	 * 
	 * @param user
	 *            用户对象;
	 */
	@Override
	public void update(Corporation corporation) {

		// check if there is duplicated phone and username
		Corporation tu = corpDao.loadByPhone(corporation.getPhone());
		if ((tu != null) && (tu.getId() != corporation.getId())) {
			throw new CmsException("用户手机已被注册，请用其他手机");
		}
		/*
		 * tu=userDao.loadByUsername(corporation.getUsername()); if((tu!=null)
		 * &&(tu.getId()!=corporation.getId())){ throw new
		 * CmsException("用户名已被使用，请用其他用户名"); }
		 */
		corpDao.update(corporation);
	}

	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {

		try {
			Corporation u = corpDao.load(uid);

			if (!SecurityUtil.md5(oldPwd).equals(u.getCorpPassword())) {
				throw new CmsException("原始密码输入不正确");
			}
			u.setCorpPassword(SecurityUtil.md5(newPwd));
			corpDao.update(u);
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("更新密码失败:" + e.getMessage());
		}

	}

	@Override
	public void updateStatus(int id) {
		Corporation user = corpDao.load(id);
		if (user != null) {
			int status = user.getStatus();
			if (status == 0) { // is on disable state
				user.setStatus(1); // set it to enable state
			} else if (status == 1) {
				user.setStatus(0); // set it to disable state
			}

			corpDao.update(user);

		}

	}

	public List<Corporation> selectListByCondition(Map<String, Object> map) {
		return corpDao.selectListByCondition(map);
	}

	public Corporation selectByCondition(Map<String, Object> map) {
		return corpDao.selectByCondition(map);
	}

	public int selectCountByCondition(Map<String, Object> map) {
		return corpDao.selectCountByCondition(map);
	}

	public Corporation selectByPrimaryKey(Integer id) {
		return corpDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ZoneIdAssign> selectAllAssign() {
		// TODO Auto-generated method stub
		return corpDao.selectAllAssign();
	}

	@Override
	public Corporation loadByPhone(String phone) {
		// TODO Auto-generated method stub
		return corpDao.loadByPhone(phone);
	}

}
