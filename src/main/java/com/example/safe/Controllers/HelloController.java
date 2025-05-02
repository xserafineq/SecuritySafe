package com.example.safe.Controllers;

import com.example.safe.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;
import service.ViewChanger;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button login;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private void login(ActionEvent event) throws IOException {
        UserService equalDataService = new UserService();

        boolean loginSuccesfull = equalDataService.equalLoginData(email.getText(), password.getText());
        if (loginSuccesfull) {
            System.out.println("Login successful");
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("safe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            System.out.println("Login failed");
        }

    }
    @FXML
    private void registerView(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
