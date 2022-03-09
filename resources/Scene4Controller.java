package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.tonevellah.demofx1.Scene1Controller.clr;

public class Scene4Controller
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void playGame(ActionEvent e) throws IOException {
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene15.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void custom(ActionEvent e)throws IOException {

       // System.out.println("Custom");
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene7.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene17.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void programing(ActionEvent e)throws IOException {

        //System.out.println("Programing");
        try {
            FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
            fileWriter.write("4");
            fileWriter.close();
            System.out.println("4");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

        if(clr==0){
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
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameb.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Gamecontroller gamecontroller = loader.getController();
            gamecontroller.takeGivenLine();
            gamecontroller.setfirstword();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    public void graph(ActionEvent e) {
        System.out.println("Graph");
    }
    public void exit(ActionEvent e) {
        System.exit(0);
        //System.out.println("Exit");
    }
}
