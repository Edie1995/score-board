package org.scoreboard;

import lombok.Getter;
import lombok.Setter;

@Getter
class TeamScore {
    private final String teamName;
    @Setter
    private int score;

    public TeamScore(String teamName) {
        this.teamName = teamName;
        this.score = 0;
    }
}
