package com.karishma.chatRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karishma.chatRoom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailId(String emailId);
    User findByUsername(String userName);
}