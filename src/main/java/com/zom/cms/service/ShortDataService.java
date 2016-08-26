package com.zom.cms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zom.cms.im.easemob.comm.Constants;
import com.zom.cms.im.easemob.jersey.apidemo.EasemobChatGroups;
import com.zom.cms.im.easemob.jersey.apidemo.EasemobIMUsers;
import com.zom.cms.im.easemob.jersey.apidemo.EasemobMessages;
import com.zom.cms.im.shortdata.ShortDataType;

@Component
@Transactional
public class ShortDataService {
	private static Logger logger = LoggerFactory.getLogger(ShortDataService.class);
	private static final String SDNAME = "SD";

	/**
	 *  
	 * @param userName
	 *           环信的用户名，比如：u10000
	 * @return uid: 
	 * 	 		VBTS端用户的id
	 */	
	public  int convertUsernameToUid(String userName){
		int uid = -1;
		uid = Integer.valueOf(userName.substring(1)).intValue();
		return uid;
	}
	
	/**
	 * 
	 * @param uid
	 *           VBTS端用户ID，比如10000
	 * @return String: 
	 * 	 		userName: 环信的用户名，比如:u10000
	 */	
	public  String convertuidToUsername(Long uid){
		if(uid <= 0){
			logger.error("error, the input uid is invalid: %d ", uid);
			return "";
		}
		String userName = "u" + String.valueOf(uid);
		return userName;
	}
	
	/**
	 * 
	 * @param tgid： gid是VBTS端的group ID
	 * @param owner：环信上创建群组必须有一个所有者：owner，就是某个环信用户名，比如u10000
	 * @return groupName: 
	 * 	 		创建的环信群组名字，是环信上一个群组的唯一标识
	 */	
	public  String createChatGroupByGid(int tgid, String owner/*username of group owner*/){
		ObjectNode ret = null;
		String groupName = "g" + String.valueOf(tgid);
		if(Constants.EASEMOB_REGISTER.equalsIgnoreCase("false")){
			return groupName;
		}
		ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
		dataObjectNode.put("groupname", groupName);
		dataObjectNode.put("desc", "Group Created from Easemob REST interface");
		dataObjectNode.put("approval", false);
		dataObjectNode.put("public", false);
		dataObjectNode.put("owner", owner);

		try{
			ret = EasemobChatGroups.creatChatGroups(dataObjectNode);
		} catch (Exception e) {
			logger.error("Exception in EasemobChatGroups.creatChatGroups(). createChatGroupByGid() failed ");
			return "";
		}
		
		if ((ret != null) ){
			ret = (ObjectNode) ret.get("data");
			if(ret != null){
				groupName = ret.get("groupid").asText();
			}else{
				logger.error("error: create chatgroup failed ");
				return "";
			}
		}else{
			logger.error("error: create chatgroup failed, return node is null");
			return "";
		}
		return groupName;
	}

	/**
	 * 
	 * @param uid
	 *           uid是VBTS端用户ID
	 * @return value: ObjectNode
	 *           key: username, password, if no username key returned, the register failed!
	 */
	public  ObjectNode registerUserByUid(Long uid/*uid in VBTS database*/){
		ObjectNode newUser = JsonNodeFactory.instance.objectNode();
		if(newUser == null){
			logger.error("Could not allocate buffer for newUser in registerUserByUid()\n");
			return null;
		}
		String userName = convertuidToUsername(uid);
		if (userName == ""){
			return null;
		}
		ObjectNode user = EasemobIMUsers.getIMUsersByPrimaryKey(userName);
		if (user != null && user.has("message")){
			logger.error("Query existing HX username failed. error message:%s", user.get("message"));
			return null;
		}else if(user != null && user.has("username") && user.get("username").toString().equals(userName)){
			newUser.put("username", userName);
			newUser.put("password", userName);
		}else{
			System.out.print("get username\n");
			newUser.put("username", userName);
			newUser.put("password", userName);
			ObjectNode ret = EasemobIMUsers.createNewIMUserSingle(newUser);

			if ((ret == null)){
				logger.error("error: EasemobIMUsers.createNewIMUserSingle() returns null, create new user failed!\n ");
				newUser.removeAll();
			}else if (ret.has("message")){
				logger.error("EasemobIMUsers.createNewIMUserSingle() failed, error: %s", ret.get("message"));
				newUser.removeAll();
			}
		}
		return newUser;
	}	
	
