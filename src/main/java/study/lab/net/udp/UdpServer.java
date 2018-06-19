package study.lab.net.udp;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Liw
 * @description :
 * @date : 2018/4/23 12:19
 */
public class UdpServer {

    // 定义一些常量
    private final int MAX_LENGTH = 1024 * 10; // 最大接收字节长度
    private final int PORT_NUM   = 9527;   // port号
    // 用以存放接收数据的字节数组
    private byte[] buf = new byte[MAX_LENGTH];
    // 数据报套接字
    private DatagramSocket datagramSocket;
    // 用以接收数据报
    private DatagramPacket datagramPacket;

    private Map<String, Integer> ipMap = new HashMap<String, Integer>();

    /**
     * 1,连接成功，保存下端口
     * 2，触发后，向对应端发消息，内容：appName
     * 3, 接收统一处理所有端的消息，内容：appName, 文件文本map中，未结束加[loading]
     *
     */

    public UdpServer(){
        try {
            datagramSocket = new DatagramSocket(PORT_NUM);
            datagramPacket = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(datagramPacket);

            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            System.out.println("Server Rece:" + receStr);
            System.out.println("Server Port:" + datagramPacket.getPort());
            System.out.println("Server Port:" + datagramPacket.getAddress().getHostAddress());
            InetAddress add = InetAddress.getByName("172.18.1.91");
            ipMap.put(datagramPacket.getAddress().getHostAddress(),  datagramPacket.getPort());

            byte[] buf = "I receive the message".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, add, datagramPacket.getPort());
            datagramSocket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        new UdpServer();
    }

}
