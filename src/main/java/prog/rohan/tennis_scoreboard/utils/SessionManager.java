package prog.rohan.tennis_scoreboard.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@UtilityClass
public class SessionManager {
    @Getter
    private static final SessionFactory sessionFactory =
            new Configuration()
            .configure()
            .buildSessionFactory();
}
