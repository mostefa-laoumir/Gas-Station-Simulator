package sample;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import sample.CoordinatesProvider.*;


import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.CoordinatesProvider.*;

public class Controller implements Initializable {
    @FXML
    ImageView bg, car;
    @FXML
    Polyline line1;
    @FXML
    public static ArrayList<ImageView> carsImg = new ArrayList<>();
    public static ArrayList<A> waitingCars = new ArrayList<>();
    public static ArrayList<AB> waitingCarsToPay = new ArrayList<>();
    public static ArrayList<A> cars = new ArrayList<>();
    @FXML
    Button addBtn;
@FXML
Pane pane;

    private ImageView createImageViewOfCar(String nameOfImage) throws Exception {
        Image image = new Image(getClass().getResource("/res/" + nameOfImage).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(CoordinatesProvider.getInitialPoint().getX());
        imageView.setLayoutY(CoordinatesProvider.getInitialPoint().getY());

        return imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



            addBtn.setOnAction(event->{
                try {
                   ImageView image = createImageViewOfCar("car4.png");
                    carsImg.add(image);
                    pane.getChildren().add(carsImg.get(carsImg.size()-1));
                    cars.add(new A("a",image));
                    cars.get(cars.size()-1).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

    /*MyTransitions.goToFuel(car);
    MyTransitions.goToPay(car);*/
   /* A a = new A("a", car);
    a.start();*/





pane.setOnMouseClicked((event -> {
    System.out.println(""+event.getX()+", "+event.getY());

}));
    }

  /* @FXML
    public void mouse(MouseEvent e){
        System.out.println("x= "+e.getX()+", y= "+e.getY());
    }*/
    @FXML
    public void add(javafx.event.ActionEvent actionEvent) {
    }
}
