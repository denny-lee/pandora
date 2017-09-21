package study.lab.dubbo.server;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String str) {
        System.out.println(str);
        return "Hello" + str;
    }
}
