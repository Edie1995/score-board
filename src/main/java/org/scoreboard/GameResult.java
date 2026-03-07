package org.scoreboard;

import lombok.Builder;

@Builder
public record GameResult(TeamScore homeTeam, TeamScore awayTeam) {

    public int getTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public void updateTeamScore(int homeTeamScore, int awayTeamScore) {
        homeTeam.setScore(homeTeamScore);
        awayTeam.setScore(awayTeamScore);
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %d - %d", homeTeam.getTeamName(), awayTeam.getTeamName(), homeTeam.getScore(), awayTeam.getScore());
    }
}
