package dev.matheus.whostheplayer.entity;

import dev.matheus.whostheplayer.enums.Position;

public class Player {
    private int id;
    private String name;
    private int age;
    private String country;
    private String league;
    private String club;
    private Position position;
    private int over;

    public Player(int id, String name, int age, String country, String league, String club,
                  Position position, int over){
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.league = league;
        this.club = club;
        this.position = position;
        this.over = over;
    }

    public int getId() {
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
