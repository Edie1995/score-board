package org.scoreboard;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ScoreBoard {
    private final Set<GameResult> gameResults = new HashSet<>();

    public GameResult addGameResult(GameResult gameResult) {
        gameResults.add(gameResult);
        return gameResult;
    }
}
