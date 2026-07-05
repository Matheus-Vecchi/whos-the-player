package dev.matheus.whostheplayer.config;

import dev.matheus.whostheplayer.entity.DailyPlayer;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Position;
import dev.matheus.whostheplayer.repository.DailyPlayerRepository;
import dev.matheus.whostheplayer.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
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

    public DataSeeder(PlayerRepository playerRepository, DailyPlayerRepository dailyPlayerRepository, Clock gameClock) {
        this.playerRepository = playerRepository;
        this.dailyPlayerRepository = dailyPlayerRepository;
        this.gameClock = gameClock;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() == 0) {
            playerRepository.saveAll(List.of(
                new Player("Cristiano Ronaldo", 41, "Portugal", "Saudi League", "Al-Nassr", Position.ATTACKER, 86),
                new Player("Lionel Messi", 38, "Argentina", "MLS", "Inter Miami", Position.ATTACKER, 86),
                new Player("Kylian Mbappe", 27, "France", "La Liga", "Real Madrid", Position.ATTACKER, 91),
                new Player("Erling Haaland", 25, "Norway", "Premier League", "Manchester City", Position.ATTACKER, 90),
                new Player("Vinicius Junior", 25, "Brazil", "La Liga", "Real Madrid", Position.ATTACKER, 89),
                new Player("Jude Bellingham", 22, "England", "La Liga", "Real Madrid", Position.MIDFIELDER, 89),
                new Player("Kevin De Bruyne", 34, "Belgium", "Serie A", "Napoli", Position.MIDFIELDER, 85),
                new Player("Mohamed Salah", 33, "Egypt", "Premier League", "Liverpool", Position.ATTACKER, 87),
                new Player("Harry Kane", 32, "England", "Bundesliga", "Bayern Munich", Position.ATTACKER, 91),
                new Player("Robert Lewandowski", 37, "Poland", "La Liga", "Barcelona", Position.ATTACKER, 86),
                new Player("Neymar", 34, "Brazil", "Brazilian League", "Santos", Position.ATTACKER, 82),
                new Player("Luka Modric", 40, "Croatia", "Serie A", "AC Milan", Position.MIDFIELDER, 84),
                new Player("Bruno Fernandes", 31, "Portugal", "Premier League", "Manchester United", Position.MIDFIELDER, 88),
                new Player("Rodri", 29, "Spain", "Premier League", "Manchester City", Position.MIDFIELDER, 87),
                new Player("Pedri", 23, "Spain", "La Liga", "Barcelona", Position.MIDFIELDER, 88),
                new Player("Gavi", 21, "Spain", "La Liga", "Barcelona", Position.MIDFIELDER, 84),
                new Player("Lamine Yamal", 18, "Spain", "La Liga", "Barcelona", Position.ATTACKER, 88),
                new Player("Phil Foden", 25, "England", "Premier League", "Manchester City", Position.MIDFIELDER, 86),
                new Player("Bukayo Saka", 24, "England", "Premier League", "Arsenal", Position.ATTACKER, 87),
                new Player("Martin Odegaard", 27, "Norway", "Premier League", "Arsenal", Position.MIDFIELDER, 87),
                new Player("Declan Rice", 27, "England", "Premier League", "Arsenal", Position.MIDFIELDER, 88),
                new Player("Virgil van Dijk", 34, "Netherlands", "Premier League", "Liverpool", Position.DEFENDER, 87),
                new Player("Alisson", 33, "Brazil", "Premier League", "Liverpool", Position.GOALKEEPER, 88),
                new Player("Ederson", 32, "Brazil", "Turkish League", "Fenebahce", Position.GOALKEEPER, 85),
                new Player("Thibaut Courtois", 33, "Belgium", "La Liga", "Real Madrid", Position.GOALKEEPER, 90),
                new Player("Marc-Andre ter Stegen", 33, "Germany", "La Liga", "Girona", Position.GOALKEEPER, 86),
                new Player("Gianluigi Donnarumma", 27, "Italy", "Premier League", "Manchester City", Position.GOALKEEPER, 88),
                new Player("Antoine Griezmann", 34, "France", "La Liga", "Atletico Madrid", Position.ATTACKER, 85),
                new Player("Joao Felix", 26, "Portugal", "Saudi League", "Al-Nassr", Position.ATTACKER, 83),
                new Player("Rafael Leao", 26, "Portugal", "Serie A", "AC Milan", Position.ATTACKER, 86),
                new Player("Victor Osimhen", 27, "Nigeria", "Turkish League", "Galatasaray", Position.ATTACKER, 85),
                new Player("Lautaro Martinez", 28, "Argentina", "Serie A", "Inter Milan", Position.ATTACKER, 88),
                new Player("Khvicha Kvaratskhelia", 25, "Georgia", "Ligue 1", "Paris Saint-Germain", Position.ATTACKER, 88),
                new Player("Ousmane Dembele", 28, "France", "Ligue 1", "Paris Saint-Germain", Position.ATTACKER, 89),
                new Player("Achraf Hakimi", 27, "Morocco", "Ligue 1", "Paris Saint-Germain", Position.DEFENDER, 89),
                new Player("Nuno Mendes", 23, "Portugal", "Ligue 1", "Paris Saint-Germain", Position.DEFENDER, 89),
                new Player("Federico Valverde", 27, "Uruguay", "La Liga", "Real Madrid", Position.MIDFIELDER, 89),
                new Player("Aurelien Tchouameni", 26, "France", "La Liga", "Real Madrid", Position.MIDFIELDER, 86),
                new Player("Eduardo Camavinga", 23, "France", "La Liga", "Real Madrid", Position.MIDFIELDER, 85),
                new Player("Antonio Rudiger", 33, "Germany", "La Liga", "Real Madrid", Position.DEFENDER, 86),
                new Player("Ruben Dias", 28, "Portugal", "Premier League", "Manchester City", Position.DEFENDER, 88),
                new Player("William Saliba", 25, "France", "Premier League", "Arsenal", Position.DEFENDER, 88),
                new Player("Alphonso Davies", 25, "Canada", "Bundesliga", "Bayern Munich", Position.DEFENDER, 83),
                new Player("Joshua Kimmich", 31, "Germany", "Bundesliga", "Bayern Munich", Position.MIDFIELDER, 88),
                new Player("Jamal Musiala", 23, "Germany", "Bundesliga", "Bayern Munich", Position.MIDFIELDER, 88),
                new Player("Florian Wirtz", 23, "Germany", "Premier League", "Liverpool", Position.MIDFIELDER, 88),
                new Player("Cole Palmer", 24, "England", "Premier League", "Chelsea", Position.MIDFIELDER, 87),
                new Player("Enzo Fernandez", 25, "Argentina", "Premier League", "Chelsea", Position.MIDFIELDER, 85),
                new Player("Moises Caicedo", 24, "Ecuador", "Premier League", "Chelsea", Position.MIDFIELDER, 84),
                new Player("Son Heung-min", 33, "South Korea", "MLS", "Los Angeles FC", Position.ATTACKER, 83),
                new Player("Dusan Vlahovic", 26, "Serbia", "Serie A", "Juventus", Position.ATTACKER, 85),
                new Player("Kenan Yildiz", 21, "Turkey", "Serie A", "Juventus", Position.ATTACKER, 83),
                new Player("Nicolo Barella", 29, "Italy", "Serie A", "Inter Milan", Position.MIDFIELDER, 87),
                new Player("Marcus Rashford", 28, "England", "La Liga", "Barcelona", Position.ATTACKER, 85),
                new Player("Gabriel Martinelli", 24, "Brazil", "Premier League", "Arsenal", Position.ATTACKER, 84),
                new Player("Darwin Nunez", 26, "Uruguay", "Saudi League", "Al-Hilal", Position.ATTACKER, 82),
                new Player("Cody Gakpo", 26, "Netherlands", "Premier League", "Liverpool", Position.ATTACKER, 85),
                new Player("Dominik Szoboszlai", 25, "Hungary", "Premier League", "Liverpool", Position.MIDFIELDER, 85),
                new Player("Alexis Mac Allister", 27, "Argentina", "Premier League", "Liverpool", Position.MIDFIELDER, 86),
                new Player("Frenkie de Jong", 28, "Netherlands", "La Liga", "Barcelona", Position.MIDFIELDER, 87),
                new Player("Raphinha", 29, "Brazil", "La Liga", "Barcelona", Position.ATTACKER, 88),
                new Player("Pau Cubarsi", 19, "Spain", "La Liga", "Barcelona", Position.DEFENDER, 80),
                new Player("Nico Williams", 23, "Spain", "La Liga", "Athletic Bilbao", Position.ATTACKER, 83),
                new Player("Mikel Merino", 29, "Spain", "Premier League", "Arsenal", Position.MIDFIELDER, 84),
                new Player("Scott McTominay", 29, "Scotland", "Serie A", "Napoli", Position.MIDFIELDER, 84),
                new Player("Federico Chiesa", 28, "Italy", "Premier League", "Liverpool", Position.ATTACKER, 83),
                new Player("Randal Kolo Muani", 27, "France", "Serie A", "Juventus", Position.ATTACKER, 83),
                new Player("Benjamin Sesko", 22, "Slovenia", "Premier League", "Manchester United", Position.ATTACKER, 84),
                new Player("Michael Olise", 24, "France", "Bundesliga", "Bayern Munich", Position.ATTACKER, 87)
            ));
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
