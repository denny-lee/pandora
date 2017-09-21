package study.lab.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-context-dubbo.xml"});
        context.start();
        System.in.read(); // press any key to exit
    }
}
