import java.util.concurrent.Callable;

public class UtilityClass {

    public static Runnable createDummyTaskWithLoopCount (int loopCount) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < loopCount; i++) {
                    try {
                        Thread.sleep(i * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is running " + i);
                }
            }
        };
    }

    public static <T extends Number> Callable<T> createTaskThatReturnsSomeData (T dataToReturn) {
        return new Callable<T>() {
            @Override
            public T call() throws InterruptedException {
                Thread.sleep(500);
                return dataToReturn;
            }
        };
    }
}