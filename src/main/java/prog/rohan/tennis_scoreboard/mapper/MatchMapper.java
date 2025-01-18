package prog.rohan.tennis_scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.entity.Match;
import prog.rohan.tennis_scoreboard.service.PlayerService;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mappings({
            @Mapping(source = "firstPlayer", target = "player1"),
            @Mapping(source = "secondPlayer", target = "player2"),
            @Mapping(target = "id", ignore = true)
    })
    Match toEntity(FinishedMatchDTO matchDTO);

    @Mappings({
            @Mapping(source = "player1", target = "firstPlayer"),
            @Mapping(source = "player2", target = "secondPlayer"),
    })
    FinishedMatchDTO toDto(Match match);

    @Mapping(source = "winnerId", target = "winner", qualifiedByName = "mapWinnerIdToWinner")
    FinishedMatchDTO toFinished(OngoingMatchDTO ongoingMatchDTO);

    @Named("mapWinnerIdToWinner")
    default PlayerDTO mapWinnerIdToWinner(Long id) {
        if (id == null) {
            return null;
        }
        return getPlayerService().findById(id);
    }

    default PlayerService getPlayerService() {
        return new PlayerService();
    }

}
