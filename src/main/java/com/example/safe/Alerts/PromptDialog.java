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

    private boolean result = false;

    public PromptDialog(String option, String inputLabel1, String inputLabel2,String inputLabel3, User user) {
        Stage stage = new Stage();

        TextField input1 = new TextField();
        input1.setPromptText(inputLabel1);
        TextArea input2 = new TextArea();
        input2.setPromptText(inputLabel2);
        input2.setWrapText(true);
        TextField input3 = new TextField();
        input3.setPromptText(inputLabel3);


        Button accept = new Button("Akceptuj");


        switch (option) {
            case "Hasło":
                input2.setMaxHeight(40);
                accept.setOnAction( e2-> {
                    this.result = true;
                    new PasswordService().addItem(input1.getText(),input2.getText(),input3.getText(),user);
                    stage.close();
                });
                break;
            case "Link":
                input2.setMaxHeight(100);
                input3.setManaged(false);
                input3.setEditable(false);
                accept.setOnAction( e2-> {
                    this.result = true;
                    new HyperLinkService().addItem(input1.getText(),input2.getText(),user);
                    stage.close();
                });
                break;
            case "Notatka":
                input2.setMaxHeight(200);
                input3.setManaged(false);
                input3.setEditable(false);
                accept.setOnAction( e2-> {
                    this.result = true;
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
        stage.showAndWait();
    }

    public boolean isResult() {
        return result;
    }
}