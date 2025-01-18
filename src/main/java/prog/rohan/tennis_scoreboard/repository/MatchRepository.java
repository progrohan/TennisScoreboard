package prog.rohan.tennis_scoreboard.repository;

import jakarta.persistence.criteria.CriteriaQuery;
import lombok.Getter;
import org.hibernate.Session;
import prog.rohan.tennis_scoreboard.entity.Match;
import prog.rohan.tennis_scoreboard.exceptions.DataBaseException;

import java.util.List;

public class MatchRepository extends BaseRepository<Long, Match>{
    @Getter
    private static final MatchRepository INSTANCE = new MatchRepository();

    private MatchRepository() {
        super(Match.class);
    }

    public List<Match> findWithPagination(int offset, int count){
        try(Session session = sessionFactory.openSession()){
            List<Match> result = session.createQuery("FROM Match ORDER BY id DESC", Match.class)
                    .setFirstResult(offset * count + 1)
                    .setMaxResults(count)
                    .getResultList();
            return result;
        }catch(Exception e){
            throw new DataBaseException("Problem with finding with pagination in database!");
        }
    }

}
