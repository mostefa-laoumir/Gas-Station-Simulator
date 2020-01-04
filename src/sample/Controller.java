package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    ImageView bg, car;
    @FXML
    Label total;
    public static double totalIncome = 0;
    public static ArrayList<ImageView> carsImg = new ArrayList<>();
    public static ArrayList<VoitureEssance> waitingCars = new ArrayList<>();
    public static ArrayList<VoitureGPL> waitingCarsGaz = new ArrayList<>();
    public static ArrayList<ImageView> waitingCarsToPay = new ArrayList<>();
    public static ArrayList<VoitureEssance> cars = new ArrayList<>();
    public static ArrayList<VoitureGPL> carsGaz = new ArrayList<>();

    @FXML
    Button addA,addB;
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
    private ImageView createImageViewOfCarGaz(String nameOfImage) throws Exception {
        Image image = new Image(getClass().getResource("/res/" + nameOfImage).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(CoordinatesProvider.getInitialPointGaz().getX());
        imageView.setLayoutY(CoordinatesProvider.getInitialPointGaz().getY());

        return imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            addA.setOnAction(event->{
                try {
                   ImageView image = createImageViewOfCar("car4.png");
                    carsImg.add(image);
                    pane.getChildren().add(carsImg.get(carsImg.size()-1));
                    cars.add(new VoitureEssance("a",image,total));
                    cars.get(cars.size()-1).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        addB.setOnAction(event->{
            try {
                ImageView image = createImageViewOfCarGaz("car2.png");
                carsImg.add(image);
                pane.getChildren().add(carsImg.get(carsImg.size()-1));
                carsGaz.add(new VoitureGPL("a",image,total));
                carsGaz.get(carsGaz.size()-1).start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
  




pane.setOnMouseClicked((event -> {
    System.out.println(""+event.getX()+", "+event.getY());

}));
    }


    @FXML
    public void add(javafx.event.ActionEvent actionEvent) {
    }
}
