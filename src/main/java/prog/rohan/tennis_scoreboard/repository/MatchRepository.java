package prog.rohan.tennis_scoreboard.repository;

import lombok.Getter;
import prog.rohan.tennis_scoreboard.entity.Match;

public class MatchRepository extends BaseRepository<Long, Match>{
    @Getter
    private static final MatchRepository INSTANCE = new MatchRepository();

    private MatchRepository() {
        super(Match.class);
    }

}
