package study.lab.multithreads.bulking;

public class Main {

    public static void main(String[] args) {
        Data data = new Data("data1.txt", "(empty)");
        new ChangerThread("changeThread", data).start();
        new SaverThread("saveThread", data).start();
    }
}
