package dev.matheus.whostheplayer.entity;

import dev.matheus.whostheplayer.enums.Position;
import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String country;
    private String league;
    private String club;
    @Enumerated(EnumType.STRING)
    private Position position;
    private int over;

    public Player() {}

    public Player(String name, int age, String country, String league, String club,
                  Position position, int over){
        this.name = name;
        this.age = age;
        this.country = country;
        this.league = league;
        this.club = club;
        this.position = position;
        this.over = over;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }

    public String getClub() {
        return club;
    }

    public Position getPosition() {
        return position;
    }

    public int getOver() {
        return over;
    }
}
