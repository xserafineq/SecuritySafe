package com.example.safe.Controllers;

import database.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class HelloController {
    @FXML
    private Button login;

    @FXML
    private void login(ActionEvent event) throws IOException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            List<User> users = query.getResultList();

            for (User user : users) {
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
