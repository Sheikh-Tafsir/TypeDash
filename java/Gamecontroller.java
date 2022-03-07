package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tonevellah.demofx1.Scene1Controller.clr;

public class Gamecontroller {
    private int wordCounter = 0;
    private int first = 0;
    int fir = 0;

    //private File saveData;

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

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;

    @FXML
    private Button playAgain;

    public String givenstring =takeGivenLine();

    public String takeGivenLine(){
        int max=50,min=40;
        try {
            File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Levels.txt");
            Scanner fileinput = new Scanner(file);

            while (fileinput.hasNext()) {
                String s = fileinput.nextLine();
                max=Integer.valueOf(s);
                System.out.println("reading"+ s);
            }
            fileinput.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(max==5){
            String st = "";
            try {
                File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/custext.txt");
                Scanner fileinput = new Scanner(file);

                while (fileinput.hasNext()) {
                    String s = fileinput.nextLine();
                    st += s;
                }
                fileinput.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
            return st;
        }
        else {
            min = max - 1;
            max *= 7;
            min *= 7;
            String st = "";
            int i = 0;
            int ran = (int) Math.floor(Math.random() * (max - min + 1) + min);

            System.out.println("random" + ran);
            try {
                File file = new File("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/Lines.txt");
                Scanner fileinput = new Scanner(file);

                while (fileinput.hasNext()) {
                    String s = fileinput.nextLine();
                    if (i >= ran) {
                        st += s;
                    }
                    i++;
                }
                fileinput.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return st;
        }
    }

    public void customtext(String ctext) {
        givenstring=ctext;
    }

    String[] givenwords = givenstring.split(" ");
    //String[] givenwords = givenstring.split("\\W");

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

    public void playAgain(ActionEvent e) throws IOException {
        //System.out.println("ttt");
        if(clr==0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
            root = loader.load();
            //root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene6Controller scene6controller = loader.getController();
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            scene6controller.displayResult(counter, acc, countAll, countAll - counter);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene16.fxml"));
            root = loader.load();
            //root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene6Controller scene6controller = loader.getController();
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            scene6controller.displayResult(counter, acc, countAll, countAll - counter);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


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

                    /*try {
                        FileWriter myWriter = new FileWriter(saveData);
                        myWriter.write(countAll +";");
                        myWriter.write(counter +";");
                        myWriter.write(String.valueOf(countAll-counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
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
