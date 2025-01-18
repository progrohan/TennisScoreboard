package prog.rohan.tennis_scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import prog.rohan.tennis_scoreboard.dto.FinishedMatchDTO;
import prog.rohan.tennis_scoreboard.entity.Match;

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

}
