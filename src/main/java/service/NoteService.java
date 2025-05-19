package service;

import model.*;
import org.hibernate.Session;

import java.time.LocalDate;

import static database.HibernateUtil.sessionFactory;


public class NoteService extends ItemService {

    @Override
    public SafeItem getItem(User user,String title) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            return session.createQuery(
                            "FROM Note WHERE title = :title AND safe.id = :safeId", Note.class)
                    .setParameter("title", title)
                    .setParameter("safeId", safeId)
                    .uniqueResult();
        }
    }

    @Override
    public void removeItem(int id) {
        try (Session session = sessionFactory.openSession()) {
            Note note = session.get(Note.class, id);
            session.beginTransaction();
            session.delete(note);
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

            Note note = new Note();
            note.setTitle(s1);
            note.setContent(s2);
            note.setSafe(user.getSafe());
            session.beginTransaction();
            session.save(note);
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
            Note result = session.createQuery(
                            "FROM Note p WHERE p.content = :s1 and p.safe.id=: safeId", Note.class)
                    .setParameter("safeId", safeId)
                    .setParameter("s1", s1)
                    .uniqueResult();
            result.setContent(s2);
            session.update(result);
            session.getTransaction().commit();
        }
    }

}
