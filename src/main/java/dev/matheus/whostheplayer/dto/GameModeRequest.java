package dev.matheus.whostheplayer.dto;

import dev.matheus.whostheplayer.enums.GameMode;
import jakarta.validation.constraints.NotNull;

public record GameModeRequest (
    @NotNull
    GameMode gameMode
) {}
