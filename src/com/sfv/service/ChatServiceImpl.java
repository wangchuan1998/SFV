package com.sfv.service;

import com.sfv.dao.ChatDao;


public class ChatServiceImpl implements ChatService {
	private ChatDao chatDao;

	public ChatDao getChatDao() {
		return chatDao;
	}

	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
}
