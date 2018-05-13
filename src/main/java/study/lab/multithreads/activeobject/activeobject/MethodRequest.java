package study.lab.multithreads.activeobject.activeobject;

abstract class MethodRequest {

    protected final Servent servent;
    protected final FutureResult future;

    protected MethodRequest(Servent servent, FutureResult future) {
        this.servent = servent;
        this.future = future;
    }

    public abstract void execute();
}
