package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static sample.CoordinatesProvider.*;

public class MyTransitionsGaz{

    public static void goToFuel(ImageView image){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(image);
        tt.setDuration(Duration.seconds(5));
        // tt.setFromX(image.getX());
        // tt.setFromY(image.getY());
        tt.setToX(getGazPoint().getX()-image.getLayoutX());
        tt.setToY(getGazPoint().getY()-image.getLayoutY());
        //   tt.setCycleCount(Animation.INDEFINITE);
        tt.play();

    }
    public static void goToWaitPoint(ImageView image, Semaphore s){
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TranslateTransition t1 = new TranslateTransition();
        t1.setNode(image);
        t1.setDuration(Duration.seconds(3));
        //t1.setFromX(getFuelPoint().getX());
        //  t1.setFromY(getFuelPoint().getY());
        t1.setToX(89-image.getLayoutX());
        t1.setToY(308-image.getLayoutY());
        t1.play();
        t1.setOnFinished(event -> image.setRotate(image.getRotate()-90));
        s.release();

    }
    public static void goThroughOneWay1(ImageView image, Semaphore s){
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TranslateTransition t2 = new TranslateTransition();
        t2.setNode(image);
        t2.setDuration(Duration.seconds(2));
        //t2.setFromX(89);
//        t2.setToX(90-image.getLayoutX());
        t2.setToY(612-image.getLayoutY());
        t2.play();
        t2.setOnFinished(event -> image.setRotate(image.getRotate()-45));
        s.release();


    }
    public static void goThroughOneWay2(ImageView image, Semaphore s){
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TranslateTransition t3 = new TranslateTransition();
        t3.setNode(image);
        t3.setDuration(Duration.seconds(3));
        //  t3.setFromX(90);
        // t3.setFromY(612);
        t3.setToX(140-image.getLayoutX());
        t3.setToY(650-image.getLayoutY());
        t3.play();
        t3.setOnFinished(event -> image.setRotate(image.getRotate()-45));
        s.release();

    }
    public static void goToPay(ImageView image, Semaphore s){
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TranslateTransition t4 = new TranslateTransition();
        t4.setNode(image);
        t4.setDuration(Duration.seconds(4));
        //  t4.setFromX(140);
        //  t4.setFromY(675);
        t4.setToX(getPayPoint().getX()-image.getLayoutX());
        t4.setToY(getPayPoint().getY()-image.getLayoutY());
        t4.play();
        t4.setOnFinished(event -> s.release());

    }
    public static void goToEnd(ImageView image){
        TranslateTransition t = new TranslateTransition();
        t.setNode(image);
        t.setDuration(Duration.seconds(2));
        //  t.setFromX(getPayPoint().getX());
        // t.setFromY(getPayPoint().getY());
        t.setToX(getOutPoint().getX()-image.getLayoutX());
        t.setToY(getOutPoint().getY()-image.getLayoutY());
        t.play();

    }

    public static void waitAfterCarGaz(ImageView image, ArrayList<VoitureGPL> crs, Semaphore s){
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ImageView image2 = crs.get(crs.size()-2).imageView;
        int n = crs.size()+1;
        double x = n * 60;
        TranslateTransition t = new TranslateTransition(Duration.seconds(4),image);
        // t.setToX(image2.getLayoutX()-image.getLayoutX()+60);
        //t.setToY(image2.getLayoutY()-image.getLayoutY());
        t.setToX(getGazPoint().getX()-image.getLayoutX()+x);
        t.setToY(getGazPoint().getY()-image.getLayoutY());
        t.play();
        t.setOnFinished(event -> s.release());

    }

    public static void increse(ImageView image, ArrayList<VoitureEssance> crs, int index){
        // ImageView image2 = crs.get(crs.size()-2).imageView;

        double x = index * 60;
        TranslateTransition t = new TranslateTransition(Duration.seconds(4),image);
        // t.setToX(image2.getLayoutX()-image.getLayoutX()+60);
        //t.setToY(image2.getLayoutY()-image.getLayoutY());
        t.setToX(getGazPoint().getX()-image.getLayoutX()+x);
        t.setToY(getGazPoint().getY()-image.getLayoutY());
        t.play();
    }
    public static void increseToPay(ImageView image,int index){
        // ImageView image2 = crs.get(crs.size()-2).imageView;

        double x = -index * 60;
        TranslateTransition t = new TranslateTransition(Duration.seconds(4),image);
        // t.setToX(image2.getLayoutX()-image.getLayoutX()+60);
        //t.setToY(image2.getLayoutY()-image.getLayoutY());
        t.setToX(getPayPoint().getX()-image.getLayoutX()+x);
        t.setToY(getPayPoint().getY()-image.getLayoutY());
        t.play();
    }




}
