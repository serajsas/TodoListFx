package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.Constants;
import DataBase.DbHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class LoginController extends DbHandler {
    private String username;
    private String password;
    private DbHandler dbHandler;
    private int userid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUPButton;

    @FXML
    private TextField loginWarningField;

    @FXML
    void initialize() {
        dbHandler = new DbHandler();
        //remember to add method to choose one of them
        proceedLogin();
        proceedSignUp();

    }

    //MODIFIES:this
    //EFFECTS: get the required fields
    private void getFields() {
        this.username = loginUsernameField.getText().trim();
        this.password = loginPasswordField.getText().trim();
    }

    private boolean checkEmptyFields() {
        getFields();
        if (username.equals("") || password.equals("")) {
            return true;
        }
        return false;
    }

    private void proceedLogin() {
        loginSignInButton.setOnAction(event -> {
            getFields();
            User user = new User(username, password);
            ResultSet userRow = dbHandler.getUser(user);

            int counter = 0;
            try {
                while (userRow.next()) {
                    counter++;
                    userid = userRow.getInt("userid");
                }
                if (counter == 1) {
                    proceedAddItem();
                } else {
                    loginWarningField.setText("Please make sure username/password are correct");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    private void proceedSignUp(){
        loginSignUPButton.setOnAction(event -> {
            //Take users to signup screen
            loginSignUPButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/SignUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
    //EFFECTS: Switch to the addItem window
    private void proceedAddItem(){
        loginSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/addItem.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            AddItemController addItemController = loader.getController();
            addItemController.setUserId(userid);
            stage.showAndWait();
    }


}

