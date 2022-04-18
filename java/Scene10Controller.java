//guest result view
package com.tonevellah.demofx1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.log;

public class Scene10Controller {
    @FXML
    Label wpmLabel;
    @FXML
    Label accuracyLabel;
    @FXML
    Label typedwordsLabel;
    @FXML
    Label wrongtwordsLabel;
    @FXML
    Button button;
    @FXML
    Button exitButton;
    @FXML
    Button statButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    //
    public void displayResult(int wpmScore,int accuracyScore,int typedWords,int wrongWords){
        wpmLabel.setText(String.valueOf((int)wpmScore));
        accuracyLabel.setText(String.valueOf(accuracyScore) + "%");
        typedwordsLabel.setText(String.valueOf(typedWords) + " Words");
        wrongtwordsLabel.setText(String.valueOf(wrongWords) + " Words");
    }
    public void tryagain(ActionEvent event) throws IOException {
        if(clr==0)root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        else root = FXMLLoader.load(getClass().getResource("hello-viewb.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
