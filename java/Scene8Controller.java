//custom test
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.tonevellah.demofx1.Scene1Controller.clr;
import static com.tonevellah.demofx1.Scene1Controller.lvl;

public class Scene8Controller{
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

    public String givenstring =takeGivenLine();

    public String takeGivenLine(){
        System.out.println("level" + lvl);
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
        int n=35;
        if(givenwords.length<35)n=givenwords.length;
        for(int ii=1;ii<n;ii++){
            st+=givenwords[ii] + " ";
        }
        greenText = new Text(st);
        if(clr==0)greenText.setFill(Color.BLACK);
        else greenText.setFill(Color.WHITE);

        textflow.getChildren().addAll(greyText,blueText, greenText);
        textflow.setStyle("-fx-font: 28 arial;");
        textflow.setPrefWidth(700);

        textflow.setPadding(new Insets(15, 15, 15, 15));

        playAgain.setVisible(false);
        wrong.setVisible(false);
        correct.setVisible(false);
    }

    public void playAgain(ActionEvent e) throws IOException {
        //System.out.println("ttt");
        double tm=60;
        double wpm= Math.ceil(((double)counter/(double)timer)*tm);
        int w=(int)wpm;

        if(clr==0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene6.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene6Controller scene6controller = loader.getController();
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            scene6controller.displayResult(w, acc, countAll, countAll - counter);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene16.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene6Controller scene6controller = loader.getController();
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            scene6controller.displayResult(w, acc, countAll, countAll - counter);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


    private int countAll = 0;
    private int counter = 0;
    private int timer = 0;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println(countAll +" "+ givenwords.length);
            if (timer >= 0) {
                seconds.setText(String.valueOf(timer));
                wrong.setVisible(false);
                correct.setVisible(false);
                timer += 1;



            }
            if (countAll >= givenwords.length) {
                userWord.setDisable(true);
                userWord.setText("Game over");
                playAgain.setVisible(true);
                playAgain.setVisible(true);
                playAgain.setDisable(false);
                executor.shutdown();
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
                double tm=60;
                double wpm= Math.ceil(((double)counter/(double)timer)*tm);
                wordsPerMin.setText(String.valueOf((int)wpm));

                wrong.setVisible(false);
                correct.setVisible(true);

                //wordsPerMin.setText(String.valueOf((int)counter));

            }
            else{
                wrong.setVisible(true);
                correct.setVisible(false);

                double tm=60;
                double wpm= ((double)counter/(double)timer)*tm;
                wordsPerMin.setText(String.valueOf((int)wpm));
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)) +"%");

            if(fir+2<givenwords.length) {
                programWord.setText(givenwords[fir]);
                secondProgramWord.setText(givenwords[fir + 1]);
                previousProgramWord.setText(givenwords[fir - 1]);
            }
            else if(fir+1<givenwords.length) {
                programWord.setText(givenwords[fir]);
                secondProgramWord.setText(givenwords[fir + 1]);
                previousProgramWord.setText("out");
            }
            else if(fir<givenwords.length) {
                programWord.setText(givenwords[fir]);
                secondProgramWord.setText("out");
                previousProgramWord.setText("of");
            }
            else{
                programWord.setText("out");
                secondProgramWord.setText("of");
                previousProgramWord.setText("bounds");
            }

            if(fir>=3)secpreviousProgramWord.setText(givenwords[fir-2]);
            else secpreviousProgramWord.setText("here:- ");


            int lim=0;
            if(fir<35 && givenwords.length>=35)lim=35;
            else if(fir<35 && givenwords.length<35)lim=givenwords.length;
            else if(fir<35)lim=35;
            else if(fir<70)lim=70;
            else if(fir<105)lim=105;
            else if(fir<140)lim=140;
            else if(fir<175)lim=175;
            textflow.getChildren().clear();
            String st="";

            if(lim%35==0) {
                for (int ii = lim - 35; ii < fir; ii++) {
                    st += givenwords[ii] + " ";
                }
            }
            else{
                for (int ii = lim - (lim%35); ii < fir; ii++) {
                    st += givenwords[ii] + " ";
                }
            }
            if(fir<givenwords.length){
                greyText = new Text(st);
                greyText.setFill(Color.GREY);

                blueText = new Text(givenwords[fir]);
                blueText.setFill(Color.BLUE);

                st=" ";
                for(int ii=fir+1;ii<lim;ii++){
                    st+=givenwords[ii] + " ";
                }
                greenText = new Text(st);
                if(clr==0)greenText.setFill(Color.BLACK);
                else greenText.setFill(Color.WHITE);

                textflow.getChildren().addAll(greyText,blueText,greenText);
                textflow.setStyle("-fx-font: 28 arial;");
                textflow.setPrefWidth(700);
            }

        }

    }

}
