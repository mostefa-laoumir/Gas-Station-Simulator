package sample;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.concurrent.Semaphore;

public class Main extends Application {

    public static final Semaphore s = new Semaphore(1);
    public static Semaphore g = new Semaphore(1);
    public static Semaphore pay = new Semaphore(1);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scn = new Scene(root, 1300, 700);
        primaryStage.setScene(scn);
        primaryStage.show();
        primaryStage.setResizable(false);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
