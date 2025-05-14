package com.example.safe.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.HyperLink;
import model.Note;
import model.Password;
import model.User;
import service.NoteService;
import service.PasswordService;
import service.SafeService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeController {

    private User user;

    @FXML
    ListView<String> itemsList;
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private Text itemsTextName;
    @FXML
    private Text categoryText;
    @FXML
    public ChoiceBox categoryItemChoiceBox;
    @FXML
    public TextField loginInput;
    @FXML
    public TextField passwordInput;
    @FXML
    public Button showBtn;


    public void setData(User user) {
        this.user = user;
    };

    public void start() {


        categoryItemChoiceBox.setOnAction(event -> {

            String option = (String) categoryItemChoiceBox.getSelectionModel().getSelectedItem();
            fillListView(option);

            itemsList.setOnMouseClicked(e -> {
                if(option.equals("Notatki")) {
                    Note note = (Note) new NoteService().getItem(user,itemsList.getSelectionModel().getSelectedItem());
                    System.out.println(note.getTitle());
                    categoryText.setText(itemsList.getSelectionModel().getSelectedItem());
                }
                else if(option.equals("Hasla")) {
                    Password password = (Password) new PasswordService().getItem(user,itemsList.getSelectionModel().getSelectedItem());
                    System.out.println(password.getName());
                    loginInput.setText(password.getLogin());
                    passwordInput.setText("************");
                    String pass = password.getPassword();
                    AtomicBoolean passShowed = new AtomicBoolean(false);
                    showBtn.setOnAction(e2 -> {
                        if(passShowed.get()) {
                            passwordInput.setText("************");
                            passShowed.set(false);
                        }
                        else {
                            passwordInput.setText(pass);
                            passShowed.set(true);
                        }
                    });
                }
            });



        });
    }

    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void fillListView(String option) {
        items.clear();
        itemsList.setItems(items);
        SafeService service = new SafeService();
        switch (option) {
            case "Linki":
                List<HyperLink> hyperLinks = service.getHyperLinks(user);
                for (HyperLink hyperLink : hyperLinks) {
                    items.add(hyperLink.getTitle());
                }
                break;
            case "Notatki":
                List<Note> notes = service.getNotes(user);
                for (Note note : notes) {
                    items.add(note.getTitle());
                }
                break;
            case "Hasla":
                List<Password> passwords = service.getPasswords(user);
                for (Password password : passwords) {
                    items.add(password.getName());
                }
        }
    }

}


