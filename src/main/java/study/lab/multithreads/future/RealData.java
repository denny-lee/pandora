package study.lab.multithreads.future;

public class RealData implements Data {

    private final String content;

    public RealData(int count, char c) {
        System.out.println("making realdata" +count +" c "+c+" start");
        char[] buf = new char[count];
        for (int i = 0; i < count; i++) {
            buf[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.content = new String(buf);
        System.out.println("making realdata" +count +" c "+c+" end");
    }

    public String getContent() {
        return content;
    }
}
