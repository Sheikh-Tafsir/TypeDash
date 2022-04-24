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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;
import static com.tonevellah.demofx1.Scene5Controller.virkey;

public class Scene102Controller implements Initializable {
    @FXML
    private CategoryAxis x1;
    @FXML
    private NumberAxis y1;
    @FXML
    private LineChart<?,?> lineChart1;

    @FXML
    private CategoryAxis x2;
    @FXML
    private NumberAxis y2;
    @FXML
    private LineChart<?,?> lineChart2;

    private Stage stage;
    private Scene scene;
    private Parent root;


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
        ArrayList<Integer> totalch = new ArrayList<Integer>();
        ArrayList<Integer> ptime = new ArrayList<Integer>();


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");
            preparedStatement = connection.prepareStatement("SELECT * FROM extra WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("username"));
                int g = resultSet.getInt("totchar");
                //System.out.println(g);
                totalch.add(g);

                int h = resultSet.getInt("pretime");
                //System.out.println(h);
                ptime.add(h);

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
        series.setName("Total characters typed");
        for(int i=0; i<totalch.size();i++){
            int x=totalch.get(i);
            //System.out.println("total char"+ i +" "+ x);
            int ind=i+1;
            series.getData().add(new XYChart.Data(""+ ind,x+1-1));
        }
        lineChart1.getData().addAll(series);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Preparation time");
        for(int i=0; i<ptime.size();i++){
            int x=ptime.get(i);
            //System.out.println("prep time"+ i +" "+ x);
            int ind=i+1;
            series2.getData().add(new XYChart.Data(""+ ind,x+1-1));
        }
        lineChart2.getData().addAll(series2);



    }
    public void tryagain(MouseEvent event) throws IOException {
        virkey=0;
        if (clr == 0) root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
