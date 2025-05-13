package service;

import com.example.safe.Controllers.SafeController;
import javafx.scene.control.Alert;
import model.Safe;
import model.User;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static database.HibernateUtil.sessionFactory;

public class UserService {


    public boolean equalLoginData(String email,String password) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                            .uniqueResult();

           if(user==null) {
               return false;
           }
           return new BCryptPasswordEncoder().matches(password,user.getPassword());
        }
    }

    public String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void registerUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user.setPassword(hashPassword(user.getPassword()));
            user.setJoinDate(LocalDate.now());
            Safe safe = new Safe();
            safe.setUser(user);
            user.setSafe(safe);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public User findUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (user != null) {
                Safe safe = session.createQuery("FROM Safe WHERE user = :user", Safe.class)
                        .setParameter("user", user)
                        .uniqueResult();
                        user.setSafe(safe);
                session.getTransaction().commit();

                System.out.println("xd: " + user.getSafe().getId());

                return user;
            }
            else {
                return null;
            }
        }

    }
}
