package study.lab.multithreads.threadspecificstorage;

public class ClientThread extends Thread {

    public ClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Start");
        for (int i = 0; i < 10; i++) {
            Log.println("---------- count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println(Thread.currentThread().getName() + "End");
    }
}
