package dev.matheus.whostheplayer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class DailyPlayer {
    @Id
    private LocalDate gameDate;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public DailyPlayer() {}

    public DailyPlayer(LocalDate gameDate, Player player) {
        this.gameDate = gameDate;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
