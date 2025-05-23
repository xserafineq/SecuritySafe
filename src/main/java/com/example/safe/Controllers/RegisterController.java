package com.example.safe.Controllers;

import com.example.safe.Alerts.ConnectionAlert;
import com.example.safe.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import service.UserService;
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
        try {
            new UserService().registerUser(user);
        }catch (Exception e){
            new ConnectionAlert("Błąd","Wystąpił błąd podczas rejestracji",
                    "Spróbuj ponownie", Alert.AlertType.ERROR);
            return;
        }

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void loginView(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
