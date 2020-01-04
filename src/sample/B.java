package sample;

import java.util.ArrayList;

public class B extends Thread {
    public static ArrayList<String> waiting = new ArrayList<>();

        public String name ="";

    public B(String name){
        this.name = name;
    }


    @Override
    public void run() {
        try {
            if(Main.s.availablePermits()<=0) waiting.add(name);
            Main.s.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waiting.remove(name);
        for (String names:waiting) {
            System.out.println("car : "+names + " is waiting...");
        }

        System.out.println("car "+ name + " is filling essance");

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.s.release();
        System.out.println("-------------------------");
        try {
            Main.pay.acquire();
            System.out.println("car "+ name + " is paying");
            sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }





        Main.pay.release();
        System.out.println("-------------------------");


    }
}
