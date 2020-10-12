package br.com.bancozup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BancoZupApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BancoZupApplication.class, args);
	}

}
