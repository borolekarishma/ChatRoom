
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.karishma.chatRoom.model.Chatroom;
import com.karishma.chatRoom.model.User;
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
public class ChatroomController {

	@Autowired
    ChatroomRepository chatroomRepository;
	
	@Autowired
	JwtTokenUtil jwtTokenutil;
	

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	
	@GetMapping("/rooms")
    public ResponseEntity<List<Chatroom>> getAllChatrooms() {
		
        return new ResponseEntity<List<Chatroom>>(chatroomRepository.findAll(), 
        		new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping("/rooms")
    public ResponseEntity<Chatroom> addChatroom(@Valid @RequestBody Chatroom chatroom,
    		@RequestHeader(value="Authorization") String token) {
		
		String jwtToken = token.substring(7);
		String username = jwtTokenutil.getUsernameFromToken(jwtToken);
		User user = userDetailsServiceImpl.findUserByUsername(username);
		
		chatroom.setCreatedBy(user);
		chatroom.setCreateDate(new Date(System.currentTimeMillis()));
        return new ResponseEntity<Chatroom>(chatroomRepository.save(chatroom), 
        		new HttpHeaders(), HttpStatus.OK);
    }
	
    @DeleteMapping("rooms/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable ("id") long roomId,  @RequestHeader(value="Authorization") String token) {
    	String jwtToken = token.substring(7);
		String username = jwtTokenutil.getUsernameFromToken(jwtToken);
		User user = userDetailsServiceImpl.findUserByUsername(username);
		Optional<Chatroom> chatroom = chatroomRepository.findById(roomId);
		
		if (chatroom.isPresent() && chatroom.get().getCreatedBy().getUserId() == user.getUserId()) {
			
	        chatroomRepository.deleteById(roomId);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authorized to delete the chat room.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Chat room deleted");
    }
 
}