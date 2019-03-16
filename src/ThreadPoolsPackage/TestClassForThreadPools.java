package ThreadPoolsPackage;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestClassForThreadPools {
    public static final int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
    public static AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) {
        ThreadPoolExecutor customThreadPool =
                ThreadPoolsFactoryClass.
                        createACustomThreadPoolExecutorUsingTheConstructor(numberOfAvailableProcessors,
                                numberOfAvailableProcessors, 2L,
                                TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<>(),
                                Executors.defaultThreadFactory(),
                                new RejectedExecutionHandler() {
                                    @Override
                                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                        if (atomicCounter.getAndIncrement() < 4) {
                                            executor.execute(r); // retry to execute the operation 4 times and if it
                                            // fails even then, handle the situation differently. Keep in mind that
                                            // his is not a good way at all to deal with retries. There are better
                                            // ways.
                                        } else {
                                            atomicCounter.lazySet(0);
                                            // perform some cleanup operation here
                                        }
                                    }
                                });


        try {
            customThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Math.toIntExact(Thread.currentThread().getId()) + " from Runnable");
                }
            });
            System.out.println(customThreadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return Math.toIntExact(Thread.currentThread().getId());
                }
            }).get() + " from Callable");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        FutureTask<Integer> futureTask = new FutureTask<>(() -> System.out.println("Test"), 10);
        customThreadPool.shutdown();
    }
}