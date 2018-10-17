package tcp;
import java.net.*;
import java.io.*;

public class three {
	public static void main(String[] args) throws Exception {
		new TCPServer1().listen();
	}
}
class TCPServer1{
	private static final int PORT=7788;
	public void listen() throws Exception{
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true) {
			final Socket client= serverSocket.accept();
			new Thread() {
				public void run() {
					OutputStream oStream;
					try {
						oStream= client.getOutputStream();
						System.out.println("开始与客户端交流");
						oStream.write("欢迎你".getBytes());
						Thread.sleep(5000);
						System.out.println("与客户端结束交流");
						oStream.close();
						client.close();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				};
			}.start();
			
		}
		
		
		
	}
}
