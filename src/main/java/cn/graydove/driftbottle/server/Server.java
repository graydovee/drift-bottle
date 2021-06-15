package cn.graydove.driftbottle.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import cn.graydove.driftbottle.core.service.impl.DriftBottleServiceImpl;


/**
 * 
 * @author HUIHUI
 *
 */
public class Server extends Thread{
	private ServerSocket server;
	private Socket client;
	private boolean isRunning;
	
	public void load() {
		try {
			isRunning = true;
			server = new ServerSocket(18853);
			System.out.println("�����������ɹ����ȴ�����");
			recive();
			System.out.println("�������ر�");
		} catch (IOException e) {
			exit();
			e.printStackTrace();
		}
	}
	
	private void recive() throws IOException {
		while(isRunning) {
			try {
				client = server.accept();
				new Thread(new Dispatcher(client)).start();
				System.out.println("ipΪ"+client.getInetAddress().getHostAddress()+"�Ŀͻ��˽���������");
			} catch (Exception e) {
				
			}
		}
	}
	
	public void exit() {
		isRunning = false;
		try {
			if(server!=null) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		load();
	}
	
	public static void main(String[] args) {
		Server sv = new Server();
		Scanner in = new Scanner(System.in); 
		sv.start();
		String str;
		while(in.hasNext()) {
			str = in.nextLine();
			if("refresh".equalsIgnoreCase(str)) {
				int count = new DriftBottleServiceImpl().updPickUpAndThrowTime();
				System.out.println("�Ѹ���"+count+"���û�����Ư��ƿ����Ư��ƿ����");
			}else if("exit".equalsIgnoreCase(str)) {
				sv.exit();
				break;
			}
		}
		try {
			Thread.sleep(200);
			new Response(new Socket("localhost", 18853)).sendError();
		} catch (Exception e) {
			
		}
		in.close();
	}
	
}
