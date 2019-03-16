package simplemulthreading;

public class ThreadingIntroRunnableInterface implements
Runnable {

    private String message;

    public ThreadingIntroRunnableInterface(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(this.message + " " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
