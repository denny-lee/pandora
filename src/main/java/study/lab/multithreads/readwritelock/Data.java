package study.lab.multithreads.readwritelock;

public class Data {

    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnLock();
        }
    }

    private char[] doRead() throws InterruptedException {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < newBuf.length; i++) {
            newBuf[i] = buffer[i];
        }
        slowly();
        return newBuf;
    }

    private void doWrite(char c) throws InterruptedException {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() throws InterruptedException {
        Thread.sleep(50);
    }
}
