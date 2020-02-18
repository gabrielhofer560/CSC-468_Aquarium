package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;


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


 */
public class Main extends Application  {
    private int WIDTH = 500;
    private int HIEGHT = 500;

    ArrayList<Fish> Aquarium;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        /*****************************************************************************/
        // Create Hbox for bottom of border pane. Create Vbox for left of border pane.
        /*****************************************************************************/
        //Instantiating the HBox class
        HBox hbox = new HBox();
        VBox vbox = new VBox();


        /*****************************************************************************/
        // Set up the Label and Textview for Feed Amount
        // https://www.geeksforgeeks.org/javafx-textfield/
        /*****************************************************************************/
        Label feedLabel = new Label("Feed");
        TextField b = new TextField("some number");

        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // call function to feed the fish!!!!
                // need to define funciton first !!!
            }
        };


        /*****************************************************************************/
        // Set up New Day button
        /*****************************************************************************/

        /*****************************************************************************/
        // Set up CombBox
        /*****************************************************************************/
        // Weekdays
        String fish_type[] = { "Angelfish", "Goldfish" };
        // Create a combo box
        ComboBox combo_box = new ComboBox(FXCollections .observableArrayList(fish_type));
        hbox.getChildren().addAll(combo_box);


        /*****************************************************************************/
        // Set up the Gridpane to be nested in the border pane
        /*****************************************************************************/

        // create the buttons to put in the gridpane
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");
        // create new gridpane
        GridPane gridPane = new GridPane();
        // add buttons to the gridpane
        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 1, 0, 1, 1);
        gridPane.add(button3, 2, 0, 1, 1);
        gridPane.add(button4, 0, 1, 1, 1);
        gridPane.add(button5, 1, 1, 1, 1);
        gridPane.add(button6, 2, 1, 1, 1);

        /*****************************************************************************/
        //Instantiating the BorderPane class
        /*****************************************************************************/
        BorderPane bPane = new BorderPane();

        // put hbox on bottom border, vbox on left border, and gridpane in center
        bPane.setBottom(hbox);
        bPane.setLeft(hbox);
        bPane.setCenter(gridPane);

        /*****************************************************************************/
        //Creating a scene object
        /*****************************************************************************/
        Scene scene = new Scene(bPane,WIDTH,HIEGHT);

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

}






