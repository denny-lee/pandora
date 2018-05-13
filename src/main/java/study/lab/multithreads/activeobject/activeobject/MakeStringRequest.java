package study.lab.multithreads.activeobject.activeobject;

class MakeStringRequest extends MethodRequest {

    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servent servent, FutureResult future, int count, char fillchar) {
        super(servent, future);
        this.count = count;
        this.fillchar = fillchar;
    }

    @Override
    public void execute() {
        Result result = servent.makeString(count, fillchar);
        future.setResult(result);
    }
}
