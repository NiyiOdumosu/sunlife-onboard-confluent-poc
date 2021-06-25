package org.sunlife.confluent.sunlifepoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.sunlife.confluent.sunlifepoc.*")
public class SunlifePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunlifePocApplication.class, args);
	}

}
