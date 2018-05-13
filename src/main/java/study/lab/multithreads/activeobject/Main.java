package study.lab.multithreads.activeobject;

import study.lab.multithreads.activeobject.activeobject.ActiveObject;
import study.lab.multithreads.activeobject.activeobject.ActiveObjectFactory;

public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThread("Alice", activeObject).start();
        new MakeClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Charls", activeObject).start();
    }
}
