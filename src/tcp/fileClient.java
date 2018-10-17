package tcp;
import java.net.*;
import java.io.*;

public class fileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 10001);
		OutputStream outputStream = socket.getOutputStream();
		//创建FileinputStream;
		FileInputStream fileInputStream = new FileInputStream("F://1.jpg");
		byte[] buf=new byte[1024];
		int len;
		while((len=fileInputStream.read(buf))!= -1) {
			outputStream.write(buf,0,len);
		}
		socket.shutdownOutput();
		InputStream inputStream = socket.getInputStream();
		byte[] bufMsg = new byte[1024];
		int num=inputStream.read(bufMsg);
		String mString=new String(bufMsg,0,num);
		System.out.println(mString);
		fileInputStream.close();
		socket.close();
		
		
	}
}
