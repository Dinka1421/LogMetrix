package org.tvz.logmetrix.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.tvz.logmetrix"})
@EntityScan(basePackages = {"org.tvz.logmetrix.entity"})
@EnableJpaRepositories(basePackages = {"org.tvz.logmetrix.repo"})
public class Startup {
	
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}