package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;

public class AddItemController {
    public int userid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addItemTextField;

    @FXML
    private Button addItemAddButton;

    @FXML
    void initialize() {
        addButtonPressed();

    }

    private void addButtonPressed() {
        proceedAddItemForm();
    }


    private void proceedAddItemForm() {
        addItemAddButton.setOnAction(event -> {
            //Take users to additemform screen
            addItemAddButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/addItemForm.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

}
