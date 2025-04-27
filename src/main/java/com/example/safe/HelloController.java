package com.example.safe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button login;

    @FXML
    private void login(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Hello World", ButtonType.OK).show();
        login.setText("Hello World");
    }
}