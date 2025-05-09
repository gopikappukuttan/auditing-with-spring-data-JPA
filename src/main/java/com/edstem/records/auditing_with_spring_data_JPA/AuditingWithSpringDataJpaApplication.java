package com.edstem.records.auditing_with_spring_data_JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingWithSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditingWithSpringDataJpaApplication.class, args);
	}

}
