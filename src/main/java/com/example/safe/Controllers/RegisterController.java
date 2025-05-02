package com.example.safe.Controllers;

import com.example.safe.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import org.hibernate.cfg.Environment;
import service.UserService;
import java.awt.*;
import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField pin;


    @FXML
    public void register(javafx.event.ActionEvent event) throws IOException {
        User user = new User();
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        new UserService().registerUser(user);
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("safe.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }



}
