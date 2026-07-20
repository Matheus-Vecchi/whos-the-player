package dev.matheus.whostheplayer.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompetitionDto (
        String name
) {}
