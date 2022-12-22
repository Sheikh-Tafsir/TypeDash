//menu
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.tonevellah.demofx1.Scene1Controller.*;

public class Scene4Controller
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Text advc;
    @FXML
    Text uname;

    public void tips(String str){

        advc.setText(str);

        /*String username="t";
        try {
            File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/usname.txt");
            Scanner fileinput = new Scanner(file);

            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                username=s;
            }
            fileinput.close();
        }
        catch(Exception fe){
            System.out.println(fe);
        }
        uname.setText("Hi "+ username);*/
    }

    public void playGame(ActionEvent e) throws IOException {

        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene15.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void custom(ActionEvent e)throws IOException {

        lvl=5;
       // System.out.println("Custom");
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene7.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene17.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void programing(ActionEvent e)throws IOException {

        lvl=4;

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
    public void exit(ActionEvent e)throws IOException {
        //System.exit(0);
        log=0;
        if (clr == 0) root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        else root = FXMLLoader.load(getClass().getResource("hello-viewb.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
