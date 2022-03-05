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
import java.io.IOException;

public class Scene4Controller
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    /*public void wpmrush(ActionEvent e)throws IOException {
        //System.out.println("WPMRush");

        //String username = "we are iutians which is in gazipur, one of the most polluted cities of bangladesh";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        root = loader.load();

        //Scene5Controller scene5Controller = loader.getController();
        //scene5Controller.displayName();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/
    public void playGame(ActionEvent e) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Gamecontroller gamecontroller = loader.getController();
        gamecontroller.takeGivenLine();
        gamecontroller.setfirstword();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void custom(ActionEvent e) {
        System.out.println("Custom");
    }
    public void programing(ActionEvent e) {
        System.out.println("Programing");
    }
    public void graph(ActionEvent e) {
        System.out.println("Graph");
    }
    public void exit(ActionEvent e) {
        System.exit(0);
        //System.out.println("Exit");
    }
}
