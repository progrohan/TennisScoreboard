package prog.rohan.tennis_scoreboard.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.dto.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;
    private OngoingMatchDTO ongoingMatchDTO;
    private Score firstPlayerScore;
    private Score secondPlayerScore;

    @BeforeEach
    void init(){
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.ongoingMatchDTO = OngoingMatchDTO.builder()
                .firstPlayer(new PlayerDTO(0L, "Tima"))
                .secondPlayer(new PlayerDTO(1L, "Venom"))
                .firstPlayerScore(new Score())
                .secondPlayerScore(new Score())
                .build();
        this.firstPlayerScore = ongoingMatchDTO.getFirstPlayerScore();
        this.secondPlayerScore = ongoingMatchDTO.getSecondPlayerScore();
    }

    @Test
    void gettingAdvantageWhenPoints4and3(){
        firstPlayerScore.setPoints(3);
        firstPlayerScore.setDisplayedPoints("40");
        secondPlayerScore.setPoints(3);
        secondPlayerScore.setDisplayedPoints("40");

        matchScoreCalculationService.increaseScore(ongoingMatchDTO, 0L);

        assertEquals(4, firstPlayerScore.getPoints());
        assertEquals("AD", firstPlayerScore.getDisplayedPoints());
        assertEquals(3, secondPlayerScore.getPoints());
        assertEquals("40", secondPlayerScore.getDisplayedPoints());
    }

    @Test
    void losingAdvantageWhenPoints4and4(){
        firstPlayerScore.setPoints(4);
        secondPlayerScore.setPoints(3);

        matchScoreCalculationService.increaseScore(ongoingMatchDTO, 1L);

        assertEquals(3, firstPlayerScore.getPoints());
        assertEquals("40", firstPlayerScore.getDisplayedPoints());
        assertEquals(3, secondPlayerScore.getPoints());
        assertEquals("40", secondPlayerScore.getDisplayedPoints());
    }

    @Test
    void winningGameWhenPoints4and0(){
        firstPlayerScore.setPoints(3);
        secondPlayerScore.setPoints(0);

        matchScoreCalculationService.increaseScore(ongoingMatchDTO, 0L);

        assertEquals(0, firstPlayerScore.getPoints());
        assertEquals("0", firstPlayerScore.getDisplayedPoints());
        assertEquals(1, firstPlayerScore.getGames());
        assertEquals(0, secondPlayerScore.getPoints());
        assertEquals("0", secondPlayerScore.getDisplayedPoints());
        assertEquals(0, secondPlayerScore.getGames());
    }

    @Test
    void tieBreakWhenGames6and6(){
        firstPlayerScore.setGames(6);
        secondPlayerScore.setGames(6);

        matchScoreCalculationService.increaseScore(ongoingMatchDTO, 0L);

        assertEquals(1, firstPlayerScore.getPoints());
        assertEquals("1", firstPlayerScore.getDisplayedPoints());//1 can be displayed only int tie-break
        assertEquals(0, secondPlayerScore.getPoints());
        assertEquals("0", secondPlayerScore.getDisplayedPoints());
    }

    @Test
    void winningGameAndSetInTieBreakWhenPoints7and5(){
        firstPlayerScore.setGames(6);
        secondPlayerScore.setGames(6);

        firstPlayerScore.setPoints(6);
        secondPlayerScore.setPoints(5);

        matchScoreCalculationService.increaseScore(ongoingMatchDTO, 0L);

        assertEquals(0, firstPlayerScore.getPoints());
        assertEquals("0", firstPlayerScore.getDisplayedPoints());
        assertEquals(0, firstPlayerScore.getGames());
        assertEquals(1, firstPlayerScore.getSets());
        assertEquals(0, secondPlayerScore.getPoints());
        assertEquals("0", secondPlayerScore.getDisplayedPoints());
        assertEquals(0, secondPlayerScore.getGames());
        assertEquals(0, secondPlayerScore.getGames());
    }

}
