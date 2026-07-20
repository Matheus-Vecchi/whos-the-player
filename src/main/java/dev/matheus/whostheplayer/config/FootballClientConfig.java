package dev.matheus.whostheplayer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FootballClientConfig {

    @Bean
    public RestClient footballRestClient(@Value("${football.api.token}") String token) {
        return RestClient.builder()
                .baseUrl("https://api.football-data.org/v4")
                .defaultHeader("X-Auth-Token", token)
                .build();
    }
}
