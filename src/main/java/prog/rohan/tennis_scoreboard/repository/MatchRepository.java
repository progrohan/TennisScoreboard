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
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
            return result;
        }catch(Exception e){
            throw new DataBaseException("Problem with finding with pagination in database!");
        }
    }

    public List<Match> findAllWithFilter(String filter){
        try(Session session = sessionFactory.openSession()){
            List<Match> result = session
                    .createQuery("FROM Match WHERE LOWER(player1.name) LIKE :filter or LOWER(player2.name) LIKE :filter ORDER BY id DESC", Match.class)
                    .setParameter("filter", filter)
                    .getResultList();
            String pom = "bom";
            return result;
        }catch(Exception e){
            throw new DataBaseException("Problem with finding all with filter in database!");
        }
    }

    public List<Match> findWithFilterAndPagination(String filter,int offset, int count){
        try(Session session = sessionFactory.openSession()){
            List<Match> result = session
                    .createQuery("FROM Match WHERE player1.name = :filter or player2.name = :filter ORDER BY id DESC", Match.class)
                    .setParameter("filter", filter)
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
            return result;
        }catch(Exception e){
            throw new DataBaseException("Problem with finding with filter and pagination in database!");
        }
    }

}
