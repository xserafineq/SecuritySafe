package service;

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
            user.setJoineDate(LocalDate.now());
            session.save(user);
            session.getTransaction().commit();
        }
    }
}
