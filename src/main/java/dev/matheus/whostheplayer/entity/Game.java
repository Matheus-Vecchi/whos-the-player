package dev.matheus.whostheplayer.entity;

import dev.matheus.whostheplayer.enums.GameMode;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Game {
    @ManyToOne
    @JoinColumn(name = "secret_player_id")
    private Player secretPlayer;
    @Id
    private String gameId;
    private boolean finished;
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;
    private int attempts;
    private Instant createdAt;

    public Game() {}

    public Game(Player secretPlayer, String gameId, GameMode gameMode) {
        this.secretPlayer = secretPlayer;
        this.gameId = gameId;
        this.finished = false;
        this.gameMode = gameMode;
        this.attempts = 0;
        this.createdAt = Instant.now();
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

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
