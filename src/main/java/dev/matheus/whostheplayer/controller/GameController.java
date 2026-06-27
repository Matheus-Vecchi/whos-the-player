package dev.matheus.whostheplayer.controller;

import dev.matheus.whostheplayer.dto.GuessRequest;
import dev.matheus.whostheplayer.dto.GuessResult;
import dev.matheus.whostheplayer.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public String start() {
        return gameService.startGame();
    }

    @PostMapping("/guess")
    public GuessResult guess(@RequestBody GuessRequest request) {
        return gameService.guess(request.getGameId(), request.getPlayerId());
    }
}
