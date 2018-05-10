package study.lab.multithreads.prodconsum;

public class Table {

    private final String[] dish;
    private int head;
    private int tail;

    private int count;

    public Table(int num) {
        dish = new String[num];
        head = 0;
        tail = 0;
        count = 0;
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println("Thread "+ Thread.currentThread().getName()+ " puts "+cake+" count="+count);
        while (count >= dish.length) {
            wait();
        }
        dish[tail] = cake;
//        System.out.println("Thread "+ Thread.currentThread().getName()+ " puts "+cake+" count="+count);
        tail = (tail + 1) % dish.length;
        count++;
        notifyAll();
    }
    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        String cake = dish[head];
        head = (head + 1) % dish.length;
        count--;
        notifyAll();
        System.out.println("Thread "+Thread.currentThread().getName() + " takes "+ cake+" count="+count);
        return cake;
    }
}
