package org.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {

    private final ScoreBoard scoreBoard = new ScoreBoard();
    private final GameService gameService = new GameService(scoreBoard);

    @Test
    public void shouldAddGameResultToScoreBoard() {
        gameService.startGame("Team A", "Team B");
        assertEquals(1, gameService.getScoreBoard().getGameResults().size());
    }

    @Test
    public void shouldRemoveGameResultFromScoreBoardWhenGameFinish() {
        var startedGame = gameService.startGame("Team A", "Team B");
        assertEquals(1, gameService.getScoreBoard().getGameResults().size());
        gameService.endGame(startedGame);
        assertEquals(0, gameService.getScoreBoard().getGameResults().size());
    }

    @Test
    public void shouldBlockAddingTheGameWithTeamAlreadyPlaying() {
        var startedGame = gameService.startGame("Team A", "Team B");
        var startedGameWithDuplicatedTeam = gameService.startGame("Team A", "Team C");
        assertThrows(AlreadyExistingTeamException.class, () -> gameService.startGame("Team A", "Team C"));
    }

    @Test
    public void shouldUpdateScoreOfTheSelectedTeam() {
        var startedGame = gameService.startGame("Team A", "Team B");
        gameService.updateScore(startedGame, "Team A");
        assertEquals(1, startedGame.homeTeam().getScore());
    }

}
