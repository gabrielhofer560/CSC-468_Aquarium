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

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;


import java.lang.reflect.Array;
import java.util.ArrayList;

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
public class Main extends Application  {
    private int WIDTH = 500;
    private int HIEGHT = 500;
    private String comboBoxFish = "Goldfish";
    private int aquariumWidth = 3;
    private int aquariumHeight = 3;
    private int feedAmount = 0;
    private int DAY=0, FILLED=0, DIED=0;
    private int currSize=0;
    private GridPane gridPane;
    private Scene scene;


    private ArrayList<Fish> aquariumFish;
    private ArrayList<Button> aquariumButtons;
    private ArrayList<EventHandler<ActionEvent>> eventList;
    private ArrayList<ColumnConstraints> ccList;
    private ArrayList<RowConstraints> rcList;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        /*****************************************************************************/
        // Create Hbox for bottom of border pane. Create Vbox for left of border pane.
        /*****************************************************************************/
        HBox hbox = new HBox();
        VBox vbox = new VBox();

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
        };
        tf.setOnAction(feedFishEvent);

        /*****************************************************************************/
        // Set up New Day button
        // https://www.geeksforgeeks.org/javafx-button-with-examples/
        /*****************************************************************************/
        Button newDayButton = new Button("New Day");
        EventHandler<ActionEvent> newDayEvent = e -> {
            // call function to do something!!!
        };
        newDayButton.setOnAction(newDayEvent);

        /*****************************************************************************/
        // Set up CombBox
        /*****************************************************************************/
        // fish types
        String fish_type[] = { "Angelfish", "Goldfish" };
        // Create a combo box
        ComboBox combo_box = new ComboBox(FXCollections .observableArrayList(fish_type));
        EventHandler<ActionEvent> event = e -> {
                    comboBoxFish = (String)combo_box.getValue();
        };
        combo_box.setOnAction(event);
        vbox.getChildren().addAll(combo_box,feedLabel,tf,newDayButton);

        /*****************************************************************************/
        // Set up the Labels about Aquarium
        /*****************************************************************************/
        Label day = new Label("Day: "+DAY+"\t\t\t\t");
        Label filled = new Label("Filled: "+FILLED+"\t\t\t\t");
        Label died = new Label("Died: "+DIED+"\t\t\t\t");
        hbox.getChildren().addAll(day,filled,died);

        /*****************************************************************************/
        // Set up buttons for resizing the Aquarium
        /*****************************************************************************/
        Button thrbythr = new Button("3x3");
        Button fourbyfive = new Button("4x5");
        Button sixbyeight = new Button("6x8");

        EventHandler<ActionEvent> thrEvent = e -> {
            currSize=0;
            remakeGridPane(currSize, stage);
        };

        EventHandler<ActionEvent> fourEvent = e -> {
            currSize=1;
            remakeGridPane(currSize, stage);
        };

        EventHandler<ActionEvent> sixEvent = e -> {
            currSize=2;
            remakeGridPane(currSize, stage);
        };
        thrbythr.setOnAction(thrEvent);
        fourbyfive.setOnAction(fourEvent);
        sixbyeight.setOnAction(sixEvent);
        hbox.getChildren().addAll(thrbythr,fourbyfive,sixbyeight);


        /*****************************************************************************/
        // Set up the Gridpane to be nested in the border pane
        /*****************************************************************************/
        aquariumButtons = new ArrayList<Button>();
        gridPane = new GridPane();
        makeGridPane(0,stage);

        /*****************************************************************************/
        // Instantiating the BorderPane class. Add stuff to the border and center.
        /*****************************************************************************/
        BorderPane bPane = new BorderPane();

        // put hbox on bottom border, vbox on left border, and gridpane in center
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

    public void makeGridPane(int size,Stage stage){
        aquariumButtons.clear();
        int r, c;
        if(size==0) r=c=3;
        else if(size==1) { r=4; c=5; }
        else { r=6; c=8; }

        ccList = new ArrayList<ColumnConstraints>();
        rcList = new ArrayList<RowConstraints>();
        eventList = new ArrayList<EventHandler<ActionEvent>>();

        for(int i=0;i<r+2;i++){
            ColumnConstraints cc=new ColumnConstraints();
            cc.setPercentWidth(100/r);
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            ccList.add(cc);
        }
        gridPane.getColumnConstraints().addAll(ccList);
        for(int i=0;i<c+2;i++){
            RowConstraints rc=new RowConstraints();
            rc.setPercentHeight(100/c);
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

                eventList.add(e -> {
                    // do something
                });
                b1.setOnAction(eventList.get(eventList.size()-1));
                aquariumButtons.add(b1);
                gridPane.add(b1, i, j, 1, 1);
            }
        }
    }

    public void remakeGridPane(int size,Stage stage){
        gridPane.getChildren().clear();
        aquariumButtons.clear();

        ccList.clear();
        rcList.clear();
        eventList.clear();

        int r, c;
        if(size==0) r=c=3;
        else if(size==1) { r=4; c=5; }
        else { r=6; c=8; }

        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        for(int i=0;i<r+1;i++){
            ColumnConstraints cc=new ColumnConstraints();
            cc.setPercentWidth(100/r);
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            ccList.add(cc);
        }
        gridPane.getColumnConstraints().addAll(ccList);
        for(int i=0;i<c+1;i++){
            RowConstraints rc=new RowConstraints();
            rc.setPercentHeight(100/c);
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
                // action event
                eventList.add(e -> {
                    // do something
                });
                b1.setOnAction(eventList.get(eventList.size()-1));
                aquariumButtons.add(b1);
                gridPane.add(b1, i, j, 1, 1);
            }
        }
        stage.setScene(scene);
        stage.show();
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


