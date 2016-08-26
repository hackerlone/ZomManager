package com.zom.cms.dao.user;

import java.util.List;
import java.util.Map;

import com.zom.cms.model.ConsolerUser;
import com.zom.cms.model.Group;

public interface IConsolerUserDao extends CommonMapper<ConsolerUser> {

	public List<ConsolerUser> selectByconsolerId(Long id);

	public int add(ConsolerUser consolerUser);

	public void deleteByConsolerId(Long id);

	public void addConsoler1To2(ConsolerUser consolerUser);

	public void delConsoler1To2(ConsolerUser consolerUser);

	public ConsolerUser loadUserConsole(Map<String, Object> map);
		



}
