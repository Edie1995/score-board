package org.scoreboard;

import lombok.Getter;

@Getter
public class GameService {

    private final ScoreBoard scoreBoard;

    public GameService(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public GameResult startGame(String homeTeamName, String awayTeamName){
        //TODO implement start game logic
        return null;
    }

    public void endGame(GameResult game){
        //TODO implement end game logic
    }

    public void updateScore(GameResult game, String teamName){
        //TODO implement update score logic
    }

}
