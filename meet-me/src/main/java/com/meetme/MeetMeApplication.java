package com.meetme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MeetMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetMeApplication.class, args);
	}

}
