package prog.rohan.tennis_scoreboard.service;

import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.entity.Match;
import prog.rohan.tennis_scoreboard.mapper.MatchMapper;
import prog.rohan.tennis_scoreboard.repository.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;


public class FinishedMatchesPersistenceService {
    MatchRepository matchRepository = MatchRepository.getINSTANCE();

    public void save(FinishedMatchDTO matchDTO){
        Match match = MatchMapper.INSTANCE.toEntity(matchDTO);

       matchRepository.save(match);
    }

    public int count(){
        return matchRepository.findAll().size();
    }

    public List<FinishedMatchDTO> findAll(){
        List<Match> matches = matchRepository.findAll();
        return matches
                .stream()
                .map(MatchMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public List<FinishedMatchDTO> findWithPagination(int offset, int count){
        List<Match> matches = matchRepository.findWithPagination(offset, count);
        return matches
                .stream()
                .map(MatchMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
