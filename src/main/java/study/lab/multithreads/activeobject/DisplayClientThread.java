package study.lab.multithreads.activeobject;

import study.lab.multithreads.activeobject.activeobject.ActiveObject;

public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for(int i = 0; true; i++) {
            String string = Thread.currentThread().getName() + " " + i;
            activeObject.displayString(string);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
