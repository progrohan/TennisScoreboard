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

    public int countWithFilter(String filter){
        return matchRepository.findAllWithFilter(filter).size();
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

    public List<FinishedMatchDTO> findAllWithFilter(String filter){
        List<Match> matches = matchRepository.findAllWithFilter(filter);
        return matches
                .stream()
                .map(MatchMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public List<FinishedMatchDTO> findWithFilterAndPagination(String filter, int offset, int count) {
        List<Match> matches = matchRepository.findWithFilterAndPagination(filter, offset, count);
        return matches
                .stream()
                .map(MatchMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
