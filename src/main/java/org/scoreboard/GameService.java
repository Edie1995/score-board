package org.scoreboard;

import lombok.Getter;

import java.util.LinkedHashSet;
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
        if(isTeamAlreadyPlaying(newGame)) {
            throw new AlreadyExistingTeamException();
        }
        gameResults.add(newGame);
        return newGame;
    }

    public void endGame(GameResult game) {
        gameResults.remove(game);
    }

    public void updateHomeScore(GameResult game) {
        game.homeTeam()
                .increaseScore();
    }

    public void updateAwayScore(GameResult game) {
        game.awayTeam()
                .increaseScore();
    }

    public Set<GameResult> getSummaryByTotalPoints(){
        return gameResults;
    }

    private boolean isTeamAlreadyPlaying(GameResult game) {
        var allTeams = gameResults.stream()
                .flatMap(x -> Stream.of(x.awayTeam().getTeamName(), x.homeTeam().getTeamName()))
                .collect(Collectors.toSet());

        return allTeams.contains(game.homeTeam().getTeamName()) || allTeams.contains(game.awayTeam().getTeamName());
    }

}
