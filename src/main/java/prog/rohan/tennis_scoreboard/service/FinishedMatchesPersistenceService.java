package prog.rohan.tennis_scoreboard.service;

import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.entity.Match;
import prog.rohan.tennis_scoreboard.entity.Player;
import prog.rohan.tennis_scoreboard.repository.MatchRepository;


public class FinishedMatchesPersistenceService {
    MatchRepository matchRepository = MatchRepository.getINSTANCE();

    public void save(FinishedMatchDTO matchDTO){
        Match match = Match.builder()
                .player1(new Player(matchDTO.getFirstPlayer().getId(),
                        matchDTO.getFirstPlayer().getName()))
                .player2(new Player(matchDTO.getSecondPlayer().getId(),
                        matchDTO.getSecondPlayer().getName()))
                .winner(new Player(matchDTO.getWinner().getId(),
                        matchDTO.getWinner().getName()))
                .build();

       matchRepository.save(match);
    }

}
