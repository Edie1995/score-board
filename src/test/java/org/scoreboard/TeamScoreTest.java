package org.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamScoreTest {

    @Test
    void shouldReturnScoreZeroWhenNewTeamCreated() {
        TeamScore teamScore = new TeamScore("Team A");
        assertEquals(0, teamScore.getScore());
    }
}
