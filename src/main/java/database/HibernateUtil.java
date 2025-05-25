package database;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Note.class)
                    .addAnnotatedClass(Safe.class)
                    .addAnnotatedClass(Password.class)
                    .addAnnotatedClass(HyperLink.class)
                    .addAnnotatedClass(SessionLog.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

