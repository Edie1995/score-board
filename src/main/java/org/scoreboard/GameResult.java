package org.scoreboard;

import lombok.Builder;

@Builder
public record GameResult(TeamScore homeTeam, TeamScore awayTeam) {
}
