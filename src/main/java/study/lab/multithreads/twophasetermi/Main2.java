package study.lab.multithreads.twophasetermi;

public class Main2 {

    public static void main(String[] args) {
        System.out.println("main BEGIN");

        Runtime.getRuntime().addShutdownHook(
            new Thread(){
                @Override
                public void run() {
                    System.out.println("*********");
                    System.out.println(Thread.currentThread().getName() + "shutdown");
                    System.out.println("*********");
                }
            }
        );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main Exit" + Thread.currentThread().getName());
        System.exit(0);
        System.out.println("main End. you can not see me");
    }
}
