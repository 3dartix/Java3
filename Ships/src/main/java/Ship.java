import Storage.Products;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Ship implements Runnable {
    private static int SHIP_COUNT;

    private MapSea map;
    private int speed;
    private String name;

    private Products product;
    private int limit = 300;

    private CyclicBarrier barrier;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Products getProduct() { return product; }

    public int getLimit() {
        return limit;
    }

    public void setProduct(Products product) {
        try {
            this.product = (Products) product.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.product.setUnits(0); //обнуляем продукт
    }

    public Ship(MapSea map, int speed, CyclicBarrier barrier) {
        this.map = map;
        this.speed = speed;
        SHIP_COUNT++;
        this.name = "Участник #" + SHIP_COUNT;
        this.barrier = barrier;
    }

    public void run() {
        while (Main.isThereProductsInPort){
            for (int i = 0; i < map.getStages().size(); i++) {
                map.getStages().get(i).go(this);
            }

            for (int i = map.getStages().size()-2; i > 0; i--) {
                map.getStages().get(i).go(this);
            }

//            if(!Main.isThereProductsInPort) {
//                break;
//            }
        }
        try {
            barrier.await();
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " в порту нет товаров.");
    }
}
