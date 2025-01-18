package prog.rohan.tennis_scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;
import prog.rohan.tennis_scoreboard.entity.Player;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    Player toEntity(PlayerDTO playerDTO);

    PlayerDTO toDto(Player player);
}
