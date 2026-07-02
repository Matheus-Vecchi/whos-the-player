package dev.matheus.whostheplayer.dto;

import dev.matheus.whostheplayer.enums.GameMode;

public record GameModeRequest (
    GameMode gameMode
) {}
