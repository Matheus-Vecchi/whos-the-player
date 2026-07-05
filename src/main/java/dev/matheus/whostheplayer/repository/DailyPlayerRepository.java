package dev.matheus.whostheplayer.repository;

import dev.matheus.whostheplayer.entity.DailyPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyPlayerRepository extends JpaRepository<DailyPlayer, LocalDate> {

}
