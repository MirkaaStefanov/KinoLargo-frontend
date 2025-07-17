package com.example.KinoLargo_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KinoLargoFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinoLargoFrontendApplication.class, args);
	}

}
