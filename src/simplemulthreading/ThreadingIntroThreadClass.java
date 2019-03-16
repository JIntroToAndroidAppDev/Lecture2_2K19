package simplemulthreading;

public class ThreadingIntroThreadClass extends Thread {
    private String message;

    public ThreadingIntroThreadClass(String message) {
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
        for (int i = 0; i < 5; i++ ) {
            try {
                Thread.sleep(500);
                System.out.println(this.getName()
                        + " " +
                        this.message
                        + " " +
                        i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
