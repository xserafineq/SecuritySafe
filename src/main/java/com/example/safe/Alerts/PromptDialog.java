package com.example.safe.Alerts;

import com.example.safe.Controllers.SafeController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import service.HyperLinkService;
import service.NoteService;
import service.PasswordService;

public class PromptDialog {

    public PromptDialog(String option, String inputLabel1, String inputLabel2,String inputLabel3, User user) {
        Stage stage = new Stage();

        TextField input1 = new TextField();
        input1.setPromptText(inputLabel1);
        TextField input2 = new TextField();
        input2.setPromptText(inputLabel2);
        TextField input3 = new TextField();
        input3.setPromptText(inputLabel3);

        input2.setMinHeight(50);

        Button accept = new Button("Akceptuj");


        switch (option) {
            case "Hasło":
                accept.setOnAction( e2-> {
                    new PasswordService().addItem(input1.getText(),input2.getText(),input3.getText(),user);
                    stage.close();
                });
                break;
            case "Link":
                input3.setManaged(false);
                input3.setEditable(false);
                accept.setOnAction( e2-> {
                    new HyperLinkService().addItem(input1.getText(),input2.getText(),user);
                    stage.close();
                });
                break;
            case "Notatka":
                input3.setManaged(false);
                input3.setEditable(false);
                accept.setOnAction( e2-> {
                    new NoteService().addItem(input1.getText(),input2.getText(),user);
                    stage.close();
                });
                break;
        }

        VBox layout = new VBox(10,
                input1,
                input2,
                input3,
                accept);
        layout.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new javafx.scene.Scene(layout, 400, 300));
        stage.setTitle(option);
        stage.show();

    }

}