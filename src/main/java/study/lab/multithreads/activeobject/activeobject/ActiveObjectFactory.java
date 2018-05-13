package study.lab.multithreads.activeobject.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        Servent servent = new Servent();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread scheduler = new SchedulerThread(queue);
        Proxy proxy = new Proxy(scheduler, servent);
        scheduler.start();
        return proxy;
    }
}
