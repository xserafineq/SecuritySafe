package service;

import model.*;
import org.hibernate.Session;

import java.time.LocalDate;

import static database.HibernateUtil.sessionFactory;

public class HyperLinkService extends ItemService {

    @Override
    public SafeItem getItem(User user, String title) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            return session.createQuery(
                            "FROM HyperLink WHERE safe.id = :safeId and title = :title", HyperLink.class)
                    .setParameter("safeId", safeId)
                    .setParameter("title", title)
                    .uniqueResult();
        }
    }

    @Override
    public void removeItem(int id) {
        try (Session session = sessionFactory.openSession()) {
            HyperLink hyperLink = session.get(HyperLink.class, id);
            session.beginTransaction();
            session.delete(hyperLink);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void addItem(String s1, String s2, String s3, User user) {

    }

    @Override
    public void addItem(String s1, String s2, User user) {
        try (Session session = sessionFactory.openSession()) {

            HyperLink hyperLink = new HyperLink();
            hyperLink.setTitle(s1);
            hyperLink.setUrl(s2);
            hyperLink.setSafe(user.getSafe());
            hyperLink.setLastModified(LocalDate.now());

            session.beginTransaction();
            session.save(hyperLink);
            session.getTransaction().commit();

        }
    }

    @Override
    public void updateItem(String s1, String s2,String s3,User user) {

    }

    @Override
    public void updateItem(String s1, String s2, User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            int safeId = user.getSafe().getId();
            HyperLink result = session.createQuery(
                            "FROM HyperLink p WHERE p.url = :s1 and p.safe.id=: safeId", HyperLink.class)
                    .setParameter("safeId", safeId)
                    .setParameter("s1", s1)
                    .uniqueResult();
            result.setUrl(s2);
            session.update(result);
            session.getTransaction().commit();
        }
    }

}
