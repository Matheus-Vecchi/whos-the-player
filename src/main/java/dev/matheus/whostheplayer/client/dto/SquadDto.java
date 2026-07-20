package dev.matheus.whostheplayer.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SquadDto (
        String name,
        String position,
        String dateOfBirth,
        String nationality
) {}

