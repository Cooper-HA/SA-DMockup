package com.acme.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
//@ComponentScan(basePackages = "com.acme.demo.db, com.acme.demo.domain")
public class MvcjpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcjpademoApplication.class, args);
	}

}
