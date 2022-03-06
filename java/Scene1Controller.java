package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {
    //@FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    static public int clr=0;

    public void changetheme(ActionEvent event) throws IOException {
        if(clr==0){
            clr=1;
            root = FXMLLoader.load(getClass().getResource("hello-viewb.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            clr=0;
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void loginpage(ActionEvent event) throws IOException {
        System.out.println(clr);
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene12.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signuppage(ActionEvent event) throws IOException {

        //System.out.println("signup");
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene13.fxml"));
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
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Gamecontroller gamecontroller = loader.getController();
        gamecontroller.takeGivenLine();
        gamecontroller.setfirstword();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene15.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
