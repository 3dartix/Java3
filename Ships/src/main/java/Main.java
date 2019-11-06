import Storage.Clothes;
import Storage.Food;
import Storage.Fuel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int SHIP_COUNT = 5;
    public static boolean isThereProductsInPort = true;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Старт!!!");
        CyclicBarrier barrier = new CyclicBarrier(SHIP_COUNT+1);
        //созаем карту (море).
        // Порт А, Залив А, пролив (через который может пройти 1 корабль), залив В, порт В.
        MapSea map = new MapSea(
                new Port("A", new Fuel(2000), new Food(2000), new Clothes(2000)),
                new Bay(60, "Залив A"),
                new Tunnel(),
                new Bay(60, "Залив B"),
                new Port("B", new Fuel(0), new Food(0), new Clothes(0))
        );

        Ship[] ships = new Ship[SHIP_COUNT];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship(map, 20 + (int) (Math.random() * 10), barrier);
        }
        //создаем пул потоков
        //ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < ships.length; i++) {
            Thread t = new Thread(ships[i]);
            t.start();
        }

        try {
            barrier.await();
            barrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> закончили!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
