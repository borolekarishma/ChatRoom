package com.karishma.chatRoom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karishma.chatRoom.model.ChatMessage;
import com.karishma.chatRoom.model.Chatroom;
import com.karishma.chatRoom.model.User;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	List<ChatMessage> findAllByChatroom(Chatroom chatroom);
	List<ChatMessage> findAllByReceiver(User receiver);
	
}