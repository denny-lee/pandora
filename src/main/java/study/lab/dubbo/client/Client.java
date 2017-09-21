package study.lab.dubbo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.lab.dubbo.server.DemoService;

public class Client {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // obtain proxy object for remote invocation
        String hello = demoService.sayHello("world"); // execute remote invocation
        System.out.println(hello); // show the result
        System.in.read();
    }

}
