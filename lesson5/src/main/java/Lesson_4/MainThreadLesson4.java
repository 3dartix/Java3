package Lesson_4;

public class MainThreadLesson4 {
    public static void main(String[] args) {
        // 1
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(1);
//            }
//        });
//        t1.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();

        while (true) {
            if (!t1.isAlive()) {
                break;
            }
        }

        System.out.println("End");

    }
}

class MyThreadClass extends Thread {
    public void run() {
        System.out.println(1);
    }
}

class MyRunnableClass implements Runnable {
    public void run() {
        System.out.println(1);
    }
}
