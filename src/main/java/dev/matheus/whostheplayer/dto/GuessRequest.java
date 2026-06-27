package dev.matheus.whostheplayer.dto;

public class GuessRequest {
    private final String gameId;
    private final int playerId;

    public GuessRequest(String gameId, int playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
