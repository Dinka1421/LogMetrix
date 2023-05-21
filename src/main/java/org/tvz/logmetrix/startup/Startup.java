package org.tvz.logmetrix.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.tvz.logmetrix"})
public class Startup {
	
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}