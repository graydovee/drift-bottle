package cn.graydove.driftbottle.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.utils.DataFormParseUtil;

/**
 * 
 * @author HUIHUI
 *
 */
@SuppressWarnings("all")
public class Request {
	private DataTrans data;
	private InputStream is;
	private boolean running;
	private int timeOut;
	private long time;
	
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
	public DataTrans getData() {
		return data;
	}
	public void setData(DataTrans data) {
		this.data = data;
	}
	

	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public boolean isRunning() {
		return running;
	}
	public Request(InputStream is) {
		this.is = is;
		this.running = true;
		this.time = System.currentTimeMillis();
		this.timeOut = 10000;
	}
	
	public Request(Socket client) throws IOException {
		this(client.getInputStream());
	}
	
	public boolean recive(){
		String info = "";
		//读取到达的信息
		int buf_size = 1024*1024*8;
		byte[] datas = new byte[buf_size];
		int len = -1;
		try {
			if((len = is.read(datas))!=-1) {
				String str = new String(datas,0,len);
				if(str!=null) {
					info = info + str;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("收到数据info-->\n\t" + info);
		//信息处理
		this.data = DataFormParseUtil.parse(info);
		System.out.println("解析后数据data-->\n\t" +DataFormParseUtil.parse(info));
		
		if(data!=null && "close".equalsIgnoreCase(data.getService())){
			running = false;
		}
		else if(System.currentTimeMillis()>time+timeOut) {
			running = false;
		}
		return running;
	}
	
	public void close() {
		try {
			if(is!=null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
