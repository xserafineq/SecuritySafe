package service;

import model.*;
import org.controlsfx.control.PropertySheet;
import org.hibernate.Session;
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


}
