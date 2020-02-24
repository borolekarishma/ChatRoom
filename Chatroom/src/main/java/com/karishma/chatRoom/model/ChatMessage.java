/*
 * Customer  
 * */

package com.karishma.chatRoom.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author karishmaborole
 *
 */

@Entity
@Table(name = "chatMessage")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long messageId;

	String content;

	@ManyToOne
	@JoinColumn(name = "roomId")
	Chatroom chatroom;

	@OneToOne
	@JoinColumn(name = "sender")
	User sender;

	@OneToOne
	@JoinColumn(name = "receiver")
	User receiver;

	@CreatedDate
	Date createDate;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonBackReference
	public Chatroom getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
