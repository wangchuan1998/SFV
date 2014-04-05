package com.sfv.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.sfv.entitybean.system.SystemUser;
import com.sfv.service.ChatService;
import com.sfv.service.UserService;

/**
 * 网络聊天入口Action
 * @author dbq
 *
 */
public class ChatAction  extends BaseAction{
	
	private ChatService chatService;
	
	public static void main(String[] args){
		System.out.println(UUID.randomUUID().toString());
	}

	public ChatService getChatService() {
		return chatService;
	}
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
	
	private Map<String, Object> dataMap;
	//目标主机
	private String ObjectId;
	
	private String content;
	
	private UserService userService;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getObjectId() {
		return ObjectId;
	}

	public void setObjectId(String objectId) {
		ObjectId = objectId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
