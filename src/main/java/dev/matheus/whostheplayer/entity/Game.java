package dev.matheus.whostheplayer.entity;

public class Game {
    final private Player secretPlayer;
    final private String gameId;
    private boolean finished;

    public Game(Player secretPlayer, String gameId) {
        this.secretPlayer = secretPlayer;
        this.gameId = gameId;
        this.finished = false;
    }

    public Player getSecretPlayer(){
        return secretPlayer;
    }

    public String getGameId(){
        return gameId;
    }

    public boolean getFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }
}
