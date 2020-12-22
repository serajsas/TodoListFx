package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.LoginController;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
         //Parent root = FXMLLoader.load(getClass().getResource("/view/addItem.fxml"));
        primaryStage.setTitle("Todo-Application");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }
}
