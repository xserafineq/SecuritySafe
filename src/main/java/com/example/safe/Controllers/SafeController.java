package com.example.safe.Controllers;
import com.example.safe.Alerts.ConfirmationAlert;
import com.example.safe.Alerts.PromptDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.HyperLink;
import model.Note;
import model.Password;
import model.User;
import service.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    public TextArea noteInput;
    @FXML
    public TextArea linkInput;

    @FXML
    public Button showBtn;
    @FXML
    public Button deleteBtn;
    @FXML
    public Button editBtn;
    @FXML
    public Button addBtn;
    @FXML
    public Menu logoutButton;

    @FXML
    public VBox passwordPanel;
    @FXML
    public VBox notesPanel;
    @FXML
    public VBox linksPanel;


    public void setData(User user) {
        this.user = user;
    };

    public void start() throws Exception {

        categoryItemChoiceBox.setOnAction(event -> {

            String option = (String) categoryItemChoiceBox.getSelectionModel().getSelectedItem();
            fillListView(option);

            itemsList.setOnMouseClicked(e -> {
                switch (option) {
                    case "Hasla":
                        Password password = (Password) new PasswordService().getItem(user,itemsList.getSelectionModel().getSelectedItem());
                        System.out.println(password.getName());
                        loginInput.setText(password.getLogin());
                        String oldLogin = password.getLogin();
                        System.out.println(password.getLogin());
                        passwordInput.setText(new PasswordService().hideContent(password.getPassword()));

                        AtomicBoolean passShowed = new AtomicBoolean(false);

                        showBtn.setOnAction(e2 -> {
                            if(passShowed.get()) {
                                passwordInput.setText(new PasswordService().hideContent(password.getPassword()));
                                passShowed.set(false);
                            }
                            else {
                                passwordInput.setText(password.getPassword());
                                passShowed.set(true);
                            }
                        });


                        deleteBtn.setOnAction( e2 -> {
                            if(new ConfirmationAlert("Usuwanie","Czy napewno chcesz usunąć?","Działanie jest nieodwracalne").isRespond()) {
                                new PasswordService().removeItem(password.getId());
                                fillListView(option);
                            };

                        });

                        editBtn.setOnAction( e3 -> {
                            if(new ConfirmationAlert("Edytowanie","Czy napewno chcesz edytowac","Działanie jest nieodwracalne").isRespond()) {
                                new PasswordService().updateItem(oldLogin, loginInput.getText(), passwordInput.getText(), user);
                            }
                        });


                        break;
                    case "Notatki":
                        Note note = (Note) new NoteService().getItem(user,itemsList.getSelectionModel().getSelectedItem());
                        System.out.println(note.getTitle());
                        noteInput.setText(new NoteService().hideContent(note.getContent()));

                        AtomicBoolean noteContentShowed = new AtomicBoolean(false);

                        showBtn.setOnAction(e2 -> {
                            if(noteContentShowed.get()) {
                                noteInput.setText(new NoteService().hideContent(note.getContent()));
                                noteContentShowed.set(false);
                            }
                            else {
                                noteInput.setText(note.getContent());
                                noteContentShowed.set(true);
                            }
                        });

                        deleteBtn.setOnAction(e2->{
                            if(new ConfirmationAlert("Usuwanie","Czy napewno chcesz usunąć?","Działanie jest nieodwracalne").isRespond()) {
                                new NoteService().removeItem(note.getId());
                                fillListView(option);
                            };
                        });

                        editBtn.setOnAction( e3 -> {
                            if(new ConfirmationAlert("Edytowanie","Czy napewno chcesz edytowac","Działanie jest nieodwracalne").isRespond()) {
                                new NoteService().updateItem(note.getContent(),noteInput.getText(),user);
                            }
                        });

                        break;
                    case "Linki":
                        HyperLink link = (HyperLink) new HyperLinkService().getItem(user,itemsList.getSelectionModel().getSelectedItem());
                        System.out.println(link.getTitle());

                        linkInput.setText(new NoteService().hideContent(link.getUrl()));

                        AtomicBoolean linkContentShowed = new AtomicBoolean(false);

                        showBtn.setOnAction(e2 -> {
                            if(linkContentShowed.get()) {
                                linkInput.setText(new NoteService().hideContent(link.getUrl()));
                                linkContentShowed.set(false);
                            }
                            else {
                                linkInput.setText(link.getUrl());
                                linkContentShowed.set(true);
                            }
                        });

                        deleteBtn.setOnAction(e2 -> {
                            if(new ConfirmationAlert("Usuwanie","Czy napewno chcesz usunąć?","Działanie jest nieodwracalne").isRespond()) {
                                new HyperLinkService().removeItem(link.getId());
                                fillListView(option);
                            };
                        });

                        editBtn.setOnAction( e3 -> {
                            if(new ConfirmationAlert("Edytowanie","Czy napewno chcesz edytowac","Działanie jest nieodwracalne").isRespond()) {
                                new HyperLinkService().updateItem(link.getUrl(),noteInput.getText(),user);
                            }
                        });

                        break;
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
                changeCenterSection("Linki");
                List<HyperLink> hyperLinks = service.getHyperLinks(user);
                for (HyperLink hyperLink : hyperLinks) {
                    items.add(hyperLink.getTitle());
                }
                break;
            case "Notatki":
                changeCenterSection("Notatki");
                List<Note> notes = service.getNotes(user);
                for (Note note : notes) {
                    items.add(note.getTitle());
                }
                break;
            case "Hasla":
                changeCenterSection("Hasla");
                List<Password> passwords = service.getPasswords(user);
                for (Password password : passwords) {
                    items.add(password.getName());
                }
        }
    }

    public void changeCenterSection(String section) {
        switch (section) {
            case "Hasla":
                passwordPanel.setVisible(true);
                passwordPanel.setManaged(true);
                linksPanel.setVisible(false);
                linksPanel.setManaged(false);
                notesPanel.setVisible(false);
                notesPanel.setManaged(false);

                addBtn.setOnAction( e3 -> {
                    new PromptDialog("Hasło","Login","Hasło","Nazwa",user);
                });

                break;
            case "Notatki":
                passwordPanel.setVisible(false);
                passwordPanel.setManaged(false);
                linksPanel.setVisible(false);
                linksPanel.setManaged(false);
                notesPanel.setVisible(true);
                notesPanel.setManaged(true);

                addBtn.setOnAction( e3 -> {
                    new PromptDialog("Notatka","Nazwa","Zawartość","Nazwa",user);
                });

                break;
            case "Linki":
                passwordPanel.setVisible(false);
                passwordPanel.setManaged(false);
                linksPanel.setVisible(true);
                linksPanel.setManaged(true);
                notesPanel.setVisible(false);
                notesPanel.setManaged(false);
                addBtn.setOnAction( e3 -> {
                    new PromptDialog("Link","Nazwa","Odnośnik","Nazwa",user);
                });
                break;
        }
    }

}


