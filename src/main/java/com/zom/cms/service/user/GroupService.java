package com.zom.cms.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zom.cms.dao.user.IGroupDao;
import com.zom.cms.dao.user.IUserDao;
import com.zom.cms.dto.GroupDto;
import com.zom.cms.lh.tools.Result;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.Group;
import com.zom.cms.model.User;
import com.zom.cms.model.UserGroup;
import com.zom.cms.page.PageBoundsUtil;
import com.zom.cms.page.Pager;
import com.zom.cms.service.ShortDataService;

@Service("groupService")
public class GroupService implements IGroupService{
	private IGroupDao groupDao;
	private IUserDao userDao;



	@Autowired
	private ShortDataService sdService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdService idService;

	public IGroupDao getGroupDao() {
		return groupDao;
	}
	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	


	/**
	 * 添加群组
	 * @param group 群组对象
	 */
	@Override
	public Group add(GroupDto groupDto) {

		Group group=groupDto.getUser();
		if(group.getOwnerId()==null){
			throw new CmsException("请选择一个组管理员，如果系统没有任何用户，请先创建用户");
		}		
		group.setCreateDate(new Date());
		
		group.setPttid(0l);
		group.setPttTime(new Date());
		group.setGroupTs(1l);

		group.setGroupNameEasemob("test");
		//group.setRank(0);  
		group.setStatus(1); //enable group
		group.setPreconfig(1); //all are preconfig is the add is from management system
		if(group.getDcg()!=1){
			long gid=idService.getZoneCurTgid(group.getZoneId());
			group.setId(gid);			
		}
		
		groupDao.add(group);
	

		
	   //check if group owner is in the users list, if not, add in
		List<Long> idsList= groupDto.getUserIds();
		if(idsList==null){
			idsList= new ArrayList<Long>();
		}
		if(!idsList.contains(group.getOwnerId())){
			idsList.add(group.getOwnerId());
		}
		
		addGroupUsers(group.getId(),idsList,group.getOwnerId());
		//TODO 获取添加后的id
//		return 0;
		return group;
	}
	

	/**
	 * 删除群组信息(在删除之前先判断群组中是否有用户信息)
	 * @param id 群组id
	 */
	@Override
	public int delete(Long id){
		//根据群组id获取群组下用户的信息(在删除之前先判断群组中是否有用户信息)
		
		try{
			//根据群组id删除群组信息
		//	String nameString= groupDao.findGroupEasemobNameById(id);
			sdService.deleteChatGroupByGname(groupDao.findGroupEasemobNameById(id));
			return groupDao.delete(id);
		}catch(Exception e){
			throw new CmsException("删除组失败");
		}
	}

	/**
	 * 根据群组id获取群组信息
	 * @param id 群组id
	 * @return 群组
	 */
	@Override
	public Group load(Long id) {
		return groupDao.load(id);
	}

	/**
	 * 更新群组信息
	 * @param group 群组对象
	 */
	@Override
	public int update(Group group) {
		return groupDao.update(group);
	}
	
