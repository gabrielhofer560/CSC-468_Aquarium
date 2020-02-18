package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
    http://tutorials.jenkov.com/javafx/gridpane.html
    https://stackoverflow.com/questions/33339427/javafx-have-multiple-panes-in-one-scene
    https://www.tutorialspoint.com/javafx/layout_borderpane.htm
    https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
    https://www.tutorialspoint.com/javafx/layout_gridpane.htm
    https://www.javatpoint.com/javafx-event-handlers
 */
public class Main extends Application  {
    private int WIDTH = 500;
    private int HIEGHT = 500;

/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GridPane Experiment");

        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");

        GridPane gridPane = new GridPane();

        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 1, 0, 1, 1);
        gridPane.add(button3, 2, 0, 1, 1);
        gridPane.add(button4, 0, 1, 1, 1);
        gridPane.add(button5, 1, 1, 1, 1);
        gridPane.add(button6, 2, 1, 1, 1);

        Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/

    @Override
    public void start(Stage stage) {
        // set up the gridpane to be nested in the border pane
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
        BorderPane bPane = new BorderPane();

        //Setting the top, bottom, center, right and left nodes to the pane
        //bPane.setTop(new TextField("Top"));
        bPane.setBottom(new TextField("Bottom"));
        bPane.setLeft(new TextField("Left"));
        //bPane.setRight(new TextField("Right"));
        //bPane.setCenter(new TextField("Center"));
        bPane.setCenter(gridPane);

        //Creating a scene object
        Scene scene = new Scene(bPane,WIDTH,HIEGHT);

        //Setting title to the Stage
        stage.setTitle("BorderPane Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