	/**
	 * 
	 * @param userName
	 *           userName：环信用户名，比如u10000
	 * @return bool: 
	 * 	 		true：删除操作成功
	 */		
	public  boolean deleteChatUserByUname(String userName){
		ObjectNode ret = null;
		try{
			ret = EasemobIMUsers.deleteIMUserByUserPrimaryKey(userName);
		} catch (Exception e) {
			logger.error("Exception in EasemobIMUsers.deleteIMUserByUserPrimaryKey(). deleteChatUserByUname() failed ");
			return false;
		}
		if (ret == null){
			logger.error("The delete user operation failed. ret is null\n");
			return false;
		}else if(ret.has("message")){
			logger.error("The delete user operation failed. error:%s\n", ret.get("message"));
			return false;
		}		
		return true;
	}
	
	/**
	 * 
	 * @param groupName
	 *           groupName是环信端群组的名字，是其唯一标识
	 * @return bool: 
	 * 	 		true：删除成功
	 */		
	public  boolean deleteChatGroupByGname(String groupName){
		ObjectNode ret = null;
		try{
			ret = EasemobChatGroups.deleteChatGroups(groupName);
		} catch (Exception e) {
			logger.error("Exception in EasemobChatGroups.deleteChatGroups(). deleteChatGroupByGname() failed ");
			return false;
		}
		if (ret == null){
			logger.error("The sendMessage failed. ret is null\n");
			return false;
		}else if(ret.has("message")){
			logger.error("The sendMessage failed. error:%s\n", ret.get("message"));
			return false;
		}		
		return true;
	}
	/**
	 * 
	 * @param userName:环信用户名，比如u10000
	 *        groupName：环信群组名，比如：“1426674491587427”
	 * @return bool: 
	 * 	 		true：操作成功
	 */			
	public  boolean addUserToGroup(String userName, String groupName){
		ObjectNode ret = null;
		try{
			ret = EasemobChatGroups.addUserToGroup(groupName, userName);
		} catch (Exception e) {
			logger.error("Exception in EasemobChatGroups.addUserToGroup(). addUserToGroup() failed ");
			return false;
		}
		if (ret == null){
			logger.error("The addUserToGroup() failed. ret is null\n");
			return false;
		}else if(ret.has("message")){
			logger.error("The addUserToGroup() failed. error:%s\n", ret.get("message"));
			return false;
		}		
		return true;
	}

	/**
	 * 
	 * @param userNames:环信用户名，比如u10000
	 *        groupName：环信群组名
	 * @return bool: 
	 * 	 	  true：操作成功
	 */		
	public  boolean addUsersToGroup(ArrayNode userNames, String groupName){
		boolean result = true;
		ObjectNode ret = null;
		String userName = null;
		for (int i=0; i < userNames.size(); i++){
			userName = userNames.path(i).asText();
			try{
				ret = EasemobChatGroups.addUserToGroup(groupName, userName);
			} catch (Exception e) {
				logger.error("Exception in EasemobChatGroups.addUserToGroup(). addUsersToGroup() failed ");
				return false;
			}
			if (ret == null){
				logger.error("The addUsersToGroup() failed. ret is null\n");
				return false;
			}else if(ret.has("message")){
				logger.error("The addUsersToGroup() failed. error:%s\n", ret.get("message"));
				return false;
			}		
		}
		return result;
	}
	/**
	 * 
	 * @param userName:环信用户名，比如u10000
	 *        groupName：环信群组名
	 * @return bool: 
	 * 	 	  true：操作成功
	 */		
	public  boolean deleteUserFromGroup(String userName, String groupName){
		ObjectNode ret = null;
		try{
			ret = EasemobChatGroups.deleteUserFromGroup(groupName, userName);
		} catch (Exception e) {
			logger.error("Exception in EasemobChatGroups.deleteUserFromGroup(). deleteUserFromGroup() failed ");
			return false;
		}
		if (ret == null){
			logger.error("The deleteUserFromGroup() failed. ret is null\n");
			return false;
		}else if(ret.has("message")){
			logger.error("The deleteUserFromGroup() failed. error:%s\n", ret.get("message"));
			return false;
		}		
		return true;
	}	

