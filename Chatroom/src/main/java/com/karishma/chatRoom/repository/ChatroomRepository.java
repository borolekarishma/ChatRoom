package com.karishma.chatRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karishma.chatRoom.model.Chatroom;


@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

}