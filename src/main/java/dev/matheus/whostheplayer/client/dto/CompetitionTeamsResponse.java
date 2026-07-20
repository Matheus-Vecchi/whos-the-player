package dev.matheus.whostheplayer.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompetitionTeamsResponse (
        CompetitionDto competition,
        List<TeamDto> teams
) {}