	/**
	 * RTV相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdRtvInviteNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int gid, String gname, String t1Ip, int t1Port,String u1Ip, int u1Port, int ack_req, String msg,String HxGid)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		int status=0;
		
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("gid", gid);
		sdExt.put("gname", gname);
		sdExt.put("hxGid", HxGid);
		sdExt.put("t1Ip", t1Ip);
		sdExt.put("t1Port", t1Port);
		sdExt.put("u1Ip", u1Ip);
		sdExt.put("u1Port", u1Port);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		sdExt.put("status", status);
		
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdRtvInviteNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdRtvInviteNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}		

		return true;
	}
	
	/**
	 * RTV相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdRtvLogonNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int gid, String gname,  int ack_req, String msg)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("gid", gid);
		sdExt.put("gname", gname);		
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);

		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdRtvLogonNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdRtvLogonNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}		

		return true;
	}
	
	/**
	 * RTV相关的response SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */	
	public  boolean sdRtvInviteNotifResp( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int gid, String gname, int resp)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		sdMsg.put("msg", "o");
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("gid", gid);
		sdExt.put("gname", gname);
		sdExt.put("resp", resp);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdRtvInviteNotifResp() failed. from:[%s], to:[%s]\n", from, toArray.toString());
			return false;
		}else if(ret.has("message")){
			logger.error("The sdRtvInviteNotifResp() failed, error:%s, from:[%s], to:[%s]\n",
					ret.get("message"), from, toArray.toString());
			return false;
		}		

		return true;
	}
	
	/**
	 * Friend相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */			
	public  boolean sdFriendNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int ack_req, String msg) 
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdFriendNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdFriendNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}		
		return true;	
	}
	
	/**
	 * Friend相关的response SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdFriendNotifResp( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int resp)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		sdMsg.put("msg", "o");
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("resp", resp);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdFriendNotifResp() failed. from:[%s], to:[%s]\n", from, toArray.toString());
			return false;
		}else if(ret.has("message")){
			logger.error("The sdFriendNotifResp() failed, error:%s, from:[%s], to:[%s]\n",
					ret.get("message"), from, toArray.toString());
			return false;
		}		
		return true;
	}
	
	/**
	 * Group相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdGroupNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int gid, String gname, int ack_req, String msg)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("gid", gid);
		sdExt.put("gname", gname);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdGroupNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdGroupNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}	
		return true;
	}
	
	/**
	 * Group相关的response SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdGroupNotifResp( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int gid, String gname, int resp)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("msg", "o");
		sdMsg.put("type", "txt");
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("gid", gid);
		sdExt.put("gname", gname);
		sdExt.put("resp", resp);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdGroupNotifResp() failed. from:[%s], to:[%s]\n", from, toArray.toString());
			return false;
		}else if(ret.has("message")){
			logger.error("The sdGroupNotifResp() failed, error:%s, from:[%s], to:[%s]\n",
					ret.get("message"), from, toArray.toString());
			return false;
		}		

		return true;
	}
	
	/**
	 * Activity相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdActivityNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int ack_req, String msg) 
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		int adm = 0;
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("adm", adm);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
	
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdActivityNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdActivityNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}	
		return true;
	}

	/**
	 * Activity相关的 SD命令发送接口（增加了adm参数）
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdActivityNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int ack_req, String msg, int adm) 
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("adm", adm);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdActivityNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdActivityNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}	
		return true;
	}	
	
	/**
	 * Activity相关的response SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdActivityNotifResp( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, int resp, String msg)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
//		sdMsg.put("msg", "o");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}		
		sdMsg.put("type", "txt");
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("resp", resp);		
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdActivityNotifResp() failed. from:[%s], to:[%s]\n", from, toArray.toString());
			return false;
		}else if(ret.has("message")){
			logger.error("The sdActivityNotifResp() failed, error:%s, from:[%s], to:[%s]\n",
					ret.get("message"), from, toArray.toString());
			return false;
		}		

		return true;
	}
	/**
	 * Task相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public  boolean sdTaskNotification( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, String taskType, int taskId, String taskSubject, int ack_req, String msg)
	{
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("type", "txt");
		if(msg == null || msg.length() == 0){
			sdMsg.put("msg", "o");
		}else{
			sdMsg.put("msg", msg);
		}
		sdExt.put("sdType", sdType);
		sdExt.put("uid", uid);
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("taskType", taskType);
		sdExt.put("taskId", taskId);
		sdExt.put("taskSubject", taskSubject);
		sdExt.put("ack_req", ack_req);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.

		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdTaskNotification() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), msg);
			return false;
		}else if(ret.has("message")){
			logger.error("The sdTaskNotification() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
					ret.get("message"), from, toArray.toString(), msg);
			return false;
		}	
		return true;
	}
	
	/**
	 * Task相关的SD命令发送接口
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：参见BMS_SD_ICD.xls
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	public boolean sdTaskNotifResp( int sdType, String from, String toType, ArrayNode toArray, int uid, String uname, int aid, String atitle, String taskType, int taskId, String taskSubject, int resp)
	{                               
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("msg", "o");
		sdMsg.put("type", "cmd");
		sdExt.put("sdType", sdType);		
		sdExt.put("uid", uid);		
		sdExt.put("uname", uname);
		sdExt.put("aid", aid);
		sdExt.put("atitle", atitle);
		sdExt.put("taskType", taskType);
		sdExt.put("taskId", taskId);
		sdExt.put("taskSubject", taskSubject);
		sdExt.put("resp", resp);
		sdExt.put("ouid", convertUsernameToUid(from));//orginator's uid
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		int status=0;
		sdExt.put("status", status);
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdTaskNotifResp() failed. from:[%s], to:[%s]\n", from, toArray.toString());
			return false;
		}else if(ret.has("message")){
			logger.error("The sdTaskNotifResp() failed, error:%s, from:[%s], to:[%s]\n",
					ret.get("message"), from, toArray.toString());
			return false;
		}		
		return true;
	}
	
	/**
	 * 发送SD-ATTACH-LIST
	 * @param toType: 短消息类型
	 * @param toArray: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 * @param attachList：TG中已attach的成员列表
	 *                    String结构如下“num:uid_1,uid_2 ...” 
	 *                    num标识list中用户的个数
	 *                    uid_x标识list中用户的uid，以“，”分割
	 * @param tgId：TG ID
	 * @return true：操作成功
	 *         false: 操作失败
	 */
	public boolean sdAttachList(String toType, ArrayNode toArray, String attachList, int tgId) {
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("action", "SD-ATTACH-LIST");
		sdMsg.put("type", "cmd");
		sdExt.put("sdType", ShortDataType.SDAttachList);
		sdExt.put("attach_list", attachList);
		sdExt.put("tgid", tgId);
		//use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		//int status=0;
		//sdExt.put("status", status);
		//Call HX API to send message
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdAttachList() failed. to:[%s]\n", toArray.toString());
			return false;
		}
		else if(ret.has("message")){
			logger.error("The sdAttachList() failed, error:%s, to:[%s]\n",
					ret.get("message"), toArray.toString());
			return false;
		}		
		return true;
	}
	
	/**
	 * 发送SD-INTEGRITY-COMPLETE
	 * @param toType: 短消息类型
	 * @param toArray: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 * @param tgId: TG ID
	 * @param pttId: 被分配的PTT ID
	 * @param callerUid: PTT Caller UID
	 * @param maxFid: 收到的最大audio帧序号
	 * @param timestamp: 从1970年1月1日0时起到目前时间，单位为秒
	 * @return true：操作成功
	 *         false: 操作失败
	 */
	public boolean sdIntegrityComplete(String toType, ArrayNode toArray, int tgId, int pttId, int callerUid, int maxFid, String timestamp, boolean isNrt) {
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("action", "SD-INTEGRITY-COMPLETE");
		sdMsg.put("type", "cmd");
		sdExt.put("sdType", ShortDataType.SDIntegrityComplete);
		sdExt.put("tgid", tgId);
		sdExt.put("ptt_id", pttId);
		sdExt.put("caller_uid", callerUid);
		sdExt.put("max_fid", maxFid);
		sdExt.put("timestamp", timestamp);
		sdExt.put("isNrt", isNrt);
		//use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		//int status=0;
		//sdExt.put("status", status);
		//Call HX API to send message
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdIntegrityComplete() failed. to:[%s]\n", toArray.toString());
			return false;
		}
		else if(ret.has("message")){
			logger.error("The sdIntegrityComplete() failed, error:%s, to:[%s]\n",
					ret.get("message"), toArray.toString());
			return false;
		}		
		return true;
	}
	
	/**
	 * 发送SD-TG-ALERT
	 * @param toType: 短消息类型
	 * @param toArray: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 * @param tgId: TG ID
	 * @param uid: 发起者uid
	 * @return true：操作成功
	 *         false: 操作失败
	 */
	public boolean sdTgAlert(String toType, ArrayNode toArray, int tgId, int uid) {
		ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		ObjectNode ret = null;
		sdMsg.put("msg", "SD-TG-ALERT");
		sdMsg.put("type", "txt");
		sdExt.put("sdType", ShortDataType.SDTGAlert);
		sdExt.put("tgid", tgId);
		sdExt.put("uid", uid);
		//use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		//int status=0;
		//sdExt.put("status", status);
		//Call HX API to send message
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdTgAlert() failed. to:[%s]\n", toArray.toString());
			return false;
		}
		else if(ret.has("message")){
			logger.error("The sdTgAlert() failed, error:%s, to:[%s]\n",
					ret.get("message"), toArray.toString());
			return false;
		}		
		return true;
	}

	/**
	 * 通用的SD命令发送接口，支持所有的SD命令的发送，包括：notification以及对notification的reply等。
	 * 
	 * @param:
	 *        sdType: SD type
	 * 		  from: 发出SD命令的环信用户名，比如：u10000
	 *        toType: 是发给单个用户还是群组。取值范围："users", "chatgroups"
	 *        toArray: 存储多个目标用户
	 *        其它参数：要求直接放入sdMsg和sdExt两个参数中，具体参数定义参见BMS_SD_ICD.xls，目前定义的参数如下：
	 * 			tgid, uid, attach_list, ptt_id, caller_uid, max_fid, timestamp 
	 * 			添加参数的样例: 
				//ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
				//sdExt.put("uid", uid);
	 * @return boolean: 
	 * 	 	  true：操作成功
	 */		
	
	public  boolean sdCommonSend(int sdType, String from, String toType, ArrayNode toArray, ObjectNode sdMsg, ObjectNode sdExt)
	{
		ObjectNode ret = null;
		if(sdMsg == null || sdExt == null) return false;

		//!!!! please be careful following two fields for sdMsg shall NOT be missing
		//	sdMsg.put("action", action); // msg如果没有填这个域，则加一个"o"，以保证环信server端不会出错。 
		if(sdMsg.get("type") == null) {
			sdMsg.put("type", "cmd");
		}
		if(sdMsg.get("action") == null) {
			sdMsg.put("action", "o");
		}
		
		//!!!!sdExt是由调用者填好后传进来的，由于sdType是必须填的，下面的代码保证这个字段不会遗漏
		if(sdExt.get("sdType") == null) {
			sdExt.put("sdType", sdType);
		}
		//tgid, uid, attach_list, ptt_id, caller_uid, max_fid, timestamp 
		//example: 
		//ObjectNode sdExt = JsonNodeFactory.instance.objectNode();
		//ObjectNode sdMsg = JsonNodeFactory.instance.objectNode();
		//sdExt.put("uid", uid);
		//......
		//sdMsg.put("type", "cmd");
		//......
		 //use this flag to indicate the message state, such as unread,read, executed.
		//This is a workaround for the HX offline message limitation.
		//0:unread, 1:read,2:executed(it imply read already), others for future use.
		//this value is transparent to System, client will be responsible to update its value
		//according to current state, current only Android use this flag.
		
		ret = EasemobMessages.sendMessages(toType, toArray, sdMsg, SDNAME, sdExt);
		if (ret == null){
			logger.error("The sdCommonSend() failed. from:[%s], to:[%s], msg:%s\n", from, toArray.toString(), sdMsg.get("msg"));
			return false;
		}else if(ret.has("message")){
			logger.error("The sdCommonSend() failed, error:%s, from:[%s], to:[%s], msg:%s\n",
						ret.get("message"), from, toArray.toString(), sdMsg.get("msg"));
			return false;
		}	
		return true;
	}	
}
