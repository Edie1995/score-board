package org.scoreboard;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class GameService {

    private final Set<GameResult> gameResults = new LinkedHashSet<>();

    public GameResult startGame(String homeTeamName, String awayTeamName) {
        var newGame = GameResult.builder()
                .homeTeam(new TeamScore(homeTeamName))
                .awayTeam(new TeamScore(awayTeamName))
                .build();
        if (isTeamAlreadyPlaying(newGame)) {
            throw new AlreadyExistingTeamException();
        }
        gameResults.add(newGame);
        return newGame;
    }

    public void endGame(GameResult game) {
        gameResults.remove(game);
    }

    public void updateGameScore(GameResult game, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new NegativeScoreException();
        }
        game.updateTeamScore(homeScore, awayScore);
    }

    public List<GameResult> getSummaryByTotalPoints() {
        var resultsList = new ArrayList<>(gameResults);
        return resultsList.reversed()
                .stream()
                .sorted(Comparator.comparing(
                        GameResult::getTotalScore,
                        Comparator.reverseOrder())
                )
                .toList();
    }

    private boolean isTeamAlreadyPlaying(GameResult game) {
        var allTeams = gameResults.stream()
                .flatMap(x -> Stream.of(x.awayTeam()
                        .getTeamName(), x.homeTeam()
                        .getTeamName()))
                .collect(Collectors.toSet());

        return allTeams.contains(game.homeTeam()
                .getTeamName()) || allTeams.contains(game.awayTeam()
                .getTeamName());
    }

}
