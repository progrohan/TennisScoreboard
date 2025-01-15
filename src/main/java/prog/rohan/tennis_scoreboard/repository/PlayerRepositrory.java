package prog.rohan.tennis_scoreboard.repository;


import jakarta.persistence.NoResultException;
import lombok.Getter;
import org.hibernate.Session;
import prog.rohan.tennis_scoreboard.entity.Player;
import prog.rohan.tennis_scoreboard.exceptions.DataBaseException;

import java.util.Optional;

public class PlayerRepositrory extends BaseRepository<Long, Player>{
    @Getter
    private static PlayerRepositrory INSTANCE = new PlayerRepositrory();

    private PlayerRepositrory(){
        super(Player.class);
    }

    public Optional<Player> findByName(String name){
        try(Session session = sessionFactory.openSession()){

            Player player = session.createQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();

            return Optional.of(player);
        }catch (NoResultException e) {
            return Optional.empty();
        }catch (Exception e){
            throw new DataBaseException("Problem with finding by id in db!");
        }
    }

}
