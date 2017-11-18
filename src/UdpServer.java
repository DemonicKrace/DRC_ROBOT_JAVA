import java.io.*;
import java.net.*;

public class UdpServer {
	int port;    // �s����
	 
    public static void main(String args[]) throws Exception {
        UdpServer server = new UdpServer(5555); // �إ� UdpServer ���A������C
        server.run();                           // ����Ӧ��A���C
    }
 
    public UdpServer(int pPort) {
        port = pPort;                            // �]�w�s����C
    }
 
    public void run() throws Exception {
        final int SIZE = 8192;                    // �]�w�̤j���T���j�p�� 8192.
        byte buffer[] = new byte[SIZE];            // �]�w�T���Ȧs��
        for (int count = 1; ; count++) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(port);         // �]�w������ UDP Socket.
            socket.receive(packet);                                    // �����ʥ]�C
            String msg = new String(buffer, 0, packet.getLength());    // �N�����T���ഫ���r��C
            System.out.println(count + ". receive data = " + msg + "\n\n");        
            // �L�X�����쪺�T���C
            socket.close();                                            // ���� UDP Socket.
        }
    }
}
