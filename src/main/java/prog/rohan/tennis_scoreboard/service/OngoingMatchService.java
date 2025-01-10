package prog.rohan.tennis_scoreboard.service;

import lombok.Getter;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerResponseDTO;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {
    @Getter
    private OngoingMatchService INSTANCE = new OngoingMatchService();
    private Map<UUID, OngoingMatchDTO> ongoingMatches = new ConcurrentHashMap<>();

    private OngoingMatchService(){};

    OngoingMatchDTO getByUUId(UUID uuid){
        return ongoingMatches.get(uuid);
    }

    UUID startNewMatch(PlayerResponseDTO firstPlayer, PlayerResponseDTO secondPlayer){
        UUID uuid = UUID.randomUUID();
        OngoingMatchDTO ongoingMatch = OngoingMatchDTO
                                                    .builder()
                                                    .uuid(uuid)
                                                    .firstPlayer(firstPlayer)
                                                    .secondPlayer(secondPlayer)
                                                    .build();
        ongoingMatches.put(uuid, ongoingMatch);
        return uuid;
    }

    void deleteMatch(UUID uuid){
        ongoingMatches.remove(uuid);
    }


}