	/**
	 * 获取群组列表信息
	 * @return 群组列表信息
	 */
	@Override
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}
	
	/**
	 * 获取公司下群组列表信息
	 * @return 群组列表信息
	 */
	public List<Group> listCorpGroup(int corp_id){
		return groupDao.listCorpGroup(corp_id);
	}
	/**
	 * 获取分页群组列表信息
	 * @return 分页群组列表信息
	 */
	@Override
	public Pager<GroupDto> findGroup() {
		//获取群组总数
		int count = groupDao.findGroupCount();
		//封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		//获取用户分页列表集合信息
		List<Group> list = groupDao.findGroup(pageBounds);
		   List <GroupDto> groupDtoList = new ArrayList<GroupDto>();
			
			for (int i=0; i<list.size(); i++)
			{
				User user=userDao.load(list.get(i).getOwnerId());
				
				String ownerName=user.getDisplayName();;
				//if(user!=null){
				//	adminName=user.getDisplayName();
				//}
				


				GroupDto groupDto=new GroupDto(list.get(i),null,userDao.listCorpConsoles(list.get(i).getCorpId()));
				groupDto.setOwnerName(ownerName);
				groupDtoList.add(groupDto);
			}
			//封装用户分页的Pager对象
			Pager<GroupDto> pages = new Pager<GroupDto>(count,groupDtoList);
			return pages;		
	}

	/**
	 * 获取分页公司群组列表信息
	 * @return 分页公司群组列表信息
	 */
	public Pager<GroupDto> findGroupCorp(int id){
		//获取公司下群组总数
		int count = groupDao.findGroupCorpCount(id);
		System.out.printf("corp groups=%d",count);
		//封装PageBounds对象
		PageBounds pageBounds = PageBoundsUtil.PageBoundsOrderExtend("id.desc");
		//获取用户分页列表集合信息
		List<Group> list = groupDao.findCorpGroup(pageBounds,id);
		
	   List <GroupDto> groupDtoList = new ArrayList<GroupDto>();
		
		for (int i=0; i<list.size(); i++)
		{
			User user=userDao.load(list.get(i).getOwnerId());
			String ownerName=user.getDisplayName();


			GroupDto groupDto=new GroupDto(list.get(i),null,userDao.listCorpConsoles(list.get(i).getCorpId()));
            groupDto.setOwnerName(ownerName);
			groupDtoList.add(groupDto);
		}
		//封装用户分页的Pager对象
		Pager<GroupDto> pages = new Pager<GroupDto>(count,groupDtoList);
		return pages;		
	}
	
	
	/**
	 * 删除群组中的用户信息
	 * @param gid 群组id
	 */
	@Override
	public int deleteGroupUsers(Long gid) {
		Group group = groupDao.load(gid);
		updateOneMyTs(group, group.getId(), MyTsType.GROUP, true, 1);
	//	groupDao.update(group);
		//update the group TS in user table
		List<User> lu=userDao.listUsersByGid(gid);
		for (User u:lu){
			userService.updateOneMyTs(u, u.getId(), IUserService.MyTsType.GROUP, true, 1);
		}
		return userDao.deleteGroupUsers(gid);
	}
	

	/**
	 * 删除群组中的用户信息
	 * @param gid 群组id
	 */
	@Override
	public void deleteUserGroups(Long uid) {
		List <Group> gs=groupDao.listGroupsByUid(uid);
		for (Group g:gs ){
			updateOneMyTs(g, g.getId(), MyTsType.GROUP, true, 1);
		}

		userDao.deleteUserGroups(uid);
	}
	
	@Override
	public void updateStatus(Long id){
		Group group = groupDao.load(id);
		if(group!=null){
			int status =group.getStatus();
			if(status==1){ //is on disable state
				group.setStatus(0); //set it to enable state
			}else if(status==0){
				group.setStatus(1); //set it to disable state
			}
			
			
			//update group ts
			Group tmpGroup=updateOneMyTs(group,group.getId(),MyTsType.GROUP,true,1);
			
		//	groupDao.update(tmpGroup); //update database;
			
			//Find all users in this group, and update its GroupTs
			List<Long> idsList=listGroupUserId(id);
			if(idsList!=null){
				for(int i=0;i<idsList.size();i++){
					User user=userService.load(idsList.get(i));
					userService.updateOneMyTs(user, idsList.get(i), IUserService.MyTsType.GROUP, true, 1);
				//	userDao.update(user);
				}
			}
			
		}
	}





	
	
	/**
	 * 增加组用户
	 * @param gid 群组id
	 * @param uid 用户id
	 */
	@Override
	public void addGroupUsers(Long gid, List<Long>uids,Long ownerId) {
		if(uids==null) return; //No any users
		for(int i=0; i<uids.size();i++){
			//get user priority
			User u= userDao.load(uids.get(i).longValue());
			int priority =u.getPriority();
            if(ownerId.longValue() == uids.get(i).longValue()){
            	priority = priority<5? 5:priority;
            }
         // 2、检查用户 组对象是否已经存在，如果存在，就不添加
    		UserGroup ug = userDao.loadUserGroup(u.getId(), gid);
    		if (ug != null){continue;}
            userService.updateOneMyTs(u, u.getId(), IUserService.MyTsType.GROUP, true, 1);
			userDao.addUserGroup(uids.get(i), gid,Long.valueOf(priority));
           // userDao.addUserGroup(uids.get(i), gid,5l);
		}
		//update group's Ts
		
		Group group = groupDao.load(gid);
		updateOneMyTs(group, group.getId(), MyTsType.GROUP, true, 1);
	//	groupDao.update(group);
	}
	@Override
	public void delGroupUsers(Long gid, List<Long>uids,Long ownerId) {
		if(uids==null) return; //No any users
		for(int i=0; i<uids.size();i++){
			
			//get user priority
			User u= userDao.load(uids.get(i).longValue());
			int priority =u.getPriority();
			if(ownerId.longValue() == uids.get(i).longValue()){
				priority = priority<5? 5:priority;
			}
			userService.updateOneMyTs(u, u.getId(), IUserService.MyTsType.GROUP, true, 1);
			userDao.deleteUserGroup(uids.get(i), gid);
			// userDao.addUserGroup(uids.get(i), gid,5l);
		}
		//update group's Ts
		
		Group group = groupDao.load(gid);
		updateOneMyTs(group, group.getId(), MyTsType.GROUP, true, 1);
		//	groupDao.update(group);
		
	}
	
	
	/**
	 * 找到最后假的组
	 *
	 * 
	 */
	@Override
	public Long lastInsertGroupId(){
     return groupDao.lastInsertGroupId();
	}
	
	/**
	 * 找到最后假的组
	 *
	 * 
	 */
	@Override
	public Group updateOneMyTs(Group group,long gid,MyTsType type, boolean doSave ,int value ){
		Group tmpUser =groupDao.load(gid);
		
		Group result=null;
		long tmpValue=0;
		if(tmpUser == null){
			
			
			return null;
		}
		switch(type){
		case PROFILE:
		
			break;
			
		case GROUP:
			if(group==null){
				tmpUser.setGroupTs(tmpUser.getGroupTs()+value);
				result = tmpUser;
			}else{// if((tmpValue=group.getGroupTs())==tmpUser.getGroupTs()){
				tmpValue=group.getGroupTs();
				group.setGroupTs(tmpValue+1);
				result=group;
			}
			break;
		default:
			
		}
		if((doSave==true)&&(result!=null)){
			//updateUser(result);
			groupDao.update(result);
		}
		return result;
		
	}
	
	@Override
	public List<Long> listGroupUserId(Long gid) {
		
		List<UserGroup> listUserGroup = userDao.listGroupUserId(gid);
		List<Long> ids = new ArrayList<Long>();
		for(UserGroup usergroup: listUserGroup){
			ids.add(usergroup.getUserId());
		}
		
		//List<Long> ids =userDao.listGroupUserId(gid);
		return ids;
		
	}
	@Override
	public int findCorpGroupCount(int corp_id){
		int count = groupDao.findGroupCorpCount(corp_id);
		return count;
	}
	@Override
	public void update(Group group,  List<Long> userIds) {

         update(group);
         boolean updateGrp=false;
		
		List<UserGroup> listGroupUser =  userDao.listGroupUserId(group.getId());

		
		List<Long> ouids = new ArrayList<Long>();
		for(UserGroup usergroup: listGroupUser){
			ouids.add(usergroup.getUserId());
		}

		//2、判断，如果ouids中不存在euids就要进行添加
        if(userIds ==null){
        	//delete all users
        	updateGrp=true;
    		for(Long ouid:ouids) {
    					
    			userDao.deleteUserGroup(ouid, group.getId());
    			updateGrp=true;
    			User user=userDao.load(ouid);
    			userService.updateOneMyTs(user, ouid, IUserService.MyTsType.GROUP, true, 1);
    		//	userDao.update(user);
    				
    			

    		}
        	
        }else{
    		for(Long userId:userIds) {
    			if(!ouids.contains(userId)) {		
    				
    				userDao.addUserGroup(userId, group.getId(),Long.valueOf(userDao.load(userId).getPriority()) );
    				updateGrp=true;
    				User user=userDao.load(userId);
    				userService.updateOneMyTs(user, userId, IUserService.MyTsType.GROUP, true, 1);
    				//userDao.update(user);
    			}
    		}
    		
          
    		//3, 判断 userId 里面如果没有 这个用户，就要删除 
    		for(Long ouid:ouids) {
    			if(!userIds.contains(ouid)) {				
    				userDao.deleteUserGroup(ouid, group.getId());
    				updateGrp=true;
    				User user=userDao.load(ouid);
    				userService.updateOneMyTs(user, ouid, IUserService.MyTsType.GROUP, true, 1);
    			//	userDao.update(user);
    				
    			}

    		}
        }

		if(updateGrp==true){
			updateOneMyTs(group, group.getId(), MyTsType.GROUP, true, 1);
		//	update(group);
		}
	}

	
	public JSONObject selectGroupDtoListByCondition(JSONObject json, Map<String, Object> map) {
		int total = groupDao.selectCountByCondition(map);//获取公司下群组总数
		List<Group> list = groupDao.selectListByCondition(map);//获取用户分页列表集合信息
	    List <GroupDto> dataList = new ArrayList<GroupDto>();
		for (Group group : list){
			User user = userDao.load(group.getOwnerId());
			if(null == user)continue;
			String ownerName = user.getDisplayName();
			GroupDto groupDto = new GroupDto(group, null, userDao.listCorpConsoles(group.getCorpId() ));
            groupDto.setOwnerName(ownerName);
            dataList.add(groupDto);
		}
		return Result.gridData(dataList, total, json);
	}
	
	public JSONObject selectGroupDtoListByUserCondition(JSONObject json, Map<String, Object> map) {
		int total = groupDao.selectCountByUserCondition(map);//获取公司下群组总数
		List<Group> list = groupDao.selectListByUserCondition(map);//获取用户分页列表集合信息
		List <GroupDto> dataList = new ArrayList<GroupDto>();
		for (Group group : list){
			User user = userDao.load(group.getOwnerId());
			if(null == user)continue;
			String ownerName = user.getDisplayName();
			GroupDto groupDto = new GroupDto(group, null, userDao.listCorpConsoles(group.getCorpId() ));
			groupDto.setOwnerName(ownerName);
			dataList.add(groupDto);
		}
		return Result.gridData(dataList, total, json);
	}
	
	
	public List<Group> selectListByCondition(Map<String, Object> map) {
		return groupDao.selectListByCondition(map);
	}
	public Group selectByCondition(Map<String, Object> map) {
		return groupDao.selectByCondition(map);
	}
	public int selectCountByCondition(Map<String, Object> map) {
		return groupDao.selectCountByCondition(map);
	}
	public Group selectByPrimaryKey(Integer id) {
		return groupDao.selectByPrimaryKey(id);
	}
	@Override
	public void deleteByCorpId(Integer corpId) {
		groupDao.deleteByCorpId(corpId);
		
	}
	
}
