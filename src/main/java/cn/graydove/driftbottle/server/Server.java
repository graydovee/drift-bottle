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
			System.out.println("服务器启动成功，等待请求");
			recive();
			System.out.println("服务器关闭");
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
				System.out.println("ip为"+client.getInetAddress().getHostAddress()+"的客户端建立了连接");
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
				System.out.println("已更新"+count+"个用户的捞漂流瓶与扔漂流瓶次数");
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
