package service;
import model.Password;
import model.Safe;
import model.SafeItem;
import model.User;
import org.hibernate.Session;

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


}
