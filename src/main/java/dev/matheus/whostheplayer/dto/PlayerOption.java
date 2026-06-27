package dev.matheus.whostheplayer.dto;

public class PlayerOption {
    private final int playerId;
    private final String playerName;

    public PlayerOption(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
