package sample;

import javafx.scene.image.ImageView;
import sample.Main.*;

import java.util.ArrayList;

public class A extends Thread {
String name ="";
ImageView imageView;


    public A(String name, ImageView imageView) {
        this.name=name;
        this.imageView = imageView;

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
            Main.s.release();
            if(!Controller.waitingCars.isEmpty()) {
                Controller.waitingCars.remove(0);
                for (int i = 0; i < Controller.waitingCars.size(); i++)
                    MyTransitions.increse(Controller.waitingCars.get(i).imageView,Controller.waitingCars,i+1);
            }
            Thread.sleep(3000);
            MyTransitions.goThroughOneWay1(imageView);
            Thread.sleep(3000);
            MyTransitions.goThroughOneWay2(imageView);
            Thread.sleep(3000);
            if(Main.pay.availablePermits()==0) {
                MyTransitions.waitAfterCarPay(imageView, Controller.waitingCarsToPay);
                Controller.waitingCarsToPay.add(new AB(this,null));
            }
            Main.pay.acquire();
            MyTransitions.goToPay(imageView);
            Thread.sleep(15000);
            MyTransitions.goToEnd(imageView);
            Main.pay.release();
            if(!Controller.waitingCarsToPay.isEmpty()) {
                Controller.waitingCarsToPay.remove(0);
                for (int i = 0; i < Controller.waitingCarsToPay.size(); i++)
                    MyTransitions.increseToPay(Controller.waitingCarsToPay.get(i).a.imageView,i+1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

