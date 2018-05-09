package study.lab.multitheads.prodconsum;

import java.util.Random;

public class MakerThread extends Thread {
    private final Table t;
    private final Random random;
    private static int id = 0;

    public MakerThread(String name, Table t, int seed) {
        super(name);
        this.t = t;
        random = new Random(seed);
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                t.put("Cake" + netInt() + " By["+getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static synchronized int netInt() {
        return id++;
    }
}
