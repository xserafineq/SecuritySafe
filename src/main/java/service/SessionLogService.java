package service;

import model.Note;
import model.SessionLog;
import model.User;
import org.hibernate.Session;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;

import static database.HibernateUtil.sessionFactory;

public class SessionLogService {

    public void addSessionLog(boolean loginSucces, User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            SessionLog sessionLog = new SessionLog();
            sessionLog.setUser(user);

            InetAddress ip = InetAddress.getLocalHost();
            sessionLog.setIpAdress(ip.getHostAddress());
            sessionLog.setStatus(loginSucces ? "OK" : "FAIL");
            sessionLog.setLoginTime(LocalDate.now());
            session.save(sessionLog);
            session.getTransaction().commit();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
