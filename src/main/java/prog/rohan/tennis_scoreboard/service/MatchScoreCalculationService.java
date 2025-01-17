package prog.rohan.tennis_scoreboard.service;

import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.Score;

import java.util.Objects;

public class MatchScoreCalculationService {
    private final int POINTS_TO_GAME = 4;
    private final int POINTS_TO_WIN_TIE_BREAK = 7;
    private final int GAMES_TO_SET = 6;
    private final int SETS_TO_WIN = 2;
    private final int NECESSARY_DIFFERENCE = 2;

    private final String[] pointsDisplay = {"0","15", "30", "40", "AD"};

    public OngoingMatchDTO increaseScore(OngoingMatchDTO ongoingMatch, Long winnerId){
        if(Objects.equals(ongoingMatch.getFirstPlayer().getId(), winnerId))
        {
            increasePoints(ongoingMatch.getFirstPlayerScore(),
                    ongoingMatch.getSecondPlayerScore());
            ongoingMatch.setWinnerId(ongoingMatch.getFirstPlayer().getId());
        }else{
            increasePoints(ongoingMatch.getSecondPlayerScore(), ongoingMatch.getFirstPlayerScore());
            ongoingMatch.setWinnerId(ongoingMatch.getSecondPlayer().getId());
        }

        return ongoingMatch;
    }

    public boolean isMatchOver(OngoingMatchDTO match){
        return match.getFirstPlayerScore().getSets() >= SETS_TO_WIN
               || match.getSecondPlayerScore().getSets() >= SETS_TO_WIN;
    }

    private void increasePoints(Score winnerScore, Score loserScore) {
        if (isTieBreak(winnerScore, loserScore)) {
            increaseTieBrakePoints(winnerScore, loserScore);
        } else {
            if (winnerScore.getPoints() == POINTS_TO_GAME) {
                decreaseBothPoints(winnerScore);
                decreaseBothPoints(loserScore);
            }
            increaseBothPoints(winnerScore);
            if (winnerScore.getPoints() == POINTS_TO_GAME &&
                loserScore.getPoints() == POINTS_TO_GAME) {
                decreaseBothPoints(winnerScore);
                decreaseBothPoints(loserScore);
            } else if (winnerScore.getPoints() == POINTS_TO_GAME &&
                       (winnerScore.getPoints() - loserScore.getPoints() >= NECESSARY_DIFFERENCE)) {
                makeZeroPoints(winnerScore, loserScore);
                increaseGames(winnerScore, loserScore);
            }
        }
    }


    private void increaseGames(Score winnerScore, Score loserScore){
            winnerScore.setGames(winnerScore.getGames() + 1);
            if(winnerScore.getGames() >= GAMES_TO_SET &&
               winnerScore.getGames() - loserScore.getGames() >= NECESSARY_DIFFERENCE) {
                makeZeroGames(winnerScore, loserScore);
                increaseSets(winnerScore, loserScore);
            }
    }

    private void increaseSets(Score winnerScore, Score loserScore){
        winnerScore.setSets(winnerScore.getSets() + 1);
    }

    private void increaseTieBrakePoints(Score winnerScore, Score loserScore) {
        increaseBothTieBrakePoints(winnerScore);
        if (winnerScore.getPoints() >= POINTS_TO_WIN_TIE_BREAK &&
            winnerScore.getPoints() - loserScore.getPoints() >= NECESSARY_DIFFERENCE) {
            makeZeroPoints(winnerScore, loserScore);
            makeZeroGames(winnerScore, loserScore);
            increaseSets(winnerScore, loserScore);
        }
    }

    private void makeZeroPoints(Score firstPlayer, Score secondPlayer){
        firstPlayer.setPoints(0);
        firstPlayer.setDisplayedPoints(pointsDisplay[0]);
        secondPlayer.setPoints(0);
        secondPlayer.setDisplayedPoints(pointsDisplay[0]);
    }

    private void makeZeroGames(Score firstPlayer, Score secondPlayer){
        firstPlayer.setGames(0);
        secondPlayer.setGames(0);
    }

    private void increaseBothPoints(Score winnerScore){
        winnerScore.setPoints(winnerScore.getPoints() + 1);
        winnerScore.setDisplayedPoints(pointsDisplay[winnerScore.getPoints()]);
    }

    private void increaseBothTieBrakePoints(Score winnerScore){
        winnerScore.setPoints(winnerScore.getPoints() + 1);
        winnerScore.setDisplayedPoints(String.valueOf(winnerScore.getPoints()));
    }

    private void decreaseBothPoints(Score winnerScore){
        winnerScore.setPoints(winnerScore.getPoints() - 1);
        winnerScore.setDisplayedPoints(pointsDisplay[winnerScore.getPoints()]);
    }

    private boolean isTieBreak(Score first, Score second){
        return first.getGames() == GAMES_TO_SET && second.getGames() == GAMES_TO_SET;
    }
}
