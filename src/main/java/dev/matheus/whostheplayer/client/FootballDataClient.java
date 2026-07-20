package dev.matheus.whostheplayer.client;
import dev.matheus.whostheplayer.client.dto.CompetitionTeamsResponse;
import dev.matheus.whostheplayer.entity.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FootballDataClient {
    private List<String> leagues = List.of("BSA", "PL", "FL1", "BL1", "SA", "PPL", "PD");
    private final RestClient restClient;

    public FootballDataClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public CompetitionTeamsResponse request(String leagueCode) {
        return restClient.get()
                .uri("/competitions/{code}/teams", leagueCode)
                .retrieve()
                .body(CompetitionTeamsResponse.class);
    }
}
