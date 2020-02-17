package gabe;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


public class Main extends Application {
   public static final int WIDTH = 400;
   public static final int HEIGHT = 400;
   public static Layout layout;

   public static void main(String[] args) {
      launch(args);
   }

  @Override
  public void start(Stage primaryStage) {
      // create new Layout to build Layout
      // layout = new Layout();

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

      Scene scene = new Scene(gridPane, 240, 100);
      primaryStage.setScene(scene);
      primaryStage.show();
  }
}


