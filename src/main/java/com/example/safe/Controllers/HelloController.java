package com.example.safe.Controllers;

import com.almasb.fxgl.multiplayer.MultiplayerService;
import com.example.safe.Alerts.ConnectionAlert;
import com.example.safe.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.SessionLogService;
import service.UserService;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button login;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private void login(ActionEvent event) throws Exception {
        UserService equalDataService = new UserService();

        boolean loginSuccesfull = equalDataService.equalLoginData(email.getText(), password.getText());
        if (loginSuccesfull) {
            System.out.println("Login successful");
            new SessionLogService().addSessionLog(loginSuccesfull, new UserService().findUserByEmail(email.getText()));
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("safe.fxml"));
            Parent root = loader.load();

            SafeController safeController = loader.getController();
            safeController.setData(new UserService().findUserByEmail(email.getText()));
            safeController.start();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            new SessionLogService().addSessionLog(loginSuccesfull, new UserService().findUserByEmail(email.getText()));
            new ConnectionAlert("Błąd","Wystąpił błąd podczas logowania",
                    "Spróbuj ponownie", Alert.AlertType.ERROR);
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
