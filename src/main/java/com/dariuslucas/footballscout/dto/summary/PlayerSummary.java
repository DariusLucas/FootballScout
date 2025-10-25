package com.dariuslucas.footballscout.dto.summary;

public record PlayerSummary(
        Integer id,
        String name,
        String shirtNumber,
        String nationality
) {}