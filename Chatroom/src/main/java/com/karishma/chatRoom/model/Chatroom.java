/*
 * Customer  
 * */

package com.karishma.chatRoom.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

/**
 * @author karishmaborole
 *
 */

@Entity
@Table(name="Chatroom")
public class Chatroom {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roomId;
	
	@Column(length=50)
    String title;
	
    String description;
	
    @OneToOne
    @JoinColumn(name="createdBy")
    User createdBy;
    
    @CreatedDate
    Date createDate;
    
    
    @OneToMany(mappedBy="chatroom", 
    		cascade = CascadeType.ALL,
    		orphanRemoval = true)
    List<ChatMessage> messages = new ArrayList<ChatMessage>();

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}
    
    
	
}
