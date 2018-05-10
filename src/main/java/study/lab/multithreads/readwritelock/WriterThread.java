package study.lab.multithreads.readwritelock;

import java.util.Random;

public class WriterThread extends Thread {

    private static final Random random = new Random();
    private final Data data;
    private final String filler;
    private int index;

    public WriterThread(Data data, String filler) {
        this.data = data;
        this.filler = filler;
        index = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                data.write(nextChar());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
