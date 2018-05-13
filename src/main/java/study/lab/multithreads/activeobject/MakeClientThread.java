package study.lab.multithreads.activeobject;

import study.lab.multithreads.activeobject.activeobject.ActiveObject;
import study.lab.multithreads.activeobject.activeobject.Result;

public class MakeClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakeClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            Result result = activeObject.makeString(i, fillchar);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = (String) result.getResultValue();
            System.out.println(Thread.currentThread().getName() + " value: " + s);
        }
    }
}
