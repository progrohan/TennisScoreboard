package prog.rohan.tennis_scoreboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinishedMatchDTO {
    private PlayerDTO firstPlayer;
    private PlayerDTO secondPlayer;
    private PlayerDTO winner;
}
