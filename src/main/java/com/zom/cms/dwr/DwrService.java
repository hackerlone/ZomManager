package com.zom.cms.dwr;

import javax.inject.Inject;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.zom.cms.service.user.IGroupService;

@RemoteProxy(name="dwrService")
public class DwrService implements IDwrService{
	private IGroupService groupService;

	public IGroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

}
