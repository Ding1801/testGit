package UDP;
import java.io.IOException;
import java.net.*;

public class two {
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket=new DatagramSocket(3000);
		String string = "hello world!";
		DatagramPacket datagramPacket= new DatagramPacket(string.getBytes(), string.length(), InetAddress.getByName("localhost"), 8954);
		
		System.out.println("发送数据");
		datagramSocket.send(datagramPacket);
		datagramSocket.close();
	}
}
