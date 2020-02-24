package com.karishma.chatRoom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChatRoomApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(ChatRoomApplication.class);

	public static void main(String[] args) {
		log.info("Application started");
		SpringApplication.run(ChatRoomApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ChatRoomApplication.class);
	}
}
