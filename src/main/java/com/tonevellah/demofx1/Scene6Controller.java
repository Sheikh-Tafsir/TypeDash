//view result
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;
import static com.tonevellah.demofx1.Scene5Controller.virkey;

public class Scene6Controller implements Initializable {
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
    //
    public void displayResult(int wpmScore,int accuracyScore,int typedWords,int wrongWords){
        wpmLabel.setText(String.valueOf((int)wpmScore));
        accuracyLabel.setText(String.valueOf(accuracyScore) + "%");
        typedwordsLabel.setText(String.valueOf(typedWords) + " Words");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = "t";
        try {
            File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/usname.txt");
            Scanner fileinput = new Scanner(file);

            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                username = s;
            }
            fileinput.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement psInsert = null;
        ArrayList<Integer> wpmscore = new ArrayList<Integer>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");
            preparedStatement = connection.prepareStatement("SELECT * FROM races WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //System.out.println(resultSet.getString("username"));
                int g = resultSet.getInt("wpm");
                //System.out.println(g);
                wpmscore.add(g);

                //wpmscore.add(resultSet.getInt("wpm"));
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
        series.setName("WPM Graph");
        for(int i=0; i<wpmscore.size();i++){
            int x=wpmscore.get(i);
            //System.out.println(i +" "+ x);
            int ind=i+1;
            series.getData().add(new XYChart.Data(""+ ind,x+1-1));
        }
        lineChart.getData().addAll(series);

    }
    public void tryagain(ActionEvent event) throws IOException {
        virkey=0;
        if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addgrap(MouseEvent event) throws IOException {
        virkey = 0;
        if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene102.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene112.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
