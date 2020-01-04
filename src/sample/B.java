package sample;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class B extends Thread {
    String name ="";
    ImageView imageView;


    public B(String name, ImageView imageView) {
        this.name=name;
        this.imageView = imageView;

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
                MyTransitionsGaz.goToWaitPoint(imageView);
                Thread.sleep(1000);
                Main.waitingPoint.acquire();
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
                    MyTransitions.waitAfterCarPay(imageView, Controller.waitingCarsToPay);
                    Controller.waitingCarsToPay.add(imageView);
                }
                Main.pay.acquire();
                MyTransitionsGaz.goToPay(imageView);
                Thread.sleep(15000);
                MyTransitionsGaz.goToEnd(imageView);
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
