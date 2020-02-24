
package com.karishma.chatRoom.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.karishma.chatRoom.model.ChatMessage;
import com.karishma.chatRoom.model.Chatroom;
import com.karishma.chatRoom.model.User;
import com.karishma.chatRoom.repository.ChatMessageRepository;
import com.karishma.chatRoom.repository.ChatroomRepository;
import com.karishma.chatRoom.util.JwtTokenUtil;

/**
 * @author karishmaborole
 * 
 *         Chatroom Controller: Defines methods to handle Rest requests to add,
 *         delete and retrieve chatroom details.
 * 
 *         Updating Chatroom details will be available in the next version.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatMessageController {

	@Autowired
    ChatMessageRepository chatmessageRepository;

	@Autowired
	JwtTokenUtil jwtTokenutil;
	

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
    ChatroomRepository chatroomRepository;

	
	@GetMapping("/room/{roomId}/messages")
    public ResponseEntity<?> getAllChatroomMessages(@PathVariable(value="roomId") long roomId) {
		

		Optional<Chatroom> chatroom = chatroomRepository.findById(roomId);
		
		if (chatroom.isPresent()) {

	        return new ResponseEntity<List<ChatMessage>>(chatmessageRepository.findAllByChatroom(chatroom.get()), 
	        		new HttpHeaders(), HttpStatus.OK);
		}
		
		return ResponseEntity.badRequest().body("Invalid RoomId");
    }
	
	@PostMapping("/room/{roomId}/messages")
    public ResponseEntity<?> sendMessagetoChatroom(@Valid @RequestBody ChatMessage message,
    		@PathVariable(value="roomId") long roomId,
    		@RequestHeader(value="Authorization") String token) {
		String jwtToken = token.substring(7);
		String username = jwtTokenutil.getUsernameFromToken(jwtToken);
		User user = userDetailsServiceImpl.findUserByUsername(username);
		
		message.setSender(user);
		Optional<Chatroom> chatroom = chatroomRepository.findById(roomId);
		
		if (chatroom.isPresent()) {
			message.setChatroom(chatroom.get());
			message.setCreateDate(new Date(System.currentTimeMillis()));
			
	        return new ResponseEntity<ChatMessage>(chatmessageRepository.save(message), 
	        		new HttpHeaders(), HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body("Invalid RoomId");
		
    }
	
    @GetMapping("room/{roomId}/subscribe")
    public ResponseEntity<List<ChatMessage>> subscribetoChatroom(@PathVariable(value="roomId") long roomId) {
		return null;
		//TODO: This end point will be used to get messages sent to the room delivered to the subscribed user in real time
    }
    
    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages(@RequestHeader(value="Authorization") String token) {
    	String jwtToken = token.substring(7);
		User receiver = userDetailsServiceImpl.findUserByUsername(jwtTokenutil.getUsernameFromToken(jwtToken));
		
		return new ResponseEntity<List<ChatMessage>>(chatmessageRepository.findAllByReceiver(receiver), 
        		new HttpHeaders(), HttpStatus.OK);
		
    	
    }
    
    @PostMapping("/{username}/messages")
    public ResponseEntity<?> sendMessagetoUser(@Valid @RequestBody ChatMessage message,
    		@RequestHeader(value="Authorization") String token, @PathVariable(value="username") String username) {
    	
    	String jwtToken = token.substring(7);
		User sender = userDetailsServiceImpl.findUserByUsername(jwtTokenutil.getUsernameFromToken(jwtToken));
		
		message.setSender(sender);
		
		User receiver = userDetailsServiceImpl.findUserByUsername(username);
		
		
		if (receiver != null) {
			message.setCreateDate(new Date(System.currentTimeMillis()));
			message.setReceiver(receiver);
			
	        return new ResponseEntity<ChatMessage>(chatmessageRepository.save(message), 
	        		new HttpHeaders(), HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body("Invalid Username");
		
    }
 
}