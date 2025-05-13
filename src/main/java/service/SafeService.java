package service;

import model.Note;
import model.User;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static database.HibernateUtil.sessionFactory;

public class SafeService {

    public List<Note> getNotes(User user) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            List<Note> notes = session.createQuery("FROM Note WHERE safe.id = :safeId", Note.class)
                    .setParameter("safeId", safeId)
                    .getResultList();
            return notes;
        }
    }
}
