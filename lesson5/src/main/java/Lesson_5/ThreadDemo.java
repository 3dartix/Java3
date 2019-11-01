package Lesson_5;

public class ThreadDemo extends Thread {
    @Override
    public void run() {
        System.out.println("test thread " + getName());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();

        t1.setPriority(1);
        t2.setPriority(8);

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
