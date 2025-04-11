package com.affortless.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO 
{
	private String senderId;
	private String receiverId;
	private String content;
}
