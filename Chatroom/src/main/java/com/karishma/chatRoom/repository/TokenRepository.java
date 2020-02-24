package com.karishma.chatRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karishma.chatRoom.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	Token findByJwttoken(String token);
	void deleteByJwttoken(String token);

}
