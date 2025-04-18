package com.affortless.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affortless.entity.RealTimeMessage;
import com.affortless.repository.IChatMessageRepository;

@Service
public class RealTimeMessageServiceImpl implements IRealTimeMessageService 
{
	@Autowired
	private IChatMessageRepository messageRepo;
	
	private Map<String, Set<String>> unreadMessages=new HashMap<>();
	private Map<String, String> activeChats=new HashMap<String, String>();

	@Override
	public void registerMessage(RealTimeMessage msg) 
	{
		System.out.println("Message saved: " + messageRepo.save(msg));
	}

	@Override
	public List<RealTimeMessage> retieveMessages(String user1, String user2) 
	{
		return messageRepo.getChatHistoryBetweenUsers(user1, user2);
		
	}			
	@Override
	public void addUnread(String receiverId, String senderId) 
	{
		unreadMessages.computeIfAbsent(receiverId, k->new HashSet<String>()).add(senderId);
	}

	@Override
	public void markAsRead(String receiverId, String senderId) 
	{
		Set<String> unreadFrom = unreadMessages.get(receiverId);
		if(unreadFrom!=null)
		{
			unreadFrom.remove(senderId);
			if(unreadFrom.isEmpty())
			{
				unreadMessages.remove(receiverId);
			}
		}
	}

	@Override
	public void setActiveChat(String userId, String anotherUser) 
	{
		activeChats.put(userId, anotherUser);
	}

	@Override
	public Set<String> getUnreadSender(String receiverId) 
	{
		Set<String> unreadSenders = unreadMessages.getOrDefault(receiverId, new HashSet<String>());
		return unreadSenders;
	}

	@Override
	public Boolean isUserActive(String user1, String user2) 
	{
		return user2.equals(activeChats.get(user1));
	}

}
