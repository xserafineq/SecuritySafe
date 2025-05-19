package com.example.safe.Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConfirmationAlert {

    Alert alert;
    private boolean respond;

    public ConfirmationAlert(String title, String header, String content) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = this.alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.respond = true;
        }else {
            this.respond = false;
        }
    }

    public boolean isRespond() {
        return respond;
    }
}
