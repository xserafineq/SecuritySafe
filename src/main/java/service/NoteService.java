package service;

import model.Note;
import model.User;
import org.hibernate.Session;
import static database.HibernateUtil.sessionFactory;

public class NoteService {


    public Note getNote(User user,String title) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            Note note = session.createQuery(
                            "FROM Note WHERE title = :title AND safe.id = :safeId", Note.class)
                    .setParameter("title", title)
                    .setParameter("safeId", safeId)
                    .uniqueResult();
            return note;
        }
    }

}
