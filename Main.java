/*
    Panes
    http://tutorials.jenkov.com/javafx/gridpane.html
    https://stackoverflow.com/questions/33339427/javafx-have-multiple-panes-in-one-scene
    https://www.tutorialspoint.com/javafx/layout_borderpane.htm
    https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
    https://www.tutorialspoint.com/javafx/layout_gridpane.htm

    Event Handlers
    https://www.javatpoint.com/javafx-event-handlers

    ComboBox
    https://www.geeksforgeeks.org/javafx-combobox-with-examples/

    Set minimum size of Stage
    https://stackoverflow.com/questions/35864241/how-to-set-minimum-size-of-container-in-fxml

    Buttons
    http://tutorials.jenkov.com/javafx/button.html#button-size
 */
package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Application  {
    private int WIDTH = 700;
    private int HIEGHT = 600;
    private String comboBoxFish;
    private int DAY=0, FILLED=0, DIED=0;
    private int gridSize=0;
    private GridPane gridPane;
    private Scene scene;
    private HBox hbox;
    private VBox vbox;
    private Label dayFilledDied;

    private ArrayList<Fish> aquariumFish;
    private ArrayList<Button> aquariumButtons;
    private ArrayList<EventHandler<ActionEvent>> eventList;
    private ArrayList<ColumnConstraints> ccList;
    private ArrayList<RowConstraints> rcList;
    private HashMap<Button,Fish> buttonToFish;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        /*****************************************************************************/
        // Initialize container variables
        /*****************************************************************************/
        init();

        /*****************************************************************************/
        // Set up the Label and Textview for Feed Amount
        // https://www.geeksforgeeks.org/javafx-textfield/
        /*****************************************************************************/
        Label feedLabel = new Label("Feed Amount");
        TextField tf = new TextField("some number");
        EventHandler<ActionEvent> feedFishEvent = e -> {
            feedLabel.setText(tf.getText());
            // call function to feed the fish!!!!
            // need to define function first !!!
            int amt = Integer.parseInt(tf.getText())/fishCount();
            aquariumFish.forEach((x)->{
                x.feedFish(amt);
            });
            buttonToFish.forEach((b,f)->{
                b.setText("your mom!");
                b.setText(comboBoxFish+"\nHealth: "+f.getHealth()+"\nHunger: "+f.getHunger());
            });
        };
        tf.setOnAction(feedFishEvent);

        /*****************************************************************************/
        // Set up New Day button
        // https://www.geeksforgeeks.org/javafx-button-with-examples/
        /*****************************************************************************/
        Button newDayButton = new Button("New Day");
        EventHandler<ActionEvent> newDayEvent = e -> {
            DAY+=1;
            dayFilledDied.setText("\t\t\t\t\t\tDay: "+DAY+
                    "\t\t\t\t\t\n\t\t\t\t\t\tFilled: "+FILLED+"\t\t\t\t\t\t\n\t\t\t\t\t\tDied: "+DIED+
                    "\t\t\t\t\t\t\t\t\t\t\t");
            aquariumFish.forEach((x)->{
                x.newDay();
            });
            buttonToFish.forEach((b,f)->{
                b.setText(comboBoxFish+"\nHealth: "+f.getHealth()+"\nHunger: "+f.getHunger());
            });
        };
        newDayButton.setOnAction(newDayEvent);

        /*****************************************************************************/
        // Set up CombBox
        /*****************************************************************************/
        String fish_type[] = { "Angelfish", "Goldfish" };
        ComboBox combo_box = new ComboBox(FXCollections .observableArrayList(fish_type));
        EventHandler<ActionEvent> event = e -> { comboBoxFish = (String)combo_box.getValue(); };
        combo_box.setOnAction(event);
        vbox.getChildren().addAll(combo_box,feedLabel,tf,newDayButton);

        /*****************************************************************************/
        // Set up the Labels about Aquarium
        /*****************************************************************************/
        dayFilledDied = new Label("\t\t\t\t\t\tDay: "+DAY+
                "\t\t\t\t\t\n\t\t\t\t\t\tFilled: "+FILLED+"\t\t\t\t\t\t\n\t\t\t\t\t\tDied: "+DIED+
                "\t\t\t\t\t\t\t\t\t\t\t");
        hbox.getChildren().addAll(dayFilledDied);

        /*****************************************************************************/
        // Set up buttons for resizing the Aquarium
        /*****************************************************************************/
        Button thrbythr = new Button("3x3");
        Button fourbyfive = new Button("4x5");
        Button sixbyeight = new Button("6x8");
        EventHandler<ActionEvent> thrEvent = e -> { remakeGridPane(0, stage); };
        EventHandler<ActionEvent> fourEvent = e -> { remakeGridPane(1, stage); };
        EventHandler<ActionEvent> sixEvent = e -> { remakeGridPane(2, stage); };
        thrbythr.setOnAction(thrEvent);
        fourbyfive.setOnAction(fourEvent);
        sixbyeight.setOnAction(sixEvent);
        hbox.getChildren().addAll(thrbythr,fourbyfive,sixbyeight);

        /*****************************************************************************/
        // Set up the Gridpane to be nested in the border pane
        /*****************************************************************************/
        makeGridPane(0,stage);

        /*****************************************************************************/
        // Instantiating the BorderPane class. Add stuff to the border and center.
        /*****************************************************************************/
        BorderPane bPane = new BorderPane();
        bPane.setBottom(hbox);
        bPane.setLeft(vbox);
        bPane.setCenter(gridPane);

        /*****************************************************************************/
        //Creating a scene object
        /*****************************************************************************/
        scene = new Scene(bPane,WIDTH,HIEGHT);
        //Setting title to the Stage
        stage.setTitle("BorderPane Example");
        // Set Minimum width and height of the stage (in writeup)
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }

    /******************************************************************************/
    // initialize variables
    /******************************************************************************/
    public void init(){
        hbox = new HBox();
        vbox = new VBox();
        gridPane = new GridPane();
        aquariumButtons = new ArrayList<Button>();
        ccList = new ArrayList<ColumnConstraints>();
        rcList = new ArrayList<RowConstraints>();
        eventList = new ArrayList<EventHandler<ActionEvent>>();
        aquariumFish = new ArrayList<Fish>();
        buttonToFish = new HashMap<Button,Fish>();
        comboBoxFish="Goldfish";
    }

    /******************************************************************************/
    // make grid pane
    /******************************************************************************/
    public void makeGridPane(int size,Stage stage){
        aquariumButtons.clear();
        int r, c;
        if(size==0) r=c=3;
        else if(size==1) { r=4; c=5; }
        else { r=6; c=8; }

        FILLED=0;
        DIED=0;
        DAY=0;

        for(int i=0;i<r;i++){
            ColumnConstraints cc=new ColumnConstraints();
            cc.setPercentWidth(100.0/r);
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            ccList.add(cc);
        }
        gridPane.getColumnConstraints().addAll(ccList);
        for(int i=0;i<c;i++){
            RowConstraints rc=new RowConstraints();
            rc.setPercentHeight(100.0/c);
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            rcList.add(rc);
        }
        gridPane.getRowConstraints().addAll(rcList);

        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                Button b1 = new Button("None");
                b1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                b1.setMinHeight(30);
                Fish f1;
                if(comboBoxFish=="Goldfish") f1=new Goldfish();
                else f1=new Angelfish();
                eventList.add(e -> {
                    buttonToFish.put(b1,f1);
                    b1.setText(comboBoxFish+"\nHealth: "+f1.getHealth()+"\nHunger: "+f1.getHunger());
                });
                b1.setOnAction(eventList.get(eventList.size()-1));
                aquariumButtons.add(b1);
                aquariumFish.add(f1);
                gridPane.add(b1, i, j, 1, 1);
            }
        }
    }

    /******************************************************************************/
    // call remakeGridPane when resize button is clicked
    // http://tutorials.jenkov.com/javafx/textarea.html
    /******************************************************************************/
    public void remakeGridPane(int size,Stage stage){
        gridSize=size;
        gridPane.getChildren().clear();
        aquariumButtons.clear();
        FILLED=0;
        DIED=0;
        DAY=0;

        ccList.clear();
        rcList.clear();
        eventList.clear();

        int r, c;
        if(size==0) r=c=3;
        else if(size==1) { r=4; c=5; }
        else { r=6; c=8; }

        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        for(int i=0;i<r;i++){
            ColumnConstraints cc=new ColumnConstraints();
            cc.setPercentWidth(100.0/r);
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            ccList.add(cc);
        }
        gridPane.getColumnConstraints().addAll(ccList);
        for(int i=0;i<c;i++){
            RowConstraints rc=new RowConstraints();
            rc.setPercentHeight(100.0/c);
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            rcList.add(rc);
        }
        gridPane.getRowConstraints().addAll(rcList);

        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                Button b1 = new Button("None");
                b1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                b1.setMinHeight(30);
                Fish f1;
                if(comboBoxFish=="Goldfish") f1=new Goldfish();
                else f1=new Angelfish();
                eventList.add(e -> {
                    if(comboBoxFish=="Goldfish") {
                        buttonToFish.put(b1,f1);
                        b1.setText("Goldfish\n"+"Health: "+f1.getHealth()+"\nHunger: "+f1.getHunger());
                    } else if(comboBoxFish=="Angelfish") {
                        buttonToFish.put(b1, new Angelfish());
                        b1.setText("Angelfish\n"+"Health: "+f1.getHealth()+"\nHunger: "+f1.getHunger());
                    }
                });
                b1.setOnAction(eventList.get(eventList.size()-1));
                aquariumButtons.add(b1);
                aquariumFish.add(f1);
                gridPane.add(b1, i, j, 1, 1);
            }
        }
        stage.setScene(scene);
        stage.show();
    }


    /*********************************************************************************/
    // fish count
    /*********************************************************************************/
    private int fishCount(){
        if(gridSize==0) return 9;
        if(gridSize==1) return 20;
        return 48;
    }

    //    public void newDay(){
    //        for(Fish f : Aquarium){
    //            f.incHunger();
    //            f.decHealth();
    //        }
    //    }
    //    public void feedFish(int amount){
    //        int amountPerFish = amount / Aquarium.size();
    //        for(Fish f : Aquarium){
    //            f.feedFish(amountPerFish);
    //        }
    //    }

}


