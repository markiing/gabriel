package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class}
	    )
public class DownloadDepedenciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloadDepedenciesApplication.class, args);
	}
}
