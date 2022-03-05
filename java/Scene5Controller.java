package com.tonevellah.demofx1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Scene5Controller {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public RadioButton rButton1;
    @FXML
    public RadioButton rButton2;
    @FXML
    public RadioButton rButton3;


    public void getLevel(ActionEvent event) {

        if(rButton1.isSelected()) {

            //myLabel.setText(rButton1.getText());
            try {
                FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
                fileWriter.write("1");
                fileWriter.close();
                System.out.println("1");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(rButton2.isSelected()) {

            //myLabel.setText(rButton2.getText());
            try {
                FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
                fileWriter.write("2");
                fileWriter.close();
                System.out.println("2");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(rButton3.isSelected()) {

            //myLabel.setText(rButton3.getText());
            try {
                FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
                fileWriter.write("3");
                fileWriter.close();
                System.out.println("3");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    }


    public void setLevel(ActionEvent e) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Gamecontroller gamecontroller = loader.getController();
        gamecontroller.takeGivenLine();
        gamecontroller.setfirstword();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goback(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
