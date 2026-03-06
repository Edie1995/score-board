package org.scoreboard;

import lombok.Getter;

@Getter
public class TeamScore {
    private final String teamName;
    private int score;

    public TeamScore(String teamName) {
        this.teamName = teamName;
        this.score = 0;
    }

    public void increaseScore() {
        this.score++;
    }


}
