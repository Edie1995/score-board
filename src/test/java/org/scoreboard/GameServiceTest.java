package org.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @Test
    public void shouldAddGameResultToScoreBoard() {
        gameService.startGame("Team A", "Team B");
        assertEquals(1, gameService.getGameResults().size());
    }

    @Test
    public void shouldRemoveGameResultFromScoreBoardWhenGameFinish() {
        var startedGame = gameService.startGame("Team A", "Team B");
        assertEquals(1, gameService.getGameResults().size());
        gameService.endGame(startedGame);
        assertEquals(0, gameService.getGameResults().size());
    }

    @Test
    public void shouldBlockAddingTheGameWithTeamAlreadyPlaying() {
        var startedGame = gameService.startGame("Team A", "Team B");
        assertThrows(AlreadyExistingTeamException.class, () -> gameService.startGame("Team A", "Team C"));
    }

    @Test
    public void shouldUpdateScoreOfTheHomeTeam() {
        var startedGame = gameService.startGame("Team A", "Team B");
        gameService.updateHomeScore(startedGame);
        assertEquals(1, startedGame.homeTeam().getScore());
    }

    @Test
    public void shouldUpdateScoreOfTheAwayTeam() {
        var startedGame = gameService.startGame("Team A", "Team B");
        gameService.updateAwayScore(startedGame);
        assertEquals(1, startedGame.awayTeam().getScore());
    }

    @Test
    public void shouldSortDescByTotalScore(){
        var firstGame = gameService.startGame("Team A", "Team B");
        var secondGame = gameService.startGame("Team C", "Team D");
        var thirdGame = gameService.startGame("Team E", "Team F");
        gameService.updateAwayScore(firstGame);
        gameService.updateAwayScore(firstGame);
        gameService.updateAwayScore(firstGame);
        gameService.updateAwayScore(thirdGame);
        gameService.updateAwayScore(thirdGame);

        var expectedOrder = List.of(firstGame, thirdGame, secondGame);

        var result = gameService.getSummaryByTotalPoints();

        assertEquals(expectedOrder, new ArrayList<>(result));

    }

    @Test
    public void shouldSortDescByNewestIfScoresEqual(){
        var firstGame = gameService.startGame("Team A", "Team B");
        var secondGame = gameService.startGame("Team C", "Team D");
        var thirdGame = gameService.startGame("Team E", "Team F");
        gameService.updateAwayScore(firstGame);
        gameService.updateAwayScore(firstGame);
        gameService.updateAwayScore(secondGame);
        gameService.updateAwayScore(secondGame);
        gameService.updateAwayScore(thirdGame);

        var expectedOrder = List.of(secondGame, firstGame, thirdGame);

        var result = gameService.getSummaryByTotalPoints();

        assertEquals(expectedOrder, new ArrayList<>(result));

    }

}
