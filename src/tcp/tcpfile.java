package tcp;
import java.io.*;
import java.net.*;



public class tcpfile {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(10001);
		while(true) {
			//调用accept 方法接收客户端请求，得到Socket对象
			Socket socket = serverSocket.accept();
			//每当和客户建立Socket联系的时候，单独开启一个线程处理和客户端的交互
			new Thread(new ServerThread(socket)).start();
		}
	}
}

class ServerThread implements Runnable{
	private Socket socket;
	public ServerThread(Socket socket) {
		this.socket=socket;
	}
	public void run() {
		String ip= socket.getInetAddress().getHostAddress();
		int count = 1;
		try {
			InputStream inputStream = socket.getInputStream();
			File parentFile = new File("D:\\upload\\");
			if(!parentFile.exists()) {
				parentFile.mkdirs();
			}
			//把客户端的ip地址作为上传文件的文件名
			File file = new File(parentFile,ip+"("+count+").jpg");
			while(file.exists()) {
				//如果文件名存在 则把count++；
				 file = new File(parentFile,ip+"("+(count++)+").jpg");
			}
			//创建FileOutputStream对象
			FileOutputStream fileOutputStream= new FileOutputStream(file);
			byte[] buf= new byte[1024];
			int len = 0;
			while((len=inputStream.read(buf))!= -1) {
				fileOutputStream.write(buf, 0, len);
			}
			OutputStream outputStream= socket.getOutputStream();
			outputStream.write("上传成功".getBytes());
			fileOutputStream.close();
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
