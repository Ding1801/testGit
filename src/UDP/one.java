package UDP;
import java.io.IOException;
import java.net.*;
public class one {
	public static void main(String[] args) throws IOException {
		byte[] buf=new byte[1024];
		DatagramSocket datagramSocket=new DatagramSocket(8954);
		DatagramPacket datagramPacket=new DatagramPacket(buf, 1024);
		System.out.println("等待接收数据");
		datagramSocket.receive(datagramPacket);
		String str = new String(datagramPacket.getData(),0, datagramPacket.getLength())+"from"+datagramPacket.getAddress().getHostAddress()+":"+datagramPacket.getPort();
		
		System.out.println(str);
		datagramSocket.close();
	}
}
