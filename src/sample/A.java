package sample;

import javafx.scene.image.ImageView;
import sample.Main.*;

import java.util.ArrayList;

public class A extends Thread {
String name ="";
ImageView imageView;
    ArrayList<A> waitingCars = new ArrayList<>();

    public A(String name, ImageView imageView) {
        this.name=name;
        this.imageView = imageView;

    }

    @Override
    public void run() {
        if(Main.s.availablePermits()==0) {
            MyTransitions.waitAfterCarFuel(imageView, Controller.cars);
            waitingCars.add();
        }
        try {
            Main.s.acquire();
            MyTransitions.goToFuel(imageView);
            Thread.sleep(10000);
            MyTransitions.goToWaitPoint(imageView);
            Main.s.release();
            Controller.cars.remove(0);

            Thread.sleep(3000);
            MyTransitions.goThroughOneWay1(imageView);
            Thread.sleep(3000);
            MyTransitions.goThroughOneWay2(imageView);
            Thread.sleep(3000);

            MyTransitions.goToPay(imageView);
            Thread.sleep(5000);
            System.out.println("ee");
            MyTransitions.goToEnd(imageView);
            System.out.println("ff");
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

