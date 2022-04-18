package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene3Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField uname;
    @FXML
    private PasswordField pass;

    public String username;
    public String password;

    public void menu(ActionEvent event) throws IOException {
        username=uname.getText();
        password=pass.getText();
        System.out.println(username +" "+ password);

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("user exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this name");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(username,password) VALUES(?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                log=1;
                if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
                else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        /*if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

    }
    public void goback(ActionEvent event) throws IOException {
        if(clr==0)root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        else root = FXMLLoader.load(getClass().getResource("hello-viewb.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}