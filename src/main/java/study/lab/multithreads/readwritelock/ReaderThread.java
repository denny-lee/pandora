package study.lab.multithreads.readwritelock;

public class ReaderThread extends Thread {

    private final Data data;

    public ReaderThread(Data data) {
        this.data = data;
    }
    @Override
    public void run() {
        while (true) {
            try {
                char[] buf = data.read();
                System.out.println(Thread.currentThread().getName() + " read " + String.valueOf(buf));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
