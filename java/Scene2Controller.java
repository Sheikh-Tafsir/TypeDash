package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.tonevellah.demofx1.Scene1Controller.clr;


public class Scene2Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void menu(ActionEvent event) throws IOException {
        System.out.println(clr);
        String st="";
        int i=0;
        int ran = (int) Math.floor(Math.random() * (4 - 0 + 1) + 0);
        System.out.println("advc"+ ran);
        try {
            File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/advices.txt");
            Scanner fileinput = new Scanner(file);
            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                if (i == ran) {
                    st += s;
                    break;
                }
                i++;
            }
            fileinput.close();
        }
        catch (Exception exp) {
            System.out.println(exp);
        }

        if(clr==0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene4.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene4Controller scene4controller = loader.getController();
            scene4controller.tips(st);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene14.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene4Controller scene4controller = loader.getController();
            scene4controller.tips(st);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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
