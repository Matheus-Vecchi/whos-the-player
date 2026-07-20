package dev.matheus.whostheplayer.mapper;

import dev.matheus.whostheplayer.client.dto.SquadDto;
import dev.matheus.whostheplayer.entity.Player;
import dev.matheus.whostheplayer.enums.Position;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;

@Component
public class PlayerMapper {

    private final Clock clock;

    public PlayerMapper(Clock clock) {
        this.clock = clock;
    }

    public Player toPlayer(SquadDto apiPlayer, String league, String club) {
        String name = apiPlayer.name();
        int age = calculateAge(apiPlayer.dateOfBirth(), LocalDate.now(clock));
        String country = apiPlayer.nationality();
        Position position = normalizePosition(apiPlayer.position());

        Player player = new Player(name, age, country, league, club, position, 0);
        return player;
    }

    private int calculateAge(String dateOfBirth, LocalDate today) {
        LocalDate birth = LocalDate.parse(dateOfBirth);
        int age = Period.between(birth, today).getYears();

        return age;
    }

    private Position normalizePosition(String apiPosition) {
        return switch (apiPosition) {
            case "Goalkeeper" -> Position.GOALKEEPER;

            case "Defence",
                 "Centre-Back",
                 "Left-Back",
                 "Right-Back" -> Position.DEFENDER;

            case "Midfield",
                 "Defensive Midfield",
                 "Central Midfield",
                 "Attacking Midfield",
                 "Left Midfield",
                 "Right Midfield" -> Position.MIDFIELDER;

            case "Offence",
                 "Centre-Forward",
                 "Left Winger",
                 "Right Winger" -> Position.ATTACKER;

            default -> throw new IllegalArgumentException("Unknown position from API: " + apiPosition);
        };
    }
}
