package dev.matheus.whostheplayer.dto;

public class PlayerOption {
    private final Long playerId;
    private final String playerName;

    public PlayerOption(Long playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
