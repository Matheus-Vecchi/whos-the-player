package dev.matheus.whostheplayer.repository;

import dev.matheus.whostheplayer.dto.PlayerOption;
import dev.matheus.whostheplayer.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p.id, p.name FROM Player p")
    List<PlayerOption> findAllOptions();

    @Query(value = "SELECT * FROM player ORDER BY random() LIMIT 1", nativeQuery = true)
    Player findRandomPlayer();
}
