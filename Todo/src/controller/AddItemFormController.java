package controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import DataBase.DbHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Task;

public class AddItemFormController {
    private DbHandler dbHandler;
    private int userid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AddItemFormControllerTaskField;

    @FXML
    private TextField AddItemFormControllerDescriptionField;

    @FXML
    private Button AddItemFormControllerSaveTask;

    @FXML
    void initialize() {
        dbHandler = new DbHandler();
        AddItemFormControllerSaveTask.setOnAction(event -> {
            Task task = new Task();
            String taskText = AddItemFormControllerTaskField.getText().trim();
            String taskDescription = AddItemFormControllerDescriptionField.getText().trim();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            if (!taskText.equals("")||!taskDescription.equals("")){
                task.setUserId(task.getUserId());
                task.setDateCreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);
                dbHandler.insertTask(task);
                System.out.println("Task Added Successfully!");
            }else {
                System.out.println("Its empty");
            }
        });

    }

}
