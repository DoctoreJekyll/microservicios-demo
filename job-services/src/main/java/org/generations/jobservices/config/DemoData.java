package org.generations.jobservices.config;

import org.generations.jobservices.model.Job;
import org.generations.jobservices.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoData {

    @Bean
    CommandLineRunner init(JobRepository repository)
    {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Job.builder().name("Archer").description("Range Job").build());
                repository.save(Job.builder().name("Fighter").description("Melee Job").build());
            }
        };
    }


}
