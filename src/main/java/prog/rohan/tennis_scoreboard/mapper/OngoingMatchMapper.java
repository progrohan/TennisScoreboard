package prog.rohan.tennis_scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.service.PlayerService;

@Mapper
public interface OngoingMatchMapper {

    OngoingMatchMapper INSTANCE = Mappers.getMapper(OngoingMatchMapper.class);

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
