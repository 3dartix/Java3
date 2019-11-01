package Lesson_4;

public class Start {
    public static void main(String[] args) {
        TestThread e1 = new TestThread();
        TestThread e2 = new TestThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TestThread.methodStatic1();
                // e1.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TestThread.methodStatic1();
                // e2.method1();
            }
        }).start();

    }
}

class TestThread {
    public synchronized static void methodStatic1() {
        System.out.println("s1");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("s2");
    }

    public synchronized void method1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
}
