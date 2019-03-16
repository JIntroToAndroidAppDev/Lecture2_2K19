package ThreadPoolsPackage;

import java.util.concurrent.*;

/**
 * Here we will see how to create thread pools and configure them if and when required
 */
public class ThreadPoolsFactoryClass {

    /**
     * <b>
     *     Use it when you need sequential execution of task
     * </b>
     */
    static ExecutorService singleThreadedThreadPoolUsingExecutors = Executors.newSingleThreadExecutor();

    /**
     * <b>
     *     If it's a CPU intensive task, then use the number of available processors.
     *     If it's IO intensive task then use many number of threads (like 20 threads)
     * </b>
     */
    static ExecutorService fixedNumberOfThreadsThreadPoolUsingExecutors =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * <b>
     *     Use it when you have many tasks but they don't take a lot of time to complete
     * </b>
     */
    static ExecutorService autoManagementOfNumberOfThreadsThreadPoolUsingExecutors = Executors.newCachedThreadPool();

    /*
      Now we are going to look at thread pools that can be used to perform that needs to be executed at intervals of
      time. In short, we can perform scheduled execution of the tasks if there is such a requirement.
     */

    /**
     * <b>
     *     A normal single threaded thread pool that can be configured to execute tasks at specified intervals (or
     *     periods)
     * </b>
     */
    static ScheduledExecutorService singleThreadedScheduledScheduledThreadPoolUsingExecutors =
            Executors.newSingleThreadScheduledExecutor();

    /**
     * <b>
     *     Just like a usual thread pool with specified number of threads with added scheduling capabilities to run
     *     tasks at specified intervals
     * </b>
     */
    static ScheduledExecutorService fixedScheduledThreadPoolUsingExecutors =
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * <b>
     *     We'll see how we are going to configure the thread pools to handle scheduling of tasks
     * </b>
     */

    /**
     * <b>
     *     One-shot execution of a single task that executes after the specified delay and specified unit of time
     * </b>
     */
    static ScheduledFuture<?> scheduleThreadPoolOneTime (ScheduledThreadPoolExecutor scheduledThreadPoolExecutor,
                                                        TimeUnit timeUnit, long delayTime,
                                                        Runnable task) {
        return scheduledThreadPoolExecutor.schedule(task, delayTime, timeUnit);
    }

    /**
     * <b>
     *     Use this construct if you require to schedule tasks at definite intervals. Read the documentation for
     *     more details.
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledThreadPoolExecutor.html#scheduleAtFixedRate-java.lang.Runnable-long-long-java.util.concurrent.TimeUnit-">Docs</a>
     * </b>
     */
    static ScheduledFuture<?> scheduleThreadPoolAtFixedRate(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor,
                                                            Runnable task,
                                                            long initialDelayTime, long intervalPeriodUnitTime,
                                                            TimeUnit timeUnit) {
        return scheduledThreadPoolExecutor
                .scheduleAtFixedRate(task, initialDelayTime,intervalPeriodUnitTime, timeUnit);

    }

    /**
     * <b>
     *  Use this construct if you require to schedule tasks at definite intervals. The thread pool doesn't
     *  care if the previously executing tasks have finished or not. It simply enqueues the task for execution
     * </b>
     */
    static  ScheduledFuture<?> scheduleThreadPoolAtFixedDelay(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor,
                                                              Runnable task,
                                                              long initialDelayTime, long intervalPeriodUnitTime,
                                                              TimeUnit timeUnit){
        return scheduledThreadPoolExecutor
                .scheduleWithFixedDelay(task, initialDelayTime, intervalPeriodUnitTime, timeUnit);
    }

    /**
     * This method creates a custom thread pools with the given parameters
     * @param corePoolSize this many number of threads will be premium threads. They will have immunity from being
     *                     cleaned up if they are are sitting idle.
     * @param maxPoolSize this is the maximum number of threads that the thread pool can hold
     * @param keepAliveTime if the threads are not doing anything for this amount of specified time, kill them
     *                      threads (except for the core threads. But if you want to have them core threads killed as
     *                      well, used this methods {@link ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)}
     * @param timeUnit the unit of time for the keepAliveTime
     * @param queueOfTasks the queue to hold the tasks. Checkout the docs for {@link BlockingQueue} as well
     * @param supplierOfThreads you can either used the default one or create your own algorithm that governs thread
     *                          creation
     * @param howToDealWithFailedExecutions if the task execution fails because of the capacity of the queue being
     *                                      full or the max threads capacity is reached, the remaining tasks that
     *                                      were not executed are handled using this. Checkout the docs for the
     *                                      constructor of
     *                                      ThreadPoolExecutor{@link ThreadPoolExecutor(int, int, long, TimeUnit,
     *                                      BlockingQueue, ThreadFactory,
     *                                      RejectedExecutionHandler)} and read it
     * @return a new {@link ThreadPoolExecutor } object
     */
    static ThreadPoolExecutor createACustomThreadPoolExecutorUsingTheConstructor(int corePoolSize, int maxPoolSize,
                                                                                 long keepAliveTime, TimeUnit timeUnit,
                                                                                 BlockingQueue<Runnable> queueOfTasks,
                                                                                 ThreadFactory supplierOfThreads,
                                                                                 RejectedExecutionHandler
                                                                                         howToDealWithFailedExecutions) {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queueOfTasks,
                supplierOfThreads, howToDealWithFailedExecutions);
    }
}