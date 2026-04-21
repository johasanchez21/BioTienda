package com.example.BioTienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BioTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioTiendaApplication.class, args);
	}

}
