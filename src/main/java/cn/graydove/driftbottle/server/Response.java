package cn.graydove.driftbottle.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import cn.graydove.driftbottle.utils.DataFormParseUtil;

/**
 * 
 * @author HUIHUI
 *
 */
public class Response {
	private OutputStream os;

	public Response(Socket client) throws IOException {
		this(client.getOutputStream());
	}

	public Response(OutputStream os) {
		this.os = os;
	}
	
	public void send(Object info) {
		try {
			String msg = DataFormParseUtil.format(info, "return");
			os.write(msg.getBytes());
			System.out.println("发送数据msg\n\t-->"+msg);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void sendError() {
		try {
			String msg = DataFormParseUtil.format(null, "error");
			os.write(msg.getBytes());
			System.out.println("发送数据msg\n\t-->"+msg);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void close() {
		try {
			if(os!=null) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
