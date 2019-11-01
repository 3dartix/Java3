package Lesson_5.dz;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static volatile Semaphore smp = new Semaphore(2);
    public static int PLACE;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //создаем этапы конки путь до тунеля тунель путь для финиша
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        //создаем пул потоков
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            Thread t = new Thread(cars[i]);
            //заносим в пул потоки (машины) для соблюдения очереди вывода
            pool.execute(t);
        }

        pool.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                //создаем заглушку для корректной печати занимаемых мест
                //создаем новую, т.к эта уже использовалась во время подготовки
                Car.cdl = new CountDownLatch(cars.length);
            }
        }));

        for (int i = 0; i < cars.length; i++) {
            Thread t = new Thread(cars[i]);
            pool.execute(t);
        }


        pool.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            }
        }));

        pool.shutdown();
    }
}
