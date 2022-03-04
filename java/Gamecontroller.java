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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gamecontroller {
    private int wordCounter = 0;
    private int first = 0;
    int fir = 0;

    private File saveData;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Text seconds;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text accuracy;
    @FXML
    private Text programWord;
    @FXML
    private Text secondProgramWord;
    @FXML
    private Text thirddProgramWord;
    @FXML
    private Text secpreviousProgramWord;
    @FXML
    private Text previousProgramWord;


    /*@FXML
    private Label programWord;
    @FXML
    private Label secondProgramWord;*/

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;

    @FXML
    private Button playAgain;

    ArrayList<String> words = new ArrayList<>();
    String givenstring ="Bangladesh is a small and beautiful country in South Asia. We got independence in 1971 from Pakistan after a long war. Because of the sacrifice of millions of freedom fighters we got this country. 16 December is our Victory Day because this day the Pakistani army surrendered.\n" +
            "\n" +
            "And 26th March is our Independence Day because in 1971’s 26th March Ziaur Rahman declared our independence from Chittagong. We are the only nation that sacrifices lives for language and now it is known as International Mother Language Day on 21st February. More than 180 countries celebrate this day. Bangladesh is a very beautiful country to see. ";
    String[] givenwords = givenstring.split("\\W");
    //String[] givenwords = givenstring.split("\\.")[0];


    public void playAgain(ActionEvent e) throws IOException {
        //System.out.println("ttt");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
        root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene6Controller scene6controller= loader.getController();
        int acc=(int) Math.round((counter*1.0/countAll)*100);
        scene6controller.displayResult(counter,acc,countAll,countAll-counter);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*public void initialize(URL url, ResourceBundle resourceBundle) {
        programWord.setText(givenwords[0]);
        secondProgramWord.setText(givenwords[1]);
    }*/
    public void setfirstword() {
        secpreviousProgramWord.setText("start");
        previousProgramWord.setText("here:- ");
        programWord.setText(givenwords[0]);
        secondProgramWord.setText(givenwords[1]);
        thirddProgramWord.setText(givenwords[2]);

        playAgain.setVisible(false);
        wrong.setVisible(false);
        correct.setVisible(false);
    }

    /*public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new
                    FileReader("wordsList"));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private int countAll = 0;
    private int counter = 0;
    private int timer = 60;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
                wrong.setVisible(false);
                correct.setVisible(false);
            }

            else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");
                    playAgain.setVisible(true);

                    try {
                        FileWriter myWriter = new FileWriter(saveData);
                        myWriter.write(countAll +";");
                        myWriter.write(counter +";");
                        myWriter.write(String.valueOf(countAll-counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer == -4) {
                    playAgain.setVisible(true);
                    playAgain.setDisable(false);
                    executor.shutdown();
                }

                timer -= 1;
            }
        }
    };

    /*Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
            correct.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(0);

        }
    };

    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };*/


    public void startGame(KeyEvent ke) throws IOException{

        // only gets called once
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

        }

        if (ke.getCode().equals(KeyCode.SPACE)) {
            System.out.println(first);
            String s = userWord.getText();
            if(fir>=1)s=s.substring(1,s.length());
            fir++;
            String real = programWord.getText();

            countAll++;
            /*if(timer==0){
                root = FXMLLoader.load(getClass().getResource("scene5.fxml"));
                stage = (Stage)((Node)ke.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }*/
            if (s.equals(real)) {
                counter++;
                wordsPerMin.setText(String.valueOf(counter));

                wrong.setVisible(false);
                correct.setVisible(true);
            }
            else{
                wrong.setVisible(true);
                correct.setVisible(false);
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)) +"%");

            programWord.setText(givenwords[fir]);
            secondProgramWord.setText(givenwords[fir+1]);
            previousProgramWord.setText(givenwords[fir-1]);

            if(fir>=3)secpreviousProgramWord.setText(givenwords[fir-2]);
            else secpreviousProgramWord.setText("here:- ");
           // userWord.setPromptText(givenwords[fir]);
            //System.out.println(s);
            //System.out.println(s.length());
        }

    }

}
