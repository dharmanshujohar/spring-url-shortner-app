package com.spring.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UrlApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrlApiApplication.class, args);
	}
}
