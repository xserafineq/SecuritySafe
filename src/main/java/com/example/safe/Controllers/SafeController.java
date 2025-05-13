package com.example.safe.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Note;
import model.User;
import service.NoteService;
import service.SafeService;

import java.util.List;

public class SafeController {

    private User user;

    @FXML
    ListView<String> itemsList;
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private Text categoryText;


    public void start() {
        fillListView();

        itemsList.setOnMouseClicked(event -> {
            System.out.println(new NoteService().getNote(user,itemsList.getSelectionModel().getSelectedItem()).getTitle());
            categoryText.setText(itemsList.getSelectionModel().getSelectedItem());
        });
    }

    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void fillListView() {
        SafeService service = new SafeService();
        List<Note> notes = service.getNotes(user);
        itemsList.setItems(items);
        for (Note note : notes) {
            items.add(note.getTitle());
        }
    }

    public void setData(User user) {
        this.user = user;
    };

}


