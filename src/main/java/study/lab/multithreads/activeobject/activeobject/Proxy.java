package study.lab.multithreads.activeobject.activeobject;

class Proxy implements ActiveObject {

    private final SchedulerThread schedulerThread;
    private final Servent servent;

    public Proxy(SchedulerThread schedulerThread, Servent servent) {
        this.schedulerThread = schedulerThread;
        this.servent = servent;
    }

    public Result makeString(int count, char fillchar) {
        FutureResult future = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servent, future, count, fillchar));
        return future;
    }

    public void displayString(String content) {
        schedulerThread.invoke(new DisplayStringRequest(servent, content));
    }
}
