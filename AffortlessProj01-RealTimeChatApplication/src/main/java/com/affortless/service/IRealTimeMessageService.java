package com.affortless.service;

import java.util.List;
import java.util.Set;

import com.affortless.entity.RealTimeMessage;

public interface IRealTimeMessageService 
{
	public void registerMessage(RealTimeMessage msg);
	public List<RealTimeMessage> retieveMessages(String user1,String user2);
	public void addUnread(String receiverId,String senderId);
	public void markAsRead(String receiverId,String senderId);
	public void setActiveChat(String userId,String anotherUser);
	public Set<String> getUnreadSender(String receiverId);
	public Boolean isUserActive(String user1,String user2);
}
