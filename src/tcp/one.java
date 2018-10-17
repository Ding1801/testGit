package tcp;
import java.net.*;
import java.io.*;

public class one {
	public static void main(String[] args) throws Exception {
		new TCPServer().listen();
	}
}
class TCPServer{
	private static final int PORT=7788;
	public void listen() throws Exception{
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket = serverSocket.accept();
		OutputStream oStream = socket.getOutputStream();//获取客户端的输出流
		System.out.println("开始与客户端交流数据");
		oStream.write("我们是个班级的同学".getBytes());
		Thread.sleep(5000);
		System.out.println("结束与客户端的数据交流");
		oStream.close();
		socket.close();
		
	}
}
