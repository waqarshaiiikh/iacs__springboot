package com.glc.iacs__springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.glc.iacs__springboot.Service.EmailSenderService;

@SpringBootApplication
public class IacsSpringbootApplication {



	public static void main(String[] args) {
		SpringApplication.run(IacsSpringbootApplication.class, args);
	}



}
