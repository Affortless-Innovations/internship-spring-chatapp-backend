package com.affortless.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affortless.entity.RealTimeMessage;
import com.affortless.service.IRealTimeMessageService;

@RestController
public class MessageApplicationRestController 
{
	@Autowired
	private IRealTimeMessageService service;
	@GetMapping("/messages")
	public ResponseEntity<List<RealTimeMessage>> getMessages(@RequestParam String user1 , @RequestParam String user2)
	{
		List<RealTimeMessage> list = service.retieveMessages(user1, user2);
		return new ResponseEntity<List<RealTimeMessage>>(list,HttpStatus.OK);
	}
	@PostMapping("/active")
	public ResponseEntity<Void> setActiveChat(@RequestParam String receiver,@RequestParam String sender)
	{
		service.setActiveChat(receiver, sender);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	@PostMapping("/read")
	public ResponseEntity<Void> markAsRead(@RequestParam String receiver,@RequestParam String sender)
	{
		service.markAsRead(receiver, sender);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/unread")
	public ResponseEntity<Set<String>> getUnreadSender(@RequestParam String receiverId)
	{
		Set<String> unread = service.getUnreadSender(receiverId);
		return new ResponseEntity<Set<String>>(unread,HttpStatus.OK);
	}
}
