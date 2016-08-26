package com.zom.cms.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zom.cms.dao.user.IChargeDao;
import com.zom.cms.dao.user.IConsolerUserDao;
import com.zom.cms.dao.user.IUserDao;
import com.zom.cms.model.ConsolerUser;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.service.user.IGroupService.MyTsType;

@Service("chargeService")
public class ChargeService implements IChargeService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private IChargeDao chargeDao;
	private IUserDao userDao;
	public IChargeDao getChargeDao() {
		return chargeDao;
	}
	@Inject
	public void setChargeDao(IChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
