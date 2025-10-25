package com.dariuslucas.footballscout.dto.response;

import com.dariuslucas.footballscout.dto.summary.ClubSummary;

public record PlayerResponse(
        Integer id,
        String name,
        String nationality,
        String shirtNumber,
        Integer gamesPlayed,
        Integer goals,
        Integer assists,
        ClubSummary club
) {}