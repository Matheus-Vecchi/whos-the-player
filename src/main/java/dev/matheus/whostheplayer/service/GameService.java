package dev.matheus.whostheplayer.service;

import dev.matheus.whostheplayer.dto.GuessResult;
import dev.matheus.whostheplayer.dto.PlayerOption;
import dev.matheus.whostheplayer.entity.Game;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Clue;
import dev.matheus.whostheplayer.enums.Position;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final Random random = new Random();
    private final Map<String, Game> games = new ConcurrentHashMap<>();
    private final Map<Integer, Player> players = new HashMap<>();
    private final List<PlayerOption> allPlayers = new ArrayList<>();
    private final List<Integer> ids = new ArrayList<>();
    {
        players.put(1, new Player(1, "Cristiano Ronaldo", 41, "Portugal", "Saudi League", "Al-Nassr", Position.ATTACKER, 86));
        players.put(2, new Player(2, "Lionel Messi", 38, "Argentina", "MLS", "Inter Miami", Position.ATTACKER, 86));
        players.put(3, new Player(3, "Kylian Mbappe", 27, "France", "La Liga", "Real Madrid", Position.ATTACKER, 91));
        players.put(4, new Player(4, "Erling Haaland", 25, "Norway", "Premier League", "Manchester City", Position.ATTACKER, 90));
        players.put(5, new Player(5, "Vinicius Junior", 25, "Brazil", "La Liga", "Real Madrid", Position.ATTACKER, 89));
        players.put(6, new Player(6, "Jude Bellingham", 22, "England", "La Liga", "Real Madrid", Position.MIDFIELDER, 89));
        players.put(7, new Player(7, "Kevin De Bruyne", 34, "Belgium", "Serie A", "Napoli", Position.MIDFIELDER, 85));
        players.put(8, new Player(8, "Mohamed Salah", 33, "Egypt", "Premier League", "Liverpool", Position.ATTACKER, 87));
        players.put(9, new Player(9, "Harry Kane", 32, "England", "Bundesliga", "Bayern Munich", Position.ATTACKER, 91));
        players.put(10, new Player(10, "Robert Lewandowski", 37, "Poland", "La Liga", "Barcelona", Position.ATTACKER, 86));
        players.put(11, new Player(11, "Neymar", 34, "Brazil", "Brazilian League", "Santos", Position.ATTACKER, 82));
        players.put(12, new Player(12, "Luka Modric", 40, "Croatia", "Serie A", "AC Milan", Position.MIDFIELDER, 84));
        players.put(14, new Player(14, "Bruno Fernandes", 31, "Portugal", "Premier League", "Manchester United", Position.MIDFIELDER, 88));
        players.put(15, new Player(15, "Rodri", 29, "Spain", "Premier League", "Manchester City", Position.MIDFIELDER, 87));
        players.put(16, new Player(16, "Pedri", 23, "Spain", "La Liga", "Barcelona", Position.MIDFIELDER, 88));
        players.put(17, new Player(17, "Gavi", 21, "Spain", "La Liga", "Barcelona", Position.MIDFIELDER, 84));
        players.put(18, new Player(18, "Lamine Yamal", 18, "Spain", "La Liga", "Barcelona", Position.ATTACKER, 88));
        players.put(19, new Player(19, "Phil Foden", 25, "England", "Premier League", "Manchester City", Position.MIDFIELDER, 86));
        players.put(20, new Player(20, "Bukayo Saka", 24, "England", "Premier League", "Arsenal", Position.ATTACKER, 87));
        players.put(21, new Player(21, "Martin Odegaard", 27, "Norway", "Premier League", "Arsenal", Position.MIDFIELDER, 87));
        players.put(22, new Player(22, "Declan Rice", 27, "England", "Premier League", "Arsenal", Position.MIDFIELDER, 88));
        players.put(23, new Player(23, "Virgil van Dijk", 34, "Netherlands", "Premier League", "Liverpool", Position.DEFENDER, 87));
        players.put(24, new Player(24, "Alisson", 33, "Brazil", "Premier League", "Liverpool", Position.GOALKEEPER, 88));
        players.put(25, new Player(25, "Ederson", 32, "Brazil", "Turkish League", "Fenebahce", Position.GOALKEEPER, 85));
        players.put(26, new Player(26, "Thibaut Courtois", 33, "Belgium", "La Liga", "Real Madrid", Position.GOALKEEPER, 90));
        players.put(27, new Player(27, "Marc-Andre ter Stegen", 33, "Germany", "La Liga", "Girona", Position.GOALKEEPER, 86));
        players.put(28, new Player(28, "Gianluigi Donnarumma", 27, "Italy", "Premier League", "Manchester City", Position.GOALKEEPER, 88));
        players.put(29, new Player(29, "Antoine Griezmann", 34, "France", "La Liga", "Atletico Madrid", Position.ATTACKER, 85));
        players.put(30, new Player(30, "Joao Felix", 26, "Portugal", "Saudi League", "Al-Nassr", Position.ATTACKER, 83));
        players.put(31, new Player(31, "Rafael Leao", 26, "Portugal", "Serie A", "AC Milan", Position.ATTACKER, 86));
        players.put(32, new Player(32, "Victor Osimhen", 27, "Nigeria", "Turkish League", "Galatasaray", Position.ATTACKER, 85));
        players.put(33, new Player(33, "Lautaro Martinez", 28, "Argentina", "Serie A", "Inter Milan", Position.ATTACKER, 88));
        players.put(34, new Player(34, "Khvicha Kvaratskhelia", 25, "Georgia", "Ligue 1", "Paris Saint-Germain", Position.ATTACKER, 88));
        players.put(35, new Player(35, "Ousmane Dembele", 28, "France", "Ligue 1", "Paris Saint-Germain", Position.ATTACKER, 89));
        players.put(36, new Player(36, "Achraf Hakimi", 27, "Morocco", "Ligue 1", "Paris Saint-Germain", Position.DEFENDER, 89));
        players.put(37, new Player(37, "Nuno Mendes", 23, "Portugal", "Ligue 1", "Paris Saint-Germain", Position.DEFENDER, 89));
        players.put(38, new Player(38, "Federico Valverde", 27, "Uruguay", "La Liga", "Real Madrid", Position.MIDFIELDER, 89));
        players.put(39, new Player(39, "Aurelien Tchouameni", 26, "France", "La Liga", "Real Madrid", Position.MIDFIELDER, 86));
        players.put(40, new Player(40, "Eduardo Camavinga", 23, "France", "La Liga", "Real Madrid", Position.MIDFIELDER, 85));
        players.put(41, new Player(41, "Antonio Rudiger", 33, "Germany", "La Liga", "Real Madrid", Position.DEFENDER, 86));
        players.put(42, new Player(42, "Ruben Dias", 28, "Portugal", "Premier League", "Manchester City", Position.DEFENDER, 88));
        players.put(43, new Player(43, "William Saliba", 25, "France", "Premier League", "Arsenal", Position.DEFENDER, 88));
        players.put(44, new Player(44, "Alphonso Davies", 25, "Canada", "Bundesliga", "Bayern Munich", Position.DEFENDER, 83));
        players.put(45, new Player(45, "Joshua Kimmich", 31, "Germany", "Bundesliga", "Bayern Munich", Position.MIDFIELDER, 88));
        players.put(46, new Player(46, "Jamal Musiala", 23, "Germany", "Bundesliga", "Bayern Munich", Position.MIDFIELDER, 88));
        players.put(47, new Player(47, "Florian Wirtz", 23, "Germany", "Premier League", "Liverpool", Position.MIDFIELDER, 88));
        players.put(48, new Player(48, "Cole Palmer", 24, "England", "Premier League", "Chelsea", Position.MIDFIELDER, 87));
        players.put(49, new Player(49, "Enzo Fernandez", 25, "Argentina", "Premier League", "Chelsea", Position.MIDFIELDER, 85));
        players.put(50, new Player(50, "Moises Caicedo", 24, "Ecuador", "Premier League", "Chelsea", Position.MIDFIELDER, 84));
        players.put(51, new Player(51, "Son Heung-min", 33, "South Korea", "MLS", "Los Angeles FC", Position.ATTACKER, 83));
        players.put(52, new Player(52, "Dusan Vlahovic", 26, "Serbia", "Serie A", "Juventus", Position.ATTACKER, 85));
        players.put(53, new Player(53, "Kenan Yildiz", 21, "Turkey", "Serie A", "Juventus", Position.ATTACKER, 83));
        players.put(54, new Player(54, "Nicolo Barella", 29, "Italy", "Serie A", "Inter Milan", Position.MIDFIELDER, 87));
        players.put(55, new Player(55, "Marcus Rashford", 28, "England", "La Liga", "Barcelona", Position.ATTACKER, 85));
        players.put(56, new Player(56, "Gabriel Martinelli", 24, "Brazil", "Premier League", "Arsenal", Position.ATTACKER, 84));
        players.put(57, new Player(57, "Darwin Nunez", 26, "Uruguay", "Saudi League", "Al-Hilal", Position.ATTACKER, 82));
        players.put(58, new Player(58, "Cody Gakpo", 26, "Netherlands", "Premier League", "Liverpool", Position.ATTACKER, 85));
        players.put(59, new Player(59, "Dominik Szoboszlai", 25, "Hungary", "Premier League", "Liverpool", Position.MIDFIELDER, 85));
        players.put(60, new Player(60, "Alexis Mac Allister", 27, "Argentina", "Premier League", "Liverpool", Position.MIDFIELDER, 86));
        players.put(61, new Player(61, "Frenkie de Jong", 28, "Netherlands", "La Liga", "Barcelona", Position.MIDFIELDER, 87));
        players.put(62, new Player(62, "Raphinha", 29, "Brazil", "La Liga", "Barcelona", Position.ATTACKER, 88));
        players.put(63, new Player(63, "Pau Cubarsi", 19, "Spain", "La Liga", "Barcelona", Position.DEFENDER, 80));
        players.put(64, new Player(64, "Nico Williams", 23, "Spain", "La Liga", "Athletic Bilbao", Position.ATTACKER, 83));
        players.put(65, new Player(65, "Mikel Merino", 29, "Spain", "Premier League", "Arsenal", Position.MIDFIELDER, 84));
        players.put(66, new Player(66, "Scott McTominay", 29, "Scotland", "Serie A", "Napoli", Position.MIDFIELDER, 84));
        players.put(67, new Player(67, "Federico Chiesa", 28, "Italy", "Premier League", "Liverpool", Position.ATTACKER, 83));
        players.put(68, new Player(68, "Randal Kolo Muani", 27, "France", "Serie A", "Juventus", Position.ATTACKER, 83));
        players.put(69, new Player(69, "Benjamin Sesko", 22, "Slovenia", "Premier League", "Manchester United", Position.ATTACKER, 84));
        players.put(70, new Player(70, "Michael Olise", 24, "France", "Bundesliga", "Bayern Munich", Position.ATTACKER, 87));
        ids.addAll(players.keySet());
        insertPlayers();
    }


    public String startGame(){
        final int index = random.nextInt(ids.size());
        final Player secretPlayer = players.get(ids.get(index));

        String gameId = UUID.randomUUID().toString();

        Game game = new Game(secretPlayer, gameId);
        games.put(gameId, game);

        return gameId;
    }

    public GuessResult guess(String gameId, int idGuessedPlayer) {
        Game actualGame = games.get(gameId);
        if (actualGame == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        Player actualSecretPlayer = actualGame.getSecretPlayer();

        if (actualGame.getFinished()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game already finished");
        }

        Player guessedPlayer = players.get(idGuessedPlayer);
        if (guessedPlayer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }

        GuessResult clue = compareAttempt(actualSecretPlayer, guessedPlayer);
        if (clue.getClueName() == Clue.EXACT) {
            actualGame.setFinished(true);
        }

        return clue;
    }

    public GuessResult compareAttempt(Player secretPlayer, Player guessedPlayer){
        final Clue clueName;
        final Clue clueAge;
        final Clue clueCountry;
        final Clue clueLeague;
        final Clue clueClub;
        final Clue cluePosition;

        if (secretPlayer.getId() == guessedPlayer.getId()) {
            clueName = Clue.EXACT;
            clueAge = Clue.EXACT;
            clueCountry = Clue.EXACT;
            clueLeague = Clue.EXACT;
            clueClub = Clue.EXACT;
            cluePosition = Clue.EXACT;
        } else {
            clueName = Clue.WRONG;

            if (secretPlayer.getAge() > guessedPlayer.getAge()) {
                clueAge = Clue.HIGHER;
            } else if (secretPlayer.getAge() < guessedPlayer.getAge()) {
                clueAge = Clue.LOWER;
            } else {
                clueAge = Clue.EXACT;
            }

            if (secretPlayer.getCountry().equals(guessedPlayer.getCountry())) {
                clueCountry = Clue.EXACT;
            } else {
                clueCountry = Clue.WRONG;
            }

            if (secretPlayer.getLeague().equals(guessedPlayer.getLeague())) {
                clueLeague = Clue.EXACT;
            } else {
                clueLeague = Clue.WRONG;
            }

            if (secretPlayer.getClub().equals(guessedPlayer.getClub())) {
                clueClub = Clue.EXACT;
            } else {
                clueClub = Clue.WRONG;
            }

            if (secretPlayer.getPosition() != guessedPlayer.getPosition()) {
                cluePosition = Clue.WRONG;
            } else {
                cluePosition = Clue.EXACT;
            }
        }

        GuessResult guessResult = new GuessResult(clueName, clueAge, clueCountry, clueLeague, clueClub, cluePosition);
        return guessResult;
    }

    private void insertPlayers() {
        for (int i = 0; i < ids.size(); i++) {
            Player player = players.get(ids.get(i));
            PlayerOption playerOption = new PlayerOption(player.getId(), player.getName());
            allPlayers.add(playerOption);
        }
    }

    public List<PlayerOption> listAllPlayers() {
        return allPlayers;
    }
}
