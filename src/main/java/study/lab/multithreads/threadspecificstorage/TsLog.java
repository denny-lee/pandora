package study.lab.multithreads.threadspecificstorage;

import java.io.*;

public class TsLog {

    private PrintWriter writer;

    public TsLog(String name) {
        try {
            writer = new PrintWriter(new FileWriter(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String content) {
        writer.println(content);
    }

    public void close() {
        writer.close();
    }
}
