package dev.matheus.whostheplayer.controller;

import dev.matheus.whostheplayer.dto.GuessRequest;
import dev.matheus.whostheplayer.dto.GuessResult;
import dev.matheus.whostheplayer.dto.PlayerOption;
import dev.matheus.whostheplayer.dto.StartGameResponse;
import dev.matheus.whostheplayer.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public StartGameResponse start() {
        String gameId = gameService.startGame();
        return new StartGameResponse(gameId);
    }

    @PostMapping("/guess")
    public GuessResult guess(@RequestBody GuessRequest request) {
        return gameService.guess(request.getGameId(), request.getPlayerId());
    }

    @GetMapping("/players")
    public List<PlayerOption> listAllPlayers() {
        return gameService.listAllPlayers();
    }
}
