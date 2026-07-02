package dev.matheus.whostheplayer.repository;

import dev.matheus.whostheplayer.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {

}
