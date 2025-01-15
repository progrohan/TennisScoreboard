package prog.rohan.tennis_scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OngoingMatchDTO {
    private UUID uuid;

    private PlayerDTO firstPlayer;
    private PlayerDTO secondPlayer;

    private Score firstPlayerScore;
    private Score secondPlayerScore;
}
