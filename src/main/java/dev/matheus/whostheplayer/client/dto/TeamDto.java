package dev.matheus.whostheplayer.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamDto (
        String name,
        List<SquadDto> squad
) {}
