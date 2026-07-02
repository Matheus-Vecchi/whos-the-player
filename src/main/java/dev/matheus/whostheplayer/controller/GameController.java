package dev.matheus.whostheplayer.controller;

import dev.matheus.whostheplayer.dto.*;
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
    public StartGameResponse start(@RequestBody GameModeRequest request) {
        String gameId = gameService.startGame(request.gameMode());
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
