package com.example.safe.Alerts;

import javafx.scene.control.Alert;

public class ConnectionAlert {
    Alert alert;
    public ConnectionAlert(String title, String header, String content, Alert.AlertType type) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
