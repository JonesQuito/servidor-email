package com.roboquito.email;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roboquito.email.server.Servidor;

@SpringBootApplication
public class ServidorEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorEmailApplication.class, args);
		
		
		try {
			new Servidor(5000).executa();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
