package study.lab.multithreads.activeobject.activeobject;

class DisplayStringRequest extends MethodRequest {

    private final String content;

    public DisplayStringRequest(Servent servent, String content) {
        super(servent, null);
        this.content = content;
    }

    @Override
    public void execute() {
        servent.displayString(content);
    }
}
