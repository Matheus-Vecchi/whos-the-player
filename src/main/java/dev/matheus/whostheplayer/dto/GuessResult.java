package dev.matheus.whostheplayer.dto;

import dev.matheus.whostheplayer.enums.Clue;

public class GuessResult {
    private final Clue clueName;
    private final Clue clueAge;
    private final Clue clueCountry;
    private final Clue clueLeague;
    private final Clue clueClub;
    private final Clue cluePosition;

    public GuessResult(Clue clueName, Clue clueAge, Clue clueCountry, Clue clueLeague,
                            Clue clueClub, Clue cluePosition){
        this.clueName = clueName;
        this.clueAge = clueAge;
        this.clueCountry = clueCountry;
        this.clueLeague = clueLeague;
        this.clueClub = clueClub;
        this.cluePosition = cluePosition;
    }

    public Clue getClueName() {
        return clueName;
    }

    public Clue getClueAge() {
        return clueAge;
    }

    public Clue getClueCountry() {
        return clueCountry;
    }

    public Clue getClueLeague() {
        return clueLeague;
    }

    public Clue getClueClub() {
        return clueClub;
    }

    public Clue getCluePosition() {
        return cluePosition;
    }
}
