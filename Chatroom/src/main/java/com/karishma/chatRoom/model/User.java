/*
 * Customer  
 * */

package com.karishma.chatRoom.model;


import javax.persistence.*;
/**
 * @author karishmaborole
 *
 */

@Entity
@Table(name="User")
public class User {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long userId;
	
	@Column(length=20)
    String firstName;
	
	@Column(length=20)
    String lastName;
	
    @Column(nullable=false, unique=true, length=50)
    String emailId;
    
    @Column(nullable=false, unique=true, length=10)
    String username;
    
	String password;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
