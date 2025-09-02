package org.generations.playerservices.config;

import org.generations.playerservices.model.Player;
import org.generations.playerservices.reporitory.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DemoData {
  @Bean
  CommandLineRunner init(PlayerRepository repo) {
    return args -> {
      if (repo.count() == 0) {
        repo.save(Player.builder().name("Aria").race("Elfa").level(10).jobId(1).build());
        repo.save(Player.builder().name("Dorn").race("Humano").level(7).jobId(2).build());
      }
    };
  }
}
