//virtual keyboard
package com.tonevellah.demofx1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tonevellah.demofx1.Scene1Controller.*;
import static com.tonevellah.demofx1.Scene1Controller.car;

public class Scene101Controller {
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

    @FXML
    private TextField loggerPane;
    static public int cas=0;
    String st="";

    private long pretime = 0;
    Instant start,end;


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

        start = Instant.now();

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

    public void resultview(MouseEvent e) throws IOException {

        String username="t";
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

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");

            psInsert = connection.prepareStatement("INSERT INTO races(username,wpm) VALUES(?,?)");
            psInsert.setString(1, username);
            psInsert.setInt(2, counter);
            psInsert.executeUpdate();


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }


        //extra
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/typerush", "root", "Rubaiyat26");

            psInsert = connection.prepareStatement("INSERT INTO extra(username,wpm,accuracy,totword,totchar,pretime) VALUES(?,?,?,?,?,?)");
            psInsert.setString(1, username);
            psInsert.setInt(2, counter);
            int acc = (int) Math.round((counter * 1.0 / countAll) * 100);
            psInsert.setInt(3, acc);
            psInsert.setInt(4, countAll);
            psInsert.setInt(5, countChar);
            psInsert.setInt(6, (int)pretime);
            psInsert.executeUpdate();


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }

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
    private int speed = 0;
    private int countChar=0;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            userWord.setVisible(false);
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
                wrong.setVisible(false);
                correct.setVisible(false);
                imgview.setY(y1-=speed);
                //speed=0;
                if(y1<=-520)won.setVisible(true);


                double tm=60;
                double wpm= Math.ceil((counter/(tm-timer))*tm);
                wordsPerMin.setText(String.valueOf((int)wpm));
            }

            else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");
                    playAgain.setVisible(true);

                    loggerPane.setDisable(true);
                    loggerPane.setText("Game over");

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


    }


    public void but1(MouseEvent event) throws IOException
    {

        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();
            System.out.println("Time taken: "+ pretime +" milliseconds");
        }
        st+="1";
        loggerPane.setText(st);

    }
    public void but2(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="2";
        loggerPane.setText(st);

    }
    public void but3(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="3";
        loggerPane.setText(st);

    }
    public void but4(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="4";
        loggerPane.setText(st);

    }
    public void but5(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="5";
        loggerPane.setText(st);

    }
    public void but6(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="6";
        loggerPane.setText(st);

    }
    public void but7(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="7";
        loggerPane.setText(st);

    }
    public void but8(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="8";
        loggerPane.setText(st);

    }
    public void but9(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="9";
        loggerPane.setText(st);

    }
    public void but0(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        st+="0";
        loggerPane.setText(st);

    }
    public void buta(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="a";
        else if(cas==1){
            st+="A";
            cas=0;
        }
        else st+="A";
        loggerPane.setText(st);

    }
    public void butb(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="b";
        else if(cas==1){
            st+="B";
            cas=0;
        }
        else st+="B";
        loggerPane.setText(st);

    }
    public void butc(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="c";
        else if(cas==1){
            st+="C";
            cas=0;
        }
        else st+="C";
        loggerPane.setText(st);

    }
    public void butd(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="d";
        else if(cas==1){
            st+="D";
            cas=0;
        }
        else st+="D";
        loggerPane.setText(st);

    }
    public void bute(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="e";
        else if(cas==1){
            st+="E";
            cas=0;
        }
        else st+="E";
        loggerPane.setText(st);

    }
    public void butf(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="f";
        else if(cas==1){
            st+="F";
            cas=0;
        }
        else st+="F";
        loggerPane.setText(st);

    }
    public void butg(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="g";
        else if(cas==1){
            st+="G";
            cas=0;
        }
        else st+="G";
        loggerPane.setText(st);

    }
    public void buth(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="h";
        else if(cas==1){
            st+="H";
            cas=0;
        }
        else st+="H";
        loggerPane.setText(st);

    }
    public void buti(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();
        }
        if(cas==0)st+="i";
        else if(cas==1){
            st+="I";
            cas=0;
        }
        else st+="I";
        loggerPane.setText(st);

    }
    public void butj(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="j";
        else if(cas==1){
            st+="J";
            cas=0;
        }
        else st+="J";
        loggerPane.setText(st);

    }
    public void butk(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="k";
        else if(cas==1){
            st+="K";
            cas=0;
        }
        else st+="K";
        loggerPane.setText(st);

    }
    public void butl(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="l";
        else if(cas==1){
            st+="L";
            cas=0;
        }
        else st+="L";
        loggerPane.setText(st);

    }
    public void butm(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="m";
        else if(cas==1){
            st+="M";
            cas=0;
        }
        else st+="M";
        loggerPane.setText(st);

    }
    public void butn(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="n";
        else if(cas==1){
            st+="N";
            cas=0;
        }
        else st+="N";
        loggerPane.setText(st);

    }
    public void buto(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="o";
        else if(cas==1){
            st+="O";
            cas=0;
        }
        else st+="O";
        loggerPane.setText(st);

    }
    public void butp(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="p";
        else if(cas==1){
            st+="P";
            cas=0;
        }
        else st+="P";
        loggerPane.setText(st);

    }
    public void butq(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="q";
        else if(cas==1){
            st+="Q";
            cas=0;
        }
        else st+="Q";
        loggerPane.setText(st);

    }
    public void butr(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="r";
        else if(cas==1){
            st+="R";
            cas=0;
        }
        else st+="R";
        loggerPane.setText(st);

    }
    public void buts(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="s";
        else if(cas==1){
            st+="S";
            cas=0;
        }
        else st+="S";
        loggerPane.setText(st);

    }
    public void butt(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="t";
        else if(cas==1){
            st+="T";
            cas=0;
        }
        else st+="T";
        loggerPane.setText(st);

    }
    public void butu(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="u";
        else if(cas==1){
            st+="U";
            cas=0;
        }
        else st+="U";
        loggerPane.setText(st);

    }
    public void butv(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="v";
        else if(cas==1){
            st+="V";
            cas=0;
        }
        else st+="V";
        loggerPane.setText(st);

    }
    public void butw(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="w";
        else if(cas==1){
            st+="W";
            cas=0;
        }
        else st+="W";
        loggerPane.setText(st);

    }
    public void butx(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="x";
        else if(cas==1){
            st+="X";
            cas=0;
        }
        else st+="X";
        loggerPane.setText(st);

    }
    public void buty(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="y";
        else if(cas==1){
            st+="Y";
            cas=0;
        }
        else st+="Y";
        loggerPane.setText(st);

    }
    public void butz(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="z";
        else if(cas==1){
            st+="Z";
            cas=0;
        }
        else st+="Z";
        loggerPane.setText(st);

    }

    public void butdot(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+=".";
        else if(cas==1){
            st+=">";
            cas=0;
        }
        else st+=">";
        loggerPane.setText(st);

    }

    public void butcom(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+=",";
        else if(cas==1){
            st+="<";
            cas=0;
        }
        else st+="<";
        loggerPane.setText(st);

    }
    public void butrslash(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="/";
        else if(cas==1){
            st+="?";
            cas=0;
        }
        else st+="?";
        loggerPane.setText(st);

    }
    public void butsem(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+=";";
        else if(cas==1){
            st+=":";
            cas=0;
        }
        else st+=":";
        loggerPane.setText(st);

    }
    public void butinvert(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="'";
        else st+="";
        loggerPane.setText(st);

    }

    public void butlbrack(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)st+="[";
        else if(cas==1){
            st+="{";
            cas=0;
        }
        else st+="{";
        loggerPane.setText(st);

    }

    public void butrbrack(MouseEvent event) throws IOException {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();
        }
        if(cas==0)st+="]";
        else if(cas==1){
            st+="}";
            cas=0;
        }
        else st+="}";
        loggerPane.setText(st);
    }

    public void butlslash(MouseEvent event) throws IOException {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();
        }
        if(cas==0)st+="";
        else if(cas==1){
            st+="|";
            cas=0;
        }
        else st+="|";
        loggerPane.setText(st);
    }



    public void butbspace(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(st.length()>=1){
            String result = st.substring(0, st.length()-1);
            st=result;
            loggerPane.setText(st);
        }

    }

    public void butshift(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)cas=1;
        else cas=0;

    }
    public void butcaps(MouseEvent event) throws IOException
    {
        if(first==0){
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }
        if(cas==0)cas=2;
        else if(cas==2)cas=0;

    }

    public void butspace(MouseEvent event) throws IOException {
        /*String s = loggerPane.getText();
        loggerPane.setText("");
        System.out.println(s);*/

        if (first == 0) {
            first = 1;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);

            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            pretime=timeElapsed.toMillis();

        }

        int colf = 5;

        if(timer>=0){
            System.out.println(first);
            String s = loggerPane.getText();
            countChar+=s.length();
            System.out.println(countChar);
            //if(fir>=1)s=s.substring(1,s.length());
            fir++;
            String real = programWord.getText();
            st = "";

            countAll++;
            if (s.equals(real)) {
                counter++;
                double tm = 60;
                double wpm = (counter / (tm - timer)) * tm;
                wordsPerMin.setText(String.valueOf((int) wpm));

                wrong.setVisible(false);
                correct.setVisible(true);

                speed = (int) wpm;
                /*if(lvl==1)speed=60;
                else if(lvl==2)speed=65;
                else if(lvl==3)speed=70;
                else if(lvl==4)speed=80;*/
                colf = 1;
            }
            else {
                double tm = 60;
                double wpm = (counter / (tm - timer)) * tm;
                wordsPerMin.setText(String.valueOf((int) wpm));

                wrong.setVisible(true);
                correct.setVisible(false);

                //speed = (int) wpm / 5;
                speed=0;
                colf = 0;

            }
        }


        loggerPane.setText("");
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
        blueText.setUnderline(true);

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

