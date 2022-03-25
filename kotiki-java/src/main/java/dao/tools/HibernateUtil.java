package dao.tools;

import org.hibernate.SessionFactory;

import java.io.File;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(
                    new File("C:\\Users\\HTMLD\\Documents\\GitHub\\" +
                            "ferbatorTeh\\kotiki-java\\src\\main\\resources\\" +
                            "hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initSessionFactory();
        }

        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
