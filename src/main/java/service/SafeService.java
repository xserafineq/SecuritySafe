package service;

import model.HyperLink;
import model.Note;
import model.Password;
import model.User;
import org.hibernate.Session;

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

    public List<Password> getPasswords(User user) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            List<Password> passwords = session.createQuery("FROM Password WHERE safe.id = :safeId", Password.class)
                    .setParameter("safeId", safeId)
                    .getResultList();
            return passwords;
        }
    }

    public List<HyperLink> getHyperLinks(User user) {
        try (Session session = sessionFactory.openSession()) {
            int safeId = user.getSafe().getId();
            List<HyperLink> hyperLinks = session.createQuery("FROM HyperLink WHERE safe.id = :safeId", HyperLink.class)
                    .setParameter("safeId", safeId)
                    .getResultList();
            return hyperLinks;
        }
    }


}
