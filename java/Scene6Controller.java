package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene6Controller  implements Initializable {
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?,?> lineChart;

    @FXML
    Label wpmLabel;
    @FXML
    Label accuracyLabel;
    @FXML
    Label typedwordsLabel;
    @FXML
    Button button;
    @FXML
    Button exitButton;
    @FXML
    Button statButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    static public int wpm1,wpm2,wpm3,wpm4,wpm5,wpm6,wpm7,wpm8,wpm9,wpm10;
    //
    public void displayResult(int wpmScore,int accuracyScore,int typedWords,int wrongWords){
        wpmLabel.setText(String.valueOf((int)wpmScore));
        accuracyLabel.setText(String.valueOf(accuracyScore) + "%");
        typedwordsLabel.setText(String.valueOf(typedWords) + " Words");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        String username="t";
        try {
            File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/usname.txt");
            Scanner fileinput = new Scanner(file);

            while (fileinput.hasNext()) {
                 String s = fileinput.nextLine();
                 username=s;
            }
            fileinput.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");
            preparedStatement = connection.prepareStatement("SELECT wpm1,wpm2,wpm3 FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("user not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("incorrect");
                alert.show();
            } else {
                while(resultSet.next()){
                    int retrievedPassword = resultSet.getInt("wpm1");
                    int retrievedPassword2 = resultSet.getInt("wpm2");
                    int retrievedPassword3 = resultSet.getInt("wpm3");
                    System.out.println("fu"+ retrievedPassword);
                    wpm1=retrievedPassword;
                    wpm2=retrievedPassword2;
                    wpm3=retrievedPassword3;
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
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1",wpm1));
        series.getData().add(new XYChart.Data("2",wpm2));
        series.getData().add(new XYChart.Data("3",wpm3));
        series.getData().add(new XYChart.Data("4",90));
        series.getData().add(new XYChart.Data("5",45));
        series.getData().add(new XYChart.Data("6",29));
        lineChart.getData().addAll(series);

    }

    public void tryagain(ActionEvent event) throws IOException {
        if(log==1) {
            if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
            else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
        }
        else{
            if (clr == 0) root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            else root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void viewGraph(ActionEvent e) {

        System.out.println("graph");
    }
    public void exit(ActionEvent e)throws IOException {
        log=0;
        if (clr == 0) root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        else root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //System.exit(0);
    }
}
