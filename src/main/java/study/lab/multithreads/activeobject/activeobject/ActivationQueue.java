package study.lab.multithreads.activeobject.activeobject;

class ActivationQueue {

    private static final int MAX_QUEUE = 100;
    private final MethodRequest[] requestQueue;
    private int tail;
    private int head;
    private int count;

    public ActivationQueue() {
        this.requestQueue = new MethodRequest[MAX_QUEUE];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }

    public synchronized MethodRequest takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
}
