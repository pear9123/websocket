package com.boot.ws.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.boot.ws.model.Message;
import com.boot.ws.model.MessageType;

@Component // spring bean 등록
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	//Spring 4.2부터는 이벤트 리스너가 ApplicationListener 인터페이스를 구현하는 Bean 일 필요가 없어졌습니다.
	//@EventListener 주석을 통해 관리되는 Bean의 모든 public 메소드에 등록 할 수 있습니다.
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
		
		String username = (String)headerAccesor.getSessionAttributes().get("username");
		if(username != null) {
			logger.info("User Disconnected : "+username);
			Message message = new Message();
			message.setType(MessageType.LEAVE);
			message.setSender(username);
			
			messagingTemplate.convertAndSend("/topic/public", message);
		}
	}
}
