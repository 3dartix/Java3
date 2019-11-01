package Lesson_5.dz;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    public static volatile CountDownLatch cdl = new CountDownLatch(4);
    boolean isReady = false; // флаг для повторного запуска. false - машины готовятся, true - машины едут

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        if (!isReady) {
            try {
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int) (Math.random() * 800));
                System.out.println(this.name + " готов");
                cdl.countDown();
                isReady = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            for (int i = 0; i < race.getStages().size(); i++) {
                //хардкод. когда мы прошли первый этап начинается тоннель, в кот. может заехать тольео 2 машины
                //используем Semaphore smp
                if(i == 1) {
                    try {
                        MainClass.smp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                race.getStages().get(i).go(this);
                MainClass.smp.release();

                //хардкод. чтобы занимаемые места выводить в консоль последовательно использую барьер
                if(i == 2) {
                    MainClass.PLACE++;
                    System.out.println(name + " занимает " + MainClass.PLACE + " место");
                }
            }
            cdl.countDown();

            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
