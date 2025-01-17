package prog.rohan.tennis_scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private String displayedPoints = "0";
    private int sets;
    private int games;
    private int points;
}
