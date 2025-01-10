package prog.rohan.tennis_scoreboard.service;


import lombok.Getter;
import prog.rohan.tennis_scoreboard.dto.OngoingMatchDTO;
import prog.rohan.tennis_scoreboard.dto.PlayerDTO;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {
    @Getter
    private static OngoingMatchService INSTANCE = new OngoingMatchService();
    private Map<UUID, OngoingMatchDTO> ongoingMatches = new ConcurrentHashMap<>();

    private OngoingMatchService(){};

    public OngoingMatchDTO getByUUId(UUID uuid){
        return ongoingMatches.get(uuid);
    }

    public UUID startNewMatch(PlayerDTO firstPlayer, PlayerDTO secondPlayer){
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

    public void deleteMatch(UUID uuid){
        ongoingMatches.remove(uuid);
    }


}
