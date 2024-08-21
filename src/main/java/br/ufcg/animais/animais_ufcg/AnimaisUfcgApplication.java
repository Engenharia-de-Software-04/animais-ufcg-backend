package br.ufcg.animais.animais_ufcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AnimaisUfcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimaisUfcgApplication.class, args);
	}
}
