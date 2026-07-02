package dev.matheus.whostheplayer.service;

import dev.matheus.whostheplayer.dto.GameModeRequest;
import dev.matheus.whostheplayer.dto.GuessResult;
import dev.matheus.whostheplayer.dto.PlayerOption;
import dev.matheus.whostheplayer.entity.Game;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Clue;
import dev.matheus.whostheplayer.enums.GameMode;
import dev.matheus.whostheplayer.repository.GameRepository;
import dev.matheus.whostheplayer.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public GameService(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }



    @Transactional
    public String startGame(GameMode gameMode) {
        Player secretPlayer;
        if (gameMode == GameMode.ENDLESS) {
            secretPlayer = playerRepository.findRandomPlayer();
        } else {
            secretPlayer = playerRepository.findRandomPlayer();
        }

        final String gameId = UUID.randomUUID().toString();

        Game game = new Game(secretPlayer, gameId, gameMode);
        gameRepository.save(game); // transient Object -> managed Object

        return gameId;
    }

    @Transactional
    public GuessResult guess(String gameId, Long idGuessedPlayer) {
        Game currentGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        Player currentSecretPlayer = currentGame.getSecretPlayer();

        if (currentGame.getFinished()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Game already finished");
        }

        Player guessedPlayer = playerRepository.findById(idGuessedPlayer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        currentGame.setAttempts(currentGame.getAttempts() + 1);

        GuessResult clue = compareAttempt(currentSecretPlayer, guessedPlayer);
        if (clue.getClueName() == Clue.EXACT) {
            currentGame.setFinished(true);
        }

        gameRepository.save(currentGame);

        return clue;
    }

    public GuessResult compareAttempt(Player currentSecretPlayer, Player guessedPlayer){
        final Clue clueName;
        final Clue clueAge;
        final Clue clueCountry;
        final Clue clueLeague;
        final Clue clueClub;
        final Clue cluePosition;

        if (currentSecretPlayer.getId().equals(guessedPlayer.getId())) {
            clueName = Clue.EXACT;
            clueAge = Clue.EXACT;
            clueCountry = Clue.EXACT;
            clueLeague = Clue.EXACT;
            clueClub = Clue.EXACT;
            cluePosition = Clue.EXACT;
        } else {
            clueName = Clue.WRONG;

            if (currentSecretPlayer.getAge() > guessedPlayer.getAge()) {
                clueAge = Clue.HIGHER;
            } else if (currentSecretPlayer.getAge() < guessedPlayer.getAge()) {
                clueAge = Clue.LOWER;
            } else {
                clueAge = Clue.EXACT;
            }

            if (currentSecretPlayer.getCountry().equals(guessedPlayer.getCountry())) {
                clueCountry = Clue.EXACT;
            } else {
                clueCountry = Clue.WRONG;
            }

            if (currentSecretPlayer.getLeague().equals(guessedPlayer.getLeague())) {
                clueLeague = Clue.EXACT;
            } else {
                clueLeague = Clue.WRONG;
            }

            if (currentSecretPlayer.getClub().equals(guessedPlayer.getClub())) {
                clueClub = Clue.EXACT;
            } else {
                clueClub = Clue.WRONG;
            }

            if (currentSecretPlayer.getPosition() != guessedPlayer.getPosition()) {
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
