package com.affortless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.affortless.entity.ChatMessageDTO;
import com.affortless.entity.RealTimeMessage;
import com.affortless.service.IRealTimeMessageService;

@Controller
public class MessageApplicationController 
{
	@Autowired
	private IRealTimeMessageService msgService;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/chat")
	public void sendMessage(@Payload ChatMessageDTO message)
	{
		RealTimeMessage msg = new RealTimeMessage();
		msg.setSenderId(message.getSenderId());
		msg.setReceiverId(message.getReceiverId());
		msg.setContent(message.getContent());
		
		msgService.registerMessage(msg);
		
		template.convertAndSend("/topic/" + message.getReceiverId(), message);
		
		if(!msgService.isUserActive(message.getReceiverId(), message.getSenderId()))
		{
			msgService.addUnread(message.getReceiverId(), message.getSenderId());
			
			template.convertAndSend("/queue/" + message.getReceiverId() , message);
		}

	}
}
