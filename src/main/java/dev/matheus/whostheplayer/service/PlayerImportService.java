package dev.matheus.whostheplayer.service;

import dev.matheus.whostheplayer.client.FootballDataClient;
import dev.matheus.whostheplayer.client.dto.CompetitionTeamsResponse;
import dev.matheus.whostheplayer.client.dto.SquadDto;
import dev.matheus.whostheplayer.client.dto.TeamDto;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerImportService {
    private final FootballDataClient footballDataClient;
    private final PlayerMapper playerMapper;
    @Value("${football-data.league-codes}")
    private List<String> leagueCodes;


    public PlayerImportService(FootballDataClient footballDataClient, PlayerMapper playerMapper) {
        this.footballDataClient = footballDataClient;
        this.playerMapper = playerMapper;
    }

    public List<Player> requestParse() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < leagueCodes.size(); i++) {
            CompetitionTeamsResponse dirtyLeaguePlayers = footballDataClient.request(leagueCodes.get(i));
            playerParse(dirtyLeaguePlayers, players);
        }
        return players;
    }

    private void playerParse(CompetitionTeamsResponse dirtyLeaguePlayers, List<Player> players) {
        List<TeamDto> teams = dirtyLeaguePlayers.teams();

        for (int i = 0; i < teams.size(); i++) {
            TeamDto team = teams.get(i);
            for (int j = 0; j < team.squad().size(); j++) {
                SquadDto apiPlayer = team.squad().get(j);
                if (apiPlayer.position() == null || apiPlayer.position().equals("null") || apiPlayer.dateOfBirth() == null) {
                    continue;
                }

                Player player = playerMapper.toPlayer(apiPlayer, dirtyLeaguePlayers.competition().name(), team.name());
                players.add(player);
            }
        }
    }
}
