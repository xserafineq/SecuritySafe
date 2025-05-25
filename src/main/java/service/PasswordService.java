package service;
import model.*;
import org.hibernate.Session;

import java.time.LocalDate;

import static database.HibernateUtil.sessionFactory;

public class PasswordService extends ItemService{
    @Override
    public SafeItem getItem(User user, String title) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            return session.createQuery(
                            "FROM Password WHERE safe.id = :safeId and name = :name", Password.class)
                    .setParameter("safeId", safeId)
                    .setParameter("name", title)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    @Override
    public void removeItem(int id) {
        try (Session session = sessionFactory.openSession()) {
            Password password = session.get(Password.class, id);
            session.beginTransaction();
            session.delete(password);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void addItem(String login, String password, String title, User user) {
        try (Session session = sessionFactory.openSession()) {

            Password newPassword = new Password();
            newPassword.setLogin(login);
            newPassword.setPassword(password);
            newPassword.setLast_modified(LocalDate.now());
            newPassword.setSafe(user.getSafe());
            newPassword.setName(title);

            session.beginTransaction();
            session.save(newPassword);
            session.getTransaction().commit();
        }
    }

    @Override
    public void addItem(String s1, String s2, User user) {

    }

    @Override
    public void updateItem(String s1, String s2, String s3, User user) {

    }

    @Override
    public void updateItem(String s1, String s2, String s3, String s4, User user) {
        System.out.println("xd:" + s1 + " " + s2 + " " + s3);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            int safeId = user.getSafe().getId();
            Password result = session.createQuery(
                            "FROM Password p WHERE p.login = :s1 and p.safe.id=: safeId and p.password = :s4", Password.class)
                    .setParameter("safeId", safeId)
                    .setParameter("s1", s1)
                    .setParameter("s4", s4)
                    .uniqueResult();
            result.setLogin(s2);
            result.setPassword(s3);
            session.update(result);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateItem(String s1, String s2, User user) {

    }
}
