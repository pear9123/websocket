package com.boot.ws.model;

import lombok.Data;

@Data
public class Message {

	private MessageType type;
    private String content;
    private String sender;
    
}
