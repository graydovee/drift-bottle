package cn.graydove.driftbottle.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.core.service.impl.DriftBottleServiceImpl;
import cn.graydove.driftbottle.utils.DataFormParseUtil;

/**
 * ���ݲ�ͬ������в�ͬ����
 * @author HUIHUI
 *
 */
public class Dispatcher implements Runnable{
	private Socket client;
	private Request request;
	private Response response;
	private DriftBottleService service;

	public Dispatcher(Socket client) throws IOException {
		this.client = client;
		this.request = new Request(client);
		this.response = new Response(client);
		service = new DriftBottleServiceImpl();
	}
	

	@Override
	public void run() {
		while(request.recive()) {
			if(request.getData()!=null) {
				//��������ж�
				if("login".equalsIgnoreCase(request.getData().getService())) {			//�����¼����
					if("element".equals(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz()) ){
						User user = DataFormParseUtil.Bean(request.getData());
						Object info = service.login(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("register".equalsIgnoreCase(request.getData().getService())){	//����ע������
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						int info = service.register(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("updpwd".equalsIgnoreCase(request.getData().getService())){	//�����޸���������
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						int info = service.updPwd(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("throw".equalsIgnoreCase(request.getData().getService())){		//��Ư��ƿ
					if("element".equalsIgnoreCase(request.getData().getType()) && Bottle.class.getName().equals(request.getData().getClazz())) {
						Bottle bottle = DataFormParseUtil.Bean(request.getData());
						int info = service.throwBottle(bottle);
						response.send(info);
					}else {
						error(1);
					}
				}else if("show".equalsIgnoreCase(request.getData().getService())){		//չʾ�ղص�Ư��ƿ
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						List<Bottle> info = service.showCollections(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("showthrow".equalsIgnoreCase(request.getData().getService())){		//չʾ�ղص�Ư��ƿ
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						List<Bottle> info = service.showMyThrow(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("pickup".equalsIgnoreCase(request.getData().getService())){		//��Ư��ƿ
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						Bottle info = service.pickUp(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("break".equalsIgnoreCase(request.getData().getService())){		//����Ư��ƿ
					if("element".equalsIgnoreCase(request.getData().getType()) && Bottle.class.getName().equals(request.getData().getClazz())) {
						Bottle bottle = DataFormParseUtil.Bean(request.getData());
						int info = service.breakBottle(bottle);
						response.send(info);
					}else {
						error(1);
					}
				}else if("upduser".equalsIgnoreCase(request.getData().getService())){	//�����޸���������
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						int info = service.updUser(user);
						response.send(info);
					}else {
						error(1);
					}
				}else{
					error(2);
				}
			}
		}
		close();
		System.out.println("�������");
	}
	
	/**
	 * ���ʹ�����Ϣ
	 * @param n �������
	 */
	private void error(int n) {
		switch(n) {
		case 1:
			System.out.println("������ƥ��");
			break;
		case 2:
			System.out.println("���޸÷���");
			break;
		}
		response.sendError();
	}
	
	/**
	 * �ر�����
	 */
	public void close() {
		response.close();
		request.close();
		try {
			if(client!=null) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
