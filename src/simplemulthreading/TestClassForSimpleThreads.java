package simplemulthreading;

public class TestClassForSimpleThreads {
    public static void main(String[] args) {
/*
        ThreadingIntroThreadClass threadClass1 =
                new ThreadingIntroThreadClass("Thread Class message");
*/
  ThreadingIntroThreadClass[] threadClassArray = new ThreadingIntroThreadClass[Runtime
          .getRuntime()
          .availableProcessors()];

      for (int i = 0 ; i < threadClassArray.length; i++) {
          threadClassArray[i] = new ThreadingIntroThreadClass("Class array Index" + i);
      }

    for (int i = 0 ; i < threadClassArray.length; i++) {
        threadClassArray[i].start();
    }

//        threadClass1.start();

      /*  ThreadingIntroRunnableInterface runnableInterface1 =
                new ThreadingIntroRunnableInterface("Runnable message");

        Thread threadRunningRunnable = new Thread(runnableInterface1);
        threadRunningRunnable.start();*/
    }
}
