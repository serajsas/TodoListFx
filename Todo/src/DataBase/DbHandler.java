package DataBase;

import controller.AddItemController;
import controller.LoginController;
import model.Task;
import model.User;
import controller.LoginController;
import java.sql.*;

public class DbHandler extends Config {
    Connection dbConnection;


    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }

    //Write
    public void signUpUser(User user) {
        String insert = "INSERT " + Constants.USERS_TABLE + "(" + Constants.USERS_FIRSTNAME + ","
                + Constants.USERS_LASTNAME + "," + Constants.USERS_USERNAME + "," + Constants.USERS_PASSWORD + ")"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        if (!user.getUsername().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + "todo." + Constants.USERS_TABLE + " WHERE " + Constants.USERS_USERNAME +
                    "=?" + " AND " + Constants.USERS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please make sure you fill all the required fields");
        }
        return resultSet;
    }

    public void insertTask(Task task) {
        String insert = "INSERT INTO " + Constants.TASKS_TABLE + "("+ Constants.USERS_ID +","+ Constants.TASKS_DATE + ","
                + Constants.TASKS_DESCRIPTION + "," + Constants.TASKS_TASK + ")" + "VALUES(?,?,?,?)";
        AddItemController addItemController = new AddItemController();


        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, addItemController.userid);
            preparedStatement.setTimestamp(2, task.getDateCreated());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getTask());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    //Read
}
