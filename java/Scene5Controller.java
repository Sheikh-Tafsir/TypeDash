package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Scene5Controller {
    @FXML
    private Label nameLabel;
    @FXML
    TextField nameTextField;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    String givenstring="hello";
    String typedstring="tafsir rahman is a student";
    String username = "we are iutians which is in gazipur, one of the most polluted cities of bangladesh";
    String[] words = givenstring.split("\\W");
    int i=0;
    double corCount=0,incorCount=0;
    public void displayName() {
        givenstring = username;
        //ArrayList<String> words = new ArrayList<String>(Arrays.asList(givenstring.split(" ")));
        nameLabel.setWrapText(true);
        nameLabel.setText(givenstring);
    }

    /*Date startDate=new Date();

    public void displayWPM(ActionEvent e) throws IOException {
        String[] words = givenstring.split("\\W");
        typedstring = nameTextField.getText();
        Date endDate = new Date();
        double numSeconds = ((endDate.getTime() - startDate.getTime()) / 1000);


        String[] twords = typedstring.split("\\W");

        //Matching statement and typed words
        double corCount=0,incorCount=0;
        for (int i = 0; i < twords.length; i++){
            if(words[i].compareTo(twords[i]) ==0 )corCount++;
            else incorCount++;
        }

        //calculate WPM
        double accuracyscore=(corCount/(corCount+incorCount))*100;
        double wpmscore=(corCount/numSeconds)*60;
        if(numSeconds<60 && twords.length<words.length){
            wpmscore=corCount;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
        root = loader.load();

        Scene6Controller scene6Controller = loader.getController();
        scene6Controller.displayResult(wpmscore,accuracyscore);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    public void displayWPM(KeyEvent ke) throws IOException {
        Date startDate=new Date();
        if (ke.getCode().equals(KeyCode.SPACE)){

            String s = words[i];
            i++;
            String real = nameTextField.getText();
            Date endDate = new Date();
            double numSeconds = ((endDate.getTime() - startDate.getTime()) / 1000);

            // if correct
            if (s.equals(real)) {
                corCount++;
                //wordsPerMin.setText(String.valueOf(counter));

                //Thread t = new Thread(fadeCorrect);
                //t.start();

            }
            else {
                incorCount++;
                //Thread t = new Thread(fadeWrong);
                //t.start();
            }
            /*userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)));
            programWord.setText(words.get(wordCounter));
            secondProgramWord.setText(words.get(wordCounter+1));
            wordCounter++;*/

            //calculate WPM
            double accuracyscore=(corCount/(corCount+incorCount))*100;
            double wpmscore=(corCount/numSeconds)*60;
            if(numSeconds<60 && i<words.length){
                wpmscore=corCount;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
            root = loader.load();

            Scene6Controller scene6Controller = loader.getController();
            //scene6Controller.displayResult(wpmscore,accuracyscore);
            stage = (Stage)((Node)ke.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
