package Lesson_5;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MainFive {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("new thread");
                return new Thread(r);
            }
        });
//
//        pool.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(2);
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(2);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(3);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(4);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(5);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(6);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(7);
//            }
//        });
//
        //pool.shutdown();
//
//        try {
//            pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        pool.shutdown();

//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        int[] source = {1,2,3,4};
//
//        int[] res1 = new int[2];
//        int[] res2 = new int[2];
//
//        System.arraycopy(source, 0, res1, 0, res1.length);
//        System.arraycopy(source, 2, res2, 0, res2.length);
//
//        System.out.println(Arrays.toString(res1));
//        System.out.println(Arrays.toString(res2));
//
//        Callable<Integer> callable1 = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int res = 0;
//
//                for (int i = 0; i < res1.length; i++) {
//                    res += res1[i];
//                }
//
//                return res;
//            }
//        };
//
//        Callable<Integer> callable2 = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int res = 0;
//
//                for (int i = 0; i < res2.length; i++) {
//                    res += res2[i];
//                }
//
//                return res;
//            }
//        };
//
//        Future<Integer> f1 = executorService.submit(callable1);
//        Future<Integer> f2 = executorService.submit(callable2);
//
//        System.out.println(f1.get() + f2.get());
//
//        executorService.shutdown();

//        Random random = new Random();
//
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(new Date() + "start work");
//                try {
//                    Thread.sleep(random.nextInt(1000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(new Date() + "end work");
//            }
//        }, 0, 1000L, TimeUnit.MILLISECONDS);


//        MainFive mainFive = new MainFive();
//        mainFive.testSchedule();






//        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(1);
//
//        abq.add("as");
//        abq.add("as");

//        Vector<String> vector = new Vector<>();

//        Map<String, String> hm = Collections.synchronizedMap(new HashMap<>());
//
//        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();

//        Semaphore smp = new Semaphore(4);
//
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        smp.acquire();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(1);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    smp.release();
//                    System.out.println(2);
//                }
//            });
//        }
//        service.shutdown();



//        CountDownLatch cdl = new CountDownLatch(10);
//
//        for (int i = 0; i < 10; i++) {
//            int w = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(w + "-1");
//                    try {
//                        Thread.sleep((int)(Math.random() * 3000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + "-2");
//                    cdl.countDown();
//                }
//            }).start();
//        }
//        cdl.await();
//        System.out.println("End");


//        CyclicBarrier cb = new CyclicBarrier(10);
//        for (int i = 0; i < 20; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("start");
//                    try {
//                        Thread.sleep((int)(Math.random() * 5000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("ready");
//                    try {
//                        cb.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("end");
//                }
//            }).start();
//        }

//        ReentrantLock r1 = new ReentrantLock();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock();
//                System.out.println(1);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(2);
//                r1.unlock();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock();
//                System.out.println(3);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(4);
//                r1.unlock();
//            }
//        }).start();

        AtomicInteger ai = new AtomicInteger(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ai.incrementAndGet();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ai.decrementAndGet();
                }
            }
        }).start();

        System.out.println(ai);
//        MainFive mainFive = new MainFive();
//        mainFive.testScheduleLock(r1);

    }

    void testScheduleLock(ReentrantLock lock) {

    }


    void testSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        Timer timer = new Timer();

        timer.schedule(new RemindTask(), time);
    }
}

class RemindTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Time up!");
    }
}
