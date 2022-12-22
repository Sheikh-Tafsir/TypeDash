//input cusom text
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

import static com.tonevellah.demofx1.Scene1Controller.clr;

public class Scene7Controller {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextArea customtext;


    public void gotogame(ActionEvent e) throws IOException{

        //customtext.setWrapText(true);
        String ctext = customtext.getText();
        customtext.setWrapText(true);

        try {
            FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
            fileWriter.write("5");
            fileWriter.close();
            System.out.println("5");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/custext.txt");
            fileWriter.write(ctext);
            fileWriter.close();
            System.out.println("custext");
        }
        catch(IOException exc){
            exc.printStackTrace();
        }

        if(clr==0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene8.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene8Controller gamecontroller = loader.getController();
            gamecontroller.takeGivenLine();
            gamecontroller.setfirstword();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene18.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene8Controller gamecontroller = loader.getController();
            gamecontroller.takeGivenLine();
            gamecontroller.setfirstword();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }
    public void goback(ActionEvent event) throws IOException {
        if(clr==0)root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        else root = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
