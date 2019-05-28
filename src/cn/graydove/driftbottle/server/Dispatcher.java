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
 * 根据不同请求进行不同处理
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
				//请求服务判断
				if("login".equalsIgnoreCase(request.getData().getService())) {			//处理登录请求
					if("element".equals(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz()) ){
						User user = DataFormParseUtil.Bean(request.getData());
						Object info = service.login(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("register".equalsIgnoreCase(request.getData().getService())){	//处理注册请求
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						int info = service.register(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("updpwd".equalsIgnoreCase(request.getData().getService())){	//处理修改密码请求
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						int info = service.updPwd(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("throw".equalsIgnoreCase(request.getData().getService())){		//扔漂流瓶
					if("element".equalsIgnoreCase(request.getData().getType()) && Bottle.class.getName().equals(request.getData().getClazz())) {
						Bottle bottle = DataFormParseUtil.Bean(request.getData());
						int info = service.throwBottle(bottle);
						response.send(info);
					}else {
						error(1);
					}
				}else if("show".equalsIgnoreCase(request.getData().getService())){		//展示收藏的漂流瓶
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						List<Bottle> info = service.showCollections(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("showthrow".equalsIgnoreCase(request.getData().getService())){		//展示收藏的漂流瓶
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						List<Bottle> info = service.showMyThrow(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("pickup".equalsIgnoreCase(request.getData().getService())){		//捞漂流瓶
					if("element".equalsIgnoreCase(request.getData().getType()) && User.class.getName().equals(request.getData().getClazz())) {
						User user = DataFormParseUtil.Bean(request.getData());
						Bottle info = service.pickUp(user);
						response.send(info);
					}else {
						error(1);
					}
				}else if("break".equalsIgnoreCase(request.getData().getService())){		//打破漂流瓶
					if("element".equalsIgnoreCase(request.getData().getType()) && Bottle.class.getName().equals(request.getData().getClazz())) {
						Bottle bottle = DataFormParseUtil.Bean(request.getData());
						int info = service.breakBottle(bottle);
						response.send(info);
					}else {
						error(1);
					}
				}else if("upduser".equalsIgnoreCase(request.getData().getService())){	//处理修改密码请求
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
		System.out.println("服务结束");
	}
	
	/**
	 * 发送错误信息
	 * @param n 错误编码
	 */
	private void error(int n) {
		switch(n) {
		case 1:
			System.out.println("参数不匹配");
			break;
		case 2:
			System.out.println("暂无该服务");
			break;
		}
		response.sendError();
	}
	
	/**
	 * 关闭连接
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
