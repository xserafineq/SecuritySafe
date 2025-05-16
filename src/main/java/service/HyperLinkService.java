package service;

import model.HyperLink;
import model.Password;
import model.SafeItem;
import model.User;
import org.hibernate.Session;

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

}
