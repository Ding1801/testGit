package GuiChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;
import java.lang.*;

import javax.management.RuntimeErrorException;
import javax.swing.*;

@SuppressWarnings("serial")
public class GuiChat extends JFrame {
	public GuiChat() {
		setUpUI();
		initSocket();
		setListener();
	}
	public static void main(String[] args) {
		new GuiChat();
	}
	private static final int DEFAULT_PORT=8899;
	//把主窗口分为三个部分 north cemter south 三个部分
	
	private JLabel stateLB;//显示监听状态
	private JTextArea centerTextArea;//显示聊天的记录
	private JPanel southPanel;//最下面的面板
	private JTextArea inputTextArea;//聊天输入框
	private JPanel bottomPanel;//放置IP输入框
	private JTextField ipTextField;//IP输入框
	private JTextField remotePort;//端口号输入框
	private JButton send;//发送按钮
	private JButton clear;//清除按钮
	private DatagramSocket datagramSocket;//后台功能的实现
	
	private void setUpUI() {
		setTitle("聊天软件");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		//窗口的center 部分
		stateLB = new JLabel("当前还未启动监听");
		stateLB.setHorizontalAlignment(JLabel.RIGHT);
		//窗口的NOTRTH 部分
		centerTextArea = new JTextArea();
		centerTextArea.setEditable(false);
		centerTextArea.setBackground(new Color(211, 211, 211));
		//窗口的south部分
		southPanel = new JPanel(new BorderLayout());
		inputTextArea = new JTextArea(5,20);
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ipTextField = new JTextField("127.0.0.1", 8);
		remotePort = new JTextField(String.valueOf(DEFAULT_PORT), 3);
		send =  new JButton("发送");
		clear = new JButton("清屏");
		bottomPanel.add(ipTextField);
		bottomPanel.add(remotePort);
		bottomPanel.add(send);
		bottomPanel.add(clear);
		southPanel.add(new JScrollPane(inputTextArea),BorderLayout.CENTER);
		southPanel.add(bottomPanel,BorderLayout.SOUTH);
		//添加窗口  部分组件
		add(stateLB, BorderLayout.NORTH);
		add(new JScrollPane(centerTextArea), BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setVisible(true);		
	}
	//监听的作用
	private void setListener(){
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//获取发送的目标IP地址和端口号
			final String ipAddress=ipTextField.getText();		
			final String remoteport = remotePort.getText();
			
			
			//判断IP地址和端口号是否为空
			if(ipAddress == null ||ipAddress.trim().equals("")|| remoteport == null
					||remoteport.trim().equals("")) {
				JOptionPane.showMessageDialog(GuiChat.this, "请输入IP地址和端口号");
				return ;
				
			}
			if(datagramSocket == null ||datagramSocket.isClosed()) {
				JOptionPane.showMessageDialog(GuiChat.this, "监听不成功");
			}
			//需要发送的内容
			String sendContent = inputTextArea.getText();
			byte[] buf = sendContent.getBytes();
			try {
				centerTextArea.append("我对"+ipAddress+"："+remoteport+"说"+inputTextArea.getText()+"\n\n");
				centerTextArea.setCaretPosition(centerTextArea.getText().length());
				datagramSocket.send(new DatagramPacket(buf, buf.length,InetAddress.getByName(ipAddress),Integer.parseInt(remoteport)));
				inputTextArea.setText("");
				
			}catch(Exception e2) {
				JOptionPane.showMessageDialog(GuiChat.this, "出错了，发送不成功");				
			}
			
		};
		});
		
	clear.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			centerTextArea.setText("");
			
		}
	});
	}
	//实现启动监听，并接收消息
	private void initSocket() {
		int port = DEFAULT_PORT;
		while(true) {
			try {
				if(datagramSocket != null && !datagramSocket.isClosed()) {
					datagramSocket.close();
				}
				try {
					port = Integer.parseInt(JOptionPane.showInputDialog(this, "请输入端口号","端口号",JOptionPane.QUESTION_MESSAGE));
					if(port<1 || port>65535) {
						throw new RuntimeErrorException(null, "端口号超出范围");						
					}							
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "你输入的端口号不正确，请重新输出1——65355");
					continue;
				}
				datagramSocket = new DatagramSocket(port);
				startListen();
				stateLB.setText("已在"+port+"端口监听");
				break;
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "端口已被占用，请重新设置端口");
				stateLB.setText("当前还未启用监听");
			}
		}
	}
	private void startListen() {
		// TODO Auto-generated method stub
		new Thread() {
			private DatagramPacket packet;
			public void run() {
				byte[] buf = new byte[1024];
				packet= new DatagramPacket(buf, buf.length);
				while(!datagramSocket.isClosed()) {
					try {
						datagramSocket.receive(packet);
						centerTextArea.append(packet.getAddress().getHostAddress()+":"+((InetSocketAddress)packet.getSocketAddress()).getPort()+"对我说：\n"+new String(packet.getData(),0,packet.getLength()));
						centerTextArea.setCaretPosition(centerTextArea.getText().length());
						
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
