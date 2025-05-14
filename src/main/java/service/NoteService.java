package service;

import model.Note;
import model.SafeItem;
import model.User;
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
}
