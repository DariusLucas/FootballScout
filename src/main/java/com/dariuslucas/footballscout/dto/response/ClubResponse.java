package com.dariuslucas.footballscout.dto.response;

import com.dariuslucas.footballscout.dto.summary.PlayerSummary;
import java.util.List;

public record ClubResponse(
        Integer id,
        String clubName,
        String country,
        List<PlayerSummary> players
) {}