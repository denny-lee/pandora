package study.lab.multithreads.activeobject.activeobject;

class RealResult extends Result {
    private final Object resultValue;
    public RealResult(Object s) {
        this.resultValue = s;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
