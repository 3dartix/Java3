package Lesson_4;

public class IntegerTest {
    private static Integer n = new Integer(300);

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static class DeadThreadOne extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("DeadThreadOne держит lock1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadOne ждет lock2...");
                synchronized (lock2) {
                    System.out.println("DeadThreadOne держит lock1 и lock2");
                }
            }
        }
    }

    static class DeadThreadTwo extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("DeadThreadTwo держит lock2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadTwo ждет lock1...");
                synchronized (lock1) {
                    System.out.println("DeadThreadTwo держит lock1 и lock2");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadThreadOne deadThreadOne = new DeadThreadOne();
        deadThreadOne.start();

        DeadThreadTwo deadThreadTwo = new DeadThreadTwo();
        deadThreadTwo.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (n) {
//                    n++;
//                    System.out.println("X");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //n++;
//                    System.out.println(n);
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (n) {
//                    n++;
//                    System.out.println("Y");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //n++;
//                    System.out.println(n);
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (n) {
//                    n++;
//                    System.out.println("Z");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //n++;
//                    System.out.println(n);
//                }
//            }
//        }).start();
    }
}
