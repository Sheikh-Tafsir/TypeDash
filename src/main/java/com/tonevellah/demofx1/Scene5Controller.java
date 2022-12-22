//choose test
package com.tonevellah.demofx1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.lvl;
import static com.tonevellah.demofx1.Scene1Controller.car;
import static com.tonevellah.demofx1.Scene1Controller.log;


public class Scene5Controller implements Initializable {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private CheckBox myCheckBox;
    @FXML
    public RadioButton rButton1;
    @FXML
    public RadioButton rButton2;
    @FXML
    public RadioButton rButton3;
    @FXML
    private ChoiceBox<String> myChoiceBox=new ChoiceBox<String>();
    private String[] cars = {"Yellow","Red","Pink"};
    int foodf=0;
    static public int virkey=0;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        myChoiceBox.getItems().addAll(cars);
        myChoiceBox.setOnAction(this::getFood);
        myChoiceBox.setStyle("-fx-font: 20 arial;");
    }
    public void getFood(ActionEvent event) {

        String mytime = myChoiceBox.getValue();
        if(mytime=="Yellow") car=1;
        else if(mytime=="Red") car=2;
        else if(mytime=="Pink") car=3;
    }

    public void getLevel(ActionEvent event) {

        if(rButton1.isSelected()) {

            lvl=1;
        }
        else if(rButton2.isSelected()) {

            lvl=2;
        }
        else if(rButton3.isSelected()) {

            lvl=3;
        }

    }

    public void getkeyboard(ActionEvent event) {

        if(myCheckBox.isSelected()) {
            virkey=1;
        }
        else{
            virkey=0;
        }

    }


    public void gotogame(ActionEvent e) throws IOException{

        if(log==1) {
            if (clr == 0) {
                if(virkey==0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Gamecontroller gamecontroller = loader.getController();
                    gamecontroller.takeGivenLine();
                    gamecontroller.setfirstword();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene101.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene101Controller gamecontroller = loader.getController();
                    gamecontroller.takeGivenLine();
                    gamecontroller.setfirstword();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else {
                if(virkey==0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gameb.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Gamecontroller gamecontroller = loader.getController();
                    gamecontroller.takeGivenLine();
                    gamecontroller.setfirstword();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene111.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene101Controller gamecontroller = loader.getController();
                    gamecontroller.takeGivenLine();
                    gamecontroller.setfirstword();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
        else{
            if (clr == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene9.fxml"));
                root = loader.load();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene9Controller gamecontroller = loader.getController();
                //Scene25Controller gamecontroller = loader.getController();
                gamecontroller.takeGivenLine();
                gamecontroller.setfirstword();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene19.fxml"));
                root = loader.load();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene9Controller gamecontroller = loader.getController();
                gamecontroller.takeGivenLine();
                gamecontroller.setfirstword();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public void goback(ActionEvent event) throws IOException {
        if(log==1){
            if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
            else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
        }
        else {
            if(clr==0)root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            else root = FXMLLoader.load(getClass().getResource("hello-viewb.fxml"));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
