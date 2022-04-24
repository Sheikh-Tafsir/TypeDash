//login
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene2Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    static public int[] wpms= new int[10];

    @FXML
    private TextField uname;
    @FXML
    private PasswordField pass;
    @FXML
    private Label warnin;

    public String username;
    public String password;

    public void menu(ActionEvent event) throws IOException {
        username=uname.getText();
        password=pass.getText();

        System.out.println(username +" "+ password);

        try {
            FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/usname.txt");
            fileWriter.write(username);
            fileWriter.close();
        }
        catch(IOException exc){
            exc.printStackTrace();
        }


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                warnin.setText("Username not found!");
                warnin.setVisible(true);
                System.out.println("User not found");
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Usern not found!");
                alert.show();*/
                uname.setText("");
                pass.setText("");
            } else {
                while(resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if(retrievedPassword.equals(password)){


                        log=1;
                        if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
                        else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else{
                        warnin.setText("Password dont match!");
                        warnin.setVisible(true);
                        System.out.println("password did not match");
                        /*Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password did not match!");
                        alert.show();*/
                        pass.setText("");
                    }
                }
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
