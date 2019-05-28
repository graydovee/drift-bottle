package cn.graydove.driftbottle.client.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.utils.DataFormParseUtil;

public class Client {
	private Socket client = null;
	private OutputStream os = null;
	private InputStream is = null;
	
	public Client(){
		this("localhost", 8853);
	}
	
	public Client(String host){
		this(host, 8853);
	}
	
	public Client(String host, int post){
		try {
			this.client = new Socket(host, post);
			is = client.getInputStream();
			os = client.getOutputStream();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "����������ʧ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
		} 
	}
	
	public void send(Object obj, String service) {
		try {
			String msg = DataFormParseUtil.format(obj, service);
			System.out.println("��������-->\n\t"+msg);
			os.write(msg.getBytes());
			os.flush();
		} catch (Exception e) {
			System.out.println("��������ʧ��");
		} 
	}
	public DataTrans recive() {
		DataTrans dt = null;
		if(is==null) {
			return dt;
		}
		try {
			String info = "";
			//��ȡ�������Ϣ
			int buf_size = 1024*1024*8;
			byte[] datas = new byte[buf_size];
			int len = -1;
			if((len = is.read(datas))!=-1) {
				System.out.println("��ʼ��ȡ"+len);
				String str = new String(datas,0,len);
				if(str!=null) {
					info = info + str;
					System.out.println("һ�ζ�ȡ-->"+str);
				}
			}
			System.out.println("��ȡ����-->\n\t"+info);
			dt = DataFormParseUtil.parse(info);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ʧȥ���������Ӧ", "��ʾ",JOptionPane.PLAIN_MESSAGE);
		} 
		return dt;
	}
	
	public void close() {
		send(null, "close");
		try {
			if(is!=null) {
				is.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(os!=null) {
				os.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(client!=null) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
