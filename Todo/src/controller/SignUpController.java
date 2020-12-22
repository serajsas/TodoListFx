package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DataBase.DbHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import sun.rmi.runtime.Log;

public class SignUpController {
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpSignUpButton;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpUsername;

    @FXML
    private PasswordField signUpPassword;
    @FXML
    private TextField signUpWarning;


    @FXML
    void initialize() {
        signUpSignUpButton.setOnAction(event -> {
            createUser();
        });

    }

    //EFFECTS:create User whenever the fields of the sign up page are filled
    private void createUser() {
        DbHandler dbHandler = new DbHandler();
        getFields();
        User user = new User(firstname, lastname, username, password);
        if (!checkEmptyFields()) {
            dbHandler.signUpUser(user);

        } else {
            signUpWarning.setText("Warning: Please make sure all the required fields are filled !");
        }
    }

    //EFFECTS: get the required fields
    private void getFields() {
        firstname = signUpFirstName.getText().trim();
        lastname = signUpLastName.getText().trim();
        username = signUpUsername.getText().trim();
        password = signUpPassword.getText().trim();
    }

    //EFFECTS: return true if any of the fields is empty
    private boolean checkEmptyFields() {
        getFields();
        if (firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("")) {
            return true;
        }
        return false;
    }



}
