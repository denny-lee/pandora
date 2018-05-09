package study.lab.multitheads.prodconsum;

import java.util.Random;

public class EaterThread extends Thread {

    private final Table t;
    private final Random random;
    public EaterThread(String name, Table t, int seed) {
        super(name);
        this.t = t;
        random = new Random(seed);
    }
    @Override
    public void run() {
        while (true) {
            try {
                t.take();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
