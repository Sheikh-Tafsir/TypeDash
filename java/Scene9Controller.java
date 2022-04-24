//guest wpm test
package com.tonevellah.demofx1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tonevellah.demofx1.Scene1Controller.*;
import static com.tonevellah.demofx1.Scene1Controller.car;

public class Scene9Controller {
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

    @FXML
    private TextFlow textflow;
    @FXML
    private Text greyText;
    @FXML
    private Text blueText;
    @FXML
    private Text greenText;
    @FXML
    private Text lastText;

    @FXML
    private ImageView imgview;
    private double x1;
    private double y1;

    @FXML
    private Label won;


    public String givenstring =takeGivenLine();

    public String takeGivenLine(){
        int max=50,min=40;
        max=lvl;
        System.out.println("level" + lvl);
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
        //}
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

        greyText=new Text("");
        greyText.setFill(Color.GREY);
        blueText = new Text(givenwords[0]);
        blueText.setFill(Color.BLUE);

        String st=" ";
        for(int ii=1;ii<35;ii++){
            st+=givenwords[ii] + " ";
        }
        greenText = new Text(st);
        if(clr==0)greenText.setFill(Color.BLACK);
        else greenText.setFill(Color.WHITE);

        textflow.getChildren().addAll(greyText,blueText, greenText);
        textflow.setStyle("-fx-font: 28 arial;");
        textflow.setPrefWidth(700);
        //textflow.setMaxHeight(Control.USE_PREF_SIZE);

        textflow.setPadding(new Insets(15, 15, 15, 15));

        playAgain.setVisible(false);
        wrong.setVisible(false);
        correct.setVisible(false);

        System.out.println("car "+ car);
        if(car==1){
            imgview.setImage(new Image("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/car_yellow.png"));
        }
        else if(car==2){
            imgview.setImage(new Image ("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/car_red.png"));
        }
        else if(car==3){
            imgview.setImage(new Image ("D:/java code/demofx1/src/main/resources/com/tonevellah/demofx1/car_pink.png"));
        }
        won.setVisible(false);

    }

    public void playAgain(MouseEvent e) throws IOException {
        //System.out.println("ttt");
        if(clr==0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene10.fxml"));
            root = loader.load();
            //root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene10Controller scene6controller = loader.getController();
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            scene6controller.displayResult(counter, acc, countAll, countAll - counter);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene20.fxml"));
            root = loader.load();
            //root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene10Controller scene6controller = loader.getController();
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
    private int speed = 5;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
                wrong.setVisible(false);
                correct.setVisible(false);
                imgview.setY(y1-=speed);
                if(y1<=-470)won.setVisible(true);


                double tm=60;
                double wpm= Math.ceil((counter/(tm-timer))*tm);
                wordsPerMin.setText(String.valueOf((int)wpm));
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
            int colf=5;
            System.out.println(first);
            String s = userWord.getText();
            if(fir>=1)s=s.substring(1,s.length());
            fir++;
            String real = programWord.getText();

            countAll++;
            if (s.equals(real)) {
                counter++;
                double tm=60;
                double wpm= (counter/(tm-timer))*tm;
                wordsPerMin.setText(String.valueOf((int)wpm));

                wrong.setVisible(false);
                correct.setVisible(false);

                speed=(int)wpm/5;
                colf=1;
            }
            else{
                double tm=60;
                double wpm= (counter/(tm-timer))*tm;
                wordsPerMin.setText(String.valueOf((int)wpm));

                wrong.setVisible(false);
                correct.setVisible(false);

                speed=(int)wpm/5;
                colf=0;

            }


            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)) +"%");

            programWord.setText(givenwords[fir]);
            secondProgramWord.setText(givenwords[fir+1]);
            previousProgramWord.setText(givenwords[fir-1]);

            if(fir>=3)secpreviousProgramWord.setText(givenwords[fir-2]);
            else secpreviousProgramWord.setText("here:- ");


            int lim=0;
            if(fir<35)lim=35;
            else if(fir<35)lim=35;
            else if(fir<70)lim=70;
            else if(fir<105)lim=105;
            else if(fir<140)lim=140;
            else if(fir<175)lim=175;
            textflow.getChildren().clear();
            String st="";
            for(int ii=lim-35;ii<fir-1;ii++){
                st+=givenwords[ii] + " ";
            }
            greyText = new Text(st);
            greyText.setFill(Color.GREY);

            lastText = new Text(givenwords[fir-1] +" ");
            if(colf==0)lastText.setFill(Color.LIGHTPINK);
            else lastText.setFill(Color.LIGHTGREEN);

            blueText = new Text(givenwords[fir]);
            blueText.setFill(Color.BLUE);

            st=" ";
            for(int ii=fir+1;ii<lim;ii++){
                st+=givenwords[ii] + " ";
            }
            greenText = new Text(st);
            if(clr==0)greenText.setFill(Color.BLACK);
            else greenText.setFill(Color.WHITE);

            textflow.getChildren().addAll(greyText,lastText,blueText,greenText);
            textflow.setStyle("-fx-font: 28 arial;");
            textflow.setPrefWidth(700);
        }

    }
}
