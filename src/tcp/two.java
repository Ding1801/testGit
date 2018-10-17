package tcp;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class two {
	public static void main(String[] args) throws Exception {
		new TCPClient().connent();
	}
}

class TCPClient{
	private static final int Port = 7788;
	public void connent() throws Exception{
		Socket client =  new Socket(InetAddress.getLocalHost(), Port);
		InputStream iStream = client.getInputStream();
		byte[] buf= new byte[1024];
		int len = iStream.read(buf);
		System.out.println(new String(buf, 0 , len));
		client.close();
	}
}