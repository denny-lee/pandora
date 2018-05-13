package study.lab.multithreads.threadspecificstorage;

public class Log {

    private static final ThreadLocal<TsLog> threadLocal = new ThreadLocal<TsLog>();

    public static void println(String content) {
        getLog().println(content);
    }

    public static void close() {
        getLog().close();
    }

    private static TsLog getLog() {
        TsLog tsLog = threadLocal.get();
        if (null == tsLog) {
            tsLog = new TsLog(Thread.currentThread().getName() + ".log");
            threadLocal.set(tsLog);
        }
        return tsLog;
    }
}
