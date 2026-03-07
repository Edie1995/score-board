package org.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @Test
    public void shouldAddGameResultToScoreBoard() {
        // when
        gameService.startGame("Team A", "Team B");
        // then
        assertEquals(1, gameService.getGameResults()
                .size());
    }

    @Test
    public void shouldRemoveGameResultFromScoreBoardWhenGameFinish() {
        // given
        var startedGame = gameService.startGame("Team A", "Team B");
        // when
        gameService.endGame(startedGame);
        // then
        assertEquals(0, gameService.getGameResults()
                .size());
    }

    @Test
    public void shouldBlockAddingTheGameWithTeamAlreadyPlaying() {
        // given
        var startedGame = gameService.startGame("Team A", "Team B");
        // when&then
        assertThrows(AlreadyExistingTeamException.class, () -> gameService.startGame("Team A", "Team C"));
    }

    @Test
    public void shouldUpdateScore() {
        // given
        var startedGame = gameService.startGame("Team A", "Team B");
        // when
        gameService.updateGameScore(startedGame, 2, 3);
        // then
        assertEquals(2, startedGame.homeTeam()
                .getScore());
        assertEquals(3, startedGame.awayTeam()
                .getScore());
    }

    @Test
    public void shouldSortDescByTotalScore() {
        // given
        var firstGame = gameService.startGame("Team A", "Team B");
        var secondGame = gameService.startGame("Team C", "Team D");
        var thirdGame = gameService.startGame("Team E", "Team F");
        var expectedOrder = List.of(firstGame, thirdGame, secondGame);

        gameService.updateGameScore(firstGame, 2, 3);
        gameService.updateGameScore(secondGame, 2, 0);
        gameService.updateGameScore(thirdGame, 2, 2);
        // when
        var result = gameService.getSummaryByTotalPoints();
        // then
        assertEquals(expectedOrder, new ArrayList<>(result));

    }

    @Test
    public void shouldSortDescByNewestIfScoresEqual() {
        // given
        var firstGame = gameService.startGame("Team A", "Team B");
        var secondGame = gameService.startGame("Team C", "Team D");
        var thirdGame = gameService.startGame("Team E", "Team F");
        var fourthGame = gameService.startGame("Team G", "Team H");

        var expectedOrder = List.of(secondGame, firstGame, fourthGame, thirdGame);

        gameService.updateGameScore(firstGame, 3, 3);
        gameService.updateGameScore(secondGame, 1, 5);
        gameService.updateGameScore(thirdGame, 0, 1);
        gameService.updateGameScore(fourthGame, 0, 1);
        // when
        var result = gameService.getSummaryByTotalPoints();
        // then
        assertEquals(expectedOrder, new ArrayList<>(result));
    }

    @Test
    public void shouldNotAllowNegativeScore() {
        // given
        var game = gameService.startGame("Team A", "Team B");
        // when & then
        assertThrows(NegativeScoreException.class, () -> gameService.updateGameScore(game, -1, 0));
    }
}
