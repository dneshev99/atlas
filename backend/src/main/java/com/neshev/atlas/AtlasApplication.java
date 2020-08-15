package com.neshev.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AtlasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlasApplication.class, args);
	}

}
