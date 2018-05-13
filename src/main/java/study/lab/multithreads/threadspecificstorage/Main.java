package study.lab.multithreads.threadspecificstorage;

public class Main {
    public static void main(String[] args) {
        new ClientThread("Alice").start();
        new ClientThread("Danny").start();
        new ClientThread("Zoe").start();
    }
}
