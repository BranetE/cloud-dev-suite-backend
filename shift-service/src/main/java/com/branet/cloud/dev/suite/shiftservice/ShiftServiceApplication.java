package com.branet.cloud.dev.suite.shiftservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShiftServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiftServiceApplication.class, args);
	}

}