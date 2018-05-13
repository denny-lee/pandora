package study.lab.multithreads.twophasetermi;

public class CountupThread extends Thread {

    private long counter = 0L;
    private volatile boolean shutdownRequested = false;

    public void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public void run() {
        try {
            while (!shutdownRequested) {
                doWork();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            doShutdown();
        }

    }

    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork counter:" + counter);
        Thread.sleep(500);
    }

    private void doShutdown() {
        System.out.println("doShutdown counter:" + counter);

    }
}
