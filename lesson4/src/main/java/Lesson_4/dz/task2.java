package Lesson_4.dz;

import Lesson_4.Start;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.*;

public class task2 {
    public static List<String> PRINT_QUEUE = new ArrayList<>();
    public static List<String> SCAN_QUEUE = new ArrayList<>();


    public static void main(String[] args) {
        task2 obj = new task2();
        MFU mfu = new MFU(obj);

        // Создаем задачи на печать и сканы,
        // задачи хранятся в списках PRINT_QUEUE и SCAN_QUEUE,
        // оттуда их дергают потоки в классе MFU
        PRINT_QUEUE.add("Маша");
        PRINT_QUEUE.add("Коля");
        SCAN_QUEUE.add("Саша");
        PRINT_QUEUE.add("Ваня");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PRINT_QUEUE.add("Маша");
        SCAN_QUEUE.add("Вика");
        PRINT_QUEUE.add("Коля");
        PRINT_QUEUE.add("Ваня");
        SCAN_QUEUE.add("Женя");

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PRINT_QUEUE.add("Маша");
        SCAN_QUEUE.add("Вика");
        PRINT_QUEUE.add("Коля");
        PRINT_QUEUE.add("Ваня");
        SCAN_QUEUE.add("Женя");

        try {
            Thread.sleep(2000);
            mfu.Stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MFU {
    task2 obj;
    Thread tPrint;
    Thread tScan;

    boolean isWorking = true;

    public MFU (task2 obj){
        this.obj = obj;

        tPrint = new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator itr = obj.PRINT_QUEUE.iterator();

                while (isWorking) {
                    if (obj.PRINT_QUEUE.size() != 0) {
                        while (itr.hasNext()) {
                            Object o = itr.next();
                            itr.remove();
                            System.out.println("Печатает: " + o);
                        }
                    } else {
                        try {
                            while (obj.PRINT_QUEUE.size() == 0){
                                System.out.println("В ожидание задач печати...");
                                Thread.sleep(200);
                                if(!isWorking){
                                    break;
                                }
                            }
                            itr = obj.PRINT_QUEUE.iterator();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        tPrint.start();

        tScan = new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator itr = obj.SCAN_QUEUE.iterator();

                while (isWorking) {
                    if (obj.SCAN_QUEUE.size() != 0) {
                        while (itr.hasNext()) {
                            Object o = itr.next();
                            itr.remove();
                            System.out.println("Сканирует: " + o);
                        }
                    } else {
                        try {
                            while (obj.SCAN_QUEUE.size() == 0 ){
                                System.out.println("В ожидание задач сканирования...");
                                Thread.sleep(200);
                                if(!isWorking){
                                    break;
                                }
                            }
                            itr = obj.SCAN_QUEUE.iterator();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        tScan.start();


    }


    public void Stop(){
        isWorking = false;
        System.out.println("Выкл");
    }

}
