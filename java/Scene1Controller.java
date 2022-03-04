package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {
    //@FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signuppage(ActionEvent event) throws IOException {

        //System.out.println("signup");
        root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(ActionEvent e) {

        //System.out.println("Exit");
        System.exit(0);
    }
    public void wpmrush(ActionEvent e) throws IOException{
        // System.out.println("wprush");
        //String username = "tafsir rahman is a student";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Gamecontroller gamecontroller = loader.getController();
        gamecontroller.setfirstword();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
