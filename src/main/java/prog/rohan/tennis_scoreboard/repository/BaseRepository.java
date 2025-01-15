package prog.rohan.tennis_scoreboard.repository;

import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prog.rohan.tennis_scoreboard.exceptions.DataBaseException;
import prog.rohan.tennis_scoreboard.utils.SessionManager;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<ID extends Serializable, E >
        implements CrudRepository<ID, E> {

    private final Class<E> entityClass;
    protected final SessionFactory sessionFactory = SessionManager.getSessionFactory();

    @Override
    public E save(E entity){
        try(Session session = sessionFactory.openSession()){

            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();

            return entity;
        }catch(Exception e){
            throw new DataBaseException("Problem with saving in database!");
        }
    }

    @Override
    public Optional<E> findById(ID id) {
        try(Session session = sessionFactory.openSession()){

            E e = session.find(entityClass, id);

            return Optional.ofNullable(e);
        }catch(Exception e){
            throw new DataBaseException("Problem with finding by id in database!");
        }
    }

    @Override
    public void delete(ID id) {
        try(Session session = sessionFactory.openSession()){

            E e = session.find(entityClass, id);

            session.beginTransaction();
            session.remove(e);
            session.getTransaction().commit();

        }catch(Exception e){
            throw new DataBaseException("Problem with deleting from database!");
        }
    }

    @Override
    public E update(E entity) {
        try(Session session = sessionFactory.openSession()){

            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();

            return entity;
        }catch(Exception e){
            throw new DataBaseException("Problem with updating in database!");
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {

            CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
            criteria.from(entityClass);

            return session.createQuery(criteria).getResultList();
        }catch(Exception e){
            throw new DataBaseException("Problem with selecting all from database!");
        }
    }
}
