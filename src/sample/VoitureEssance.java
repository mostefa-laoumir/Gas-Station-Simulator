package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class VoitureEssance extends Thread {
String name ="";
ImageView imageView;
Label label;


    public VoitureEssance(String name, ImageView imageView, Label label) {
        this.name=name;
        this.imageView = imageView;
        this.label = label;

    }

    @Override
    public void run() {
        if(Main.s.availablePermits()==0) {
            MyTransitionsEssance.waitAfterCarFuel(imageView, Controller.waitingCars);
            Controller.waitingCars.add(this);
        }
        try {
            Main.s.acquire();
            MyTransitionsEssance.goToFuel(imageView);
            Thread.sleep(10000);
            MyTransitionsEssance.goToWaitPoint(imageView);
            Main.waitingPoint.acquire();
            Thread.sleep(1000);
            Main.s.release();
            if(!Controller.waitingCars.isEmpty()) {
                Controller.waitingCars.remove(0);
                for (int i = 0; i < Controller.waitingCars.size(); i++)
                    MyTransitionsEssance.increse(Controller.waitingCars.get(i).imageView,Controller.waitingCars,i+1);
            }
            Thread.sleep(3000);
            MyTransitionsEssance.goThroughOneWay1(imageView);
            Thread.sleep(3000);
            Main.waitingPoint.release();
            MyTransitionsEssance.goThroughOneWay2(imageView);
            Thread.sleep(3000);
            if(Main.pay.availablePermits()==0) {
                MyTransitionsEssance.waitAfterCarPay(imageView, Controller.waitingCarsToPay);
                Controller.waitingCarsToPay.add(imageView);
            }
            Main.pay.acquire();
            MyTransitionsEssance.goToPay(imageView);
            Thread.sleep(15000);
            Controller.totalIncome += 29.99;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    label.setText(String.format("Total : %.2f$", Controller.totalIncome));
                }

            });
            MyTransitionsEssance.goToEnd(imageView);
            Main.pay.release();
            if(!Controller.waitingCarsToPay.isEmpty()) {
                Controller.waitingCarsToPay.remove(0);
                for (int i = 0; i < Controller.waitingCarsToPay.size(); i++)
                    MyTransitionsEssance.increseToPay(Controller.waitingCarsToPay.get(i),i+1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

