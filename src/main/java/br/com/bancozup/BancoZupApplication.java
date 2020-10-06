package br.com.bancozup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.bancozup.service"})
@EntityScan   ("br.com.bancozup.model")
@EnableJpaRepositories("br.com.bancozup.repository")
public class BancoZupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoZupApplication.class, args);
	}

}
