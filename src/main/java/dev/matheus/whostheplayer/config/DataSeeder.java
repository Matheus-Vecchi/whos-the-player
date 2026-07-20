package dev.matheus.whostheplayer.config;

import dev.matheus.whostheplayer.entity.DailyPlayer;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Position;
import dev.matheus.whostheplayer.repository.DailyPlayerRepository;
import dev.matheus.whostheplayer.repository.PlayerRepository;
import dev.matheus.whostheplayer.service.PlayerImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final DailyPlayerRepository dailyPlayerRepository;
    private final Clock gameClock;
    private final PlayerImportService playerImportService;

    public DataSeeder(PlayerRepository playerRepository, DailyPlayerRepository dailyPlayerRepository, Clock gameClock, PlayerImportService playerImportService) {
        this.playerRepository = playerRepository;
        this.dailyPlayerRepository = dailyPlayerRepository;
        this.gameClock = gameClock;
        this.playerImportService = playerImportService;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() == 0) {
            List<Player> players = playerImportService.requestParse();
            playerRepository.saveAll(players);
        }
        if (dailyPlayerRepository.count() == 0) {
            List<Player> players = playerRepository.findAll();
            Collections.shuffle(players, new Random(42));

            LocalDate start = LocalDate.now(gameClock);
            LocalDate date = start;
            List<DailyPlayer> dailyPlayerList = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                DailyPlayer dailyPlayer = new DailyPlayer(date, players.get(i));
                dailyPlayerList.add(dailyPlayer);
                date = date.plusDays(1);
            }
            dailyPlayerRepository.saveAll(dailyPlayerList);
        }
    }
}
