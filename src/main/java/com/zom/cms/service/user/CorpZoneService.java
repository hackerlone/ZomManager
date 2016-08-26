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
public class CorpZoneService implements ICorpZoneService {// extends
																	// CommonService<Corporation>{//
	@Autowired
	private ICorporationDao corpDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserService userService;
	@Override
	public void addCorpZone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		corpDao.addCorpZone(map);
	}



}
