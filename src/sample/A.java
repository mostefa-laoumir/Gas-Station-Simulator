package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Main.*;

import java.util.ArrayList;

public class A extends Thread {
String name ="";
ImageView imageView;
Label label;


    public A(String name, ImageView imageView, Label label) {
        this.name=name;
        this.imageView = imageView;
        this.label = label;

    }

    @Override
    public void run() {
        if(Main.s.availablePermits()==0) {
            MyTransitions.waitAfterCarFuel(imageView, Controller.waitingCars);
            Controller.waitingCars.add(this);
        }
        try {
            Main.s.acquire();
            MyTransitions.goToFuel(imageView);
            Thread.sleep(10000);
            MyTransitions.goToWaitPoint(imageView);
            Main.waitingPoint.acquire();
            Thread.sleep(1000);
            Main.s.release();
            if(!Controller.waitingCars.isEmpty()) {
                Controller.waitingCars.remove(0);
                for (int i = 0; i < Controller.waitingCars.size(); i++)
                    MyTransitions.increse(Controller.waitingCars.get(i).imageView,Controller.waitingCars,i+1);
            }
            Thread.sleep(3000);
            MyTransitions.goThroughOneWay1(imageView);
            Thread.sleep(3000);
            Main.waitingPoint.release();
            MyTransitions.goThroughOneWay2(imageView);
            Thread.sleep(3000);
            if(Main.pay.availablePermits()==0) {
                MyTransitions.waitAfterCarPay(imageView, Controller.waitingCarsToPay);
                Controller.waitingCarsToPay.add(imageView);
            }
            Main.pay.acquire();
            MyTransitions.goToPay(imageView);
            Thread.sleep(15000);
            Controller.totalIncome += 29.99;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    label.setText(String.format("Total : %.2f$", Controller.totalIncome));
                }

            });
            MyTransitions.goToEnd(imageView);
            Main.pay.release();
            if(!Controller.waitingCarsToPay.isEmpty()) {
                Controller.waitingCarsToPay.remove(0);
                for (int i = 0; i < Controller.waitingCarsToPay.size(); i++)
                    MyTransitions.increseToPay(Controller.waitingCarsToPay.get(i),i+1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

