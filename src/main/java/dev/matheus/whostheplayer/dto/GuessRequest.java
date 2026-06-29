package dev.matheus.whostheplayer.dto;

public class GuessRequest {
    private final String gameId;
    private final Long playerId;

    public GuessRequest(String gameId, Long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
