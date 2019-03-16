package ThreadPoolsPackage;

import java.util.concurrent.*;

/**
 *     Let's see how to make these thread pools work.
 *
 *     We will also see how to used another type of task submission class other than {@linkplain Runnable}.
 *     It's called {@linkplain Callable}. The difference between {@linkplain Runnable} and
 *     {@linkplain Callable} is that {@linkplain Callable} is a generic method that returns the type of data
 *     specified in it's type argument.
 *
 * @implNote We will not catch any exception in all the methods here that can be not private. We will propagate it up
 * the chain and get catch it where it will be needed to catch. Also, one of the things that you will see here is
 * that we are not creating any objects here, rather, we are simply passing around objects that are created in other
 * classes. We are <b>Injecting Dependency</b> and it's always a good programming practice to outsource object
 * creation to other classes (use the <b>new</b> keyword as less as possible.
 */
public class WorkingWithThreadPools {
    /**
     * Run a simple {@link Runnable} task inside a thread pool
     * @param targetThreadPool  {@link ExecutorService}
     * @param taskToRunInTheThreadPool  {@link Runnable}
     */
    static void executeRunnableToThreadPools(ExecutorService targetThreadPool, Runnable taskToRunInTheThreadPool) {
        targetThreadPool.execute(taskToRunInTheThreadPool);
    }

    /**
     * {@link Future} is a construct that helps us to store that value that is returned after the computation has
     * completed by the thread that processed a {@link Callable}. We will see how to get the value that we require in
     * the sections to come
     * @param targetThreadPool  {@link ExecutorService}
     * @param taskToRunInTheThreadPool  {@link Callable}
     * @return {@link Future} which contains the result of whatever was computed by the thread in the thread pool
     */
    static Future<?> submitCallableToThreadPool(ExecutorService targetThreadPool,
                                                 Callable<?> taskToRunInTheThreadPool) {
        return targetThreadPool.submit(taskToRunInTheThreadPool);
    }

    /**
     * {@link Future#get()} is a blocking operation. This means that when this statement is executed, it will block
     * all further execution in that current thread where it is called. Basically, it means that if you have a long
     * running task and you call this method, then the program will get stuck as long was the execution finishes and
     * a value in returned or an exception is thrown that makes it jump out of it's current execution process.
     * @param futureContainingOurData {@link Future} that has our data
     * @return {@link Object} which is the data that we get after processing of the the {@link Callable} by the
     * thread in the thread pool
     * @throws ExecutionException a type of runtime exception that gets thrown when the
     * task that is executing, throws any kind of exception inside itself.
     * @throws InterruptedException If the current running state of the thread is abruptly changed, then this
     * exception is thrown
     */
    static Object getDataFromFuture(Future<?> futureContainingOurData) throws ExecutionException, InterruptedException {
        return futureContainingOurData.get();
    }

    /**
     * It works exactly the same way as {@link WorkingWithThreadPools#getDataFromFuture(Future)} with the added
     * condition that it will time out and throw an exception if the task doesn't complete and get's ready with some
     * result within the specified time
     *
     * @param futureContainingOurData {@link Future} that has our data
     * @param magnitudeOfTimeToWaitForBeforeSayingScrewThis this is specified in long and it is the magnitude of time
     *                                                     to wait for before saying <b>Naah.....I'm done waiting
     *                                                      for this task to complete and get me the data, let's
     *                                                      move on</b>
     * @param timeUnit Unit of time of the magnitude
     * @return the {@link Object} that we are waiting
     * @throws InterruptedException If the current running state of the thread is abruptly changed, then this
     * exception is thrown
     * @throws ExecutionException a type of runtime exception that gets thrown when the task that is executing,
     * throws any kind of exception inside itself.
     * @throws TimeoutException throws when the task doesn't complete in the specified magnitude of time and time unit
     */
    static Object getDataFromFutureButDontWaitForeverForIt(Future<?> futureContainingOurData,
                                                    long magnitudeOfTimeToWaitForBeforeSayingScrewThis,
                                                    TimeUnit timeUnit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return futureContainingOurData.get(magnitudeOfTimeToWaitForBeforeSayingScrewThis, timeUnit);
    }
}