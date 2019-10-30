package Lesson_4.dz;

public class task1 {
    public static int STATUS = 0;
    public static int ITERATION = 5;

    public static void main(String[] args) throws InterruptedException {
        task1 obj = new task1();
        new A(obj).start();
        new B(obj).start();
        new C(obj).start();
    }
}

class A extends Thread {
    task1 obj;

    public A(task1 obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 0; i < task1.ITERATION; i++) {
            synchronized (obj) {
                while (task1.STATUS != 0) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task1.STATUS++;
                System.out.print("A");
                obj.notifyAll();
            }
        }
    }
}

class B extends Thread {
    task1 obj;

    public B(task1 obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 0; i < task1.ITERATION; i++) {
            synchronized (obj) {
                obj.notifyAll();
                while (task1.STATUS != 1) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task1.STATUS++;
                System.out.print("B");
                obj.notifyAll();
            }
        }
    }
}

class C extends Thread {
    task1 obj;

    public C(task1 obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 0; i < task1.ITERATION; i++) {
            synchronized (obj) {
                obj.notifyAll();
                while (task1.STATUS != 2) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task1.STATUS = 0;
                System.out.print("C");
                obj.notifyAll();
            }
        }
    }
}




