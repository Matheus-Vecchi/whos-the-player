package dev.matheus.whostheplayer.service;

import dev.matheus.whostheplayer.dto.GuessResult;
import dev.matheus.whostheplayer.dto.PlayerOption;
import dev.matheus.whostheplayer.entity.Game;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Clue;
import dev.matheus.whostheplayer.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final PlayerRepository playerRepository;

    public GameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private final Map<String, Game> games = new ConcurrentHashMap<>();


    public String startGame(){
        final Player secretPlayer = playerRepository.findRandomPlayer();
        final String gameId = UUID.randomUUID().toString();

        Game game = new Game(secretPlayer, gameId);
        games.put(gameId, game);

        return gameId;
    }

    public GuessResult guess(String gameId, Long idGuessedPlayer) {
        Game actualGame = games.get(gameId);
        if (actualGame == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        Player actualSecretPlayer = actualGame.getSecretPlayer();

        if (actualGame.getFinished()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game already finished");
        }

        Player guessedPlayer = playerRepository.findById(idGuessedPlayer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        GuessResult clue = compareAttempt(actualSecretPlayer, guessedPlayer);
        if (clue.getClueName() == Clue.EXACT) {
            actualGame.setFinished(true);
        }

        return clue;
    }

    public GuessResult compareAttempt(Player actualSecretPlayer, Player guessedPlayer){
        final Clue clueName;
        final Clue clueAge;
        final Clue clueCountry;
        final Clue clueLeague;
        final Clue clueClub;
        final Clue cluePosition;

        if (actualSecretPlayer.getId().equals(guessedPlayer.getId())) {
            clueName = Clue.EXACT;
            clueAge = Clue.EXACT;
            clueCountry = Clue.EXACT;
            clueLeague = Clue.EXACT;
            clueClub = Clue.EXACT;
            cluePosition = Clue.EXACT;
        } else {
            clueName = Clue.WRONG;

            if (actualSecretPlayer.getAge() > guessedPlayer.getAge()) {
                clueAge = Clue.HIGHER;
            } else if (actualSecretPlayer.getAge() < guessedPlayer.getAge()) {
                clueAge = Clue.LOWER;
            } else {
                clueAge = Clue.EXACT;
            }

            if (actualSecretPlayer.getCountry().equals(guessedPlayer.getCountry())) {
                clueCountry = Clue.EXACT;
            } else {
                clueCountry = Clue.WRONG;
            }

            if (actualSecretPlayer.getLeague().equals(guessedPlayer.getLeague())) {
                clueLeague = Clue.EXACT;
            } else {
                clueLeague = Clue.WRONG;
            }

            if (actualSecretPlayer.getClub().equals(guessedPlayer.getClub())) {
                clueClub = Clue.EXACT;
            } else {
                clueClub = Clue.WRONG;
            }

            if (actualSecretPlayer.getPosition() != guessedPlayer.getPosition()) {
                cluePosition = Clue.WRONG;
            } else {
                cluePosition = Clue.EXACT;
            }
        }

        GuessResult guessResult = new GuessResult(clueName, clueAge, clueCountry, clueLeague, clueClub, cluePosition);
        return guessResult;
    }

    public List<PlayerOption> listAllPlayers() {
        return playerRepository.findAllOptions();
    }
}
