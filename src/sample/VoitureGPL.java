package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.concurrent.Semaphore;

public class VoitureGPL extends Thread {
    String name ="";
    ImageView imageView;
    Label label;
//    public final Semaphore animation = new Semaphore(1);



    public VoitureGPL(String name, ImageView imageView, Label label) {
        this.name=name;
        this.imageView = imageView;
        this.label = label;

    }
        @Override
        public void run() {
            if(Main.g.availablePermits()==0) {
                MyTransitionsGaz.waitAfterCarGaz(imageView, Controller.waitingCarsGaz);
                Controller.waitingCarsGaz.add(this);
            }
            try {
                Main.g.acquire();
                MyTransitionsGaz.goToFuel(imageView);
                Thread.sleep(10000);
                Main.waitingPoint.acquire();
                MyTransitionsGaz.goToWaitPoint(imageView);
                Thread.sleep(1000);
                //MyTransitions.goToWaitPoint(imageView);
                Main.g.release();
                if(!Controller.waitingCarsGaz.isEmpty()) {
                    Controller.waitingCarsGaz.remove(0);
                    for (int i = 0; i < Controller.waitingCarsGaz.size(); i++)
                        MyTransitionsGaz.increse(Controller.waitingCarsGaz.get(i).imageView,Controller.waitingCars,i+1);
                }
                Thread.sleep(3000);
                MyTransitionsGaz.goThroughOneWay1(imageView);
                Thread.sleep(3000);
                Main.waitingPoint.release();
                MyTransitionsGaz.goThroughOneWay2(imageView);
                Thread.sleep(3000);
                if(Main.pay.availablePermits()==0) {
                    MyTransitionsEssance.waitAfterCarPay(imageView, Controller.waitingCarsToPay);
                    Controller.waitingCarsToPay.add(imageView);
                }
                Main.pay.acquire();
                MyTransitionsGaz.goToPay(imageView);
                Thread.sleep(15000);
                Controller.totalIncome += 9.99;
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        label.setText(String.format("Total : %.2f$", Controller.totalIncome));
                    }

                });                MyTransitionsGaz.goToEnd(imageView);
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
