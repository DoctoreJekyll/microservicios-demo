package org.generations.jobservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class JobServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServicesApplication.class, args);
	}

}
