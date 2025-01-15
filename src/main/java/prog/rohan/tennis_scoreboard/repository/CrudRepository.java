package prog.rohan.tennis_scoreboard.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID extends Serializable, E> {

    E save(E entity);

    Optional<E> findById(ID id);

    void delete(ID id);

    E update(E entity);

    List<E> findAll();
}
