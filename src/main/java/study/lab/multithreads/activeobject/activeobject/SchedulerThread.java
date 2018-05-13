package study.lab.multithreads.activeobject.activeobject;

class SchedulerThread extends Thread {
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }
    @Override
    public void run() {
        while (true) {
            queue.takeRequest().execute();
        }
    }
}
