package cn.graydove.driftbottle.client.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.utils.DataFormParseUtil;

/**
 * 
 * @author HUIHUI
 *
 */
@SuppressWarnings("unused")
public class TestClientService {
	private static Client client;
	
	public static void main(String[] args) throws ParseException {
		//ģ���¼
//		User user = new User();
//		user.setUname("����");
//		user.setPwd("123");
//		User u = login(user);
//		if(u==null || u.getUid()==0) {
//			System.out.println("��¼ʧ��");
//		}else {
//			System.out.println("��¼�ɹ�");
//			System.out.println(u);
//		}
		//ģ��ע��
//		User user = new User();
//		user.setUname("����");
//		user.setPwd("aaa");
//		user.setSex(0);
//		user.setBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-11-11").getTime()));
//		int count = register(user);
//		if(count == 1) {
//			System.out.println("ע��ɹ�");
//		}else {
//			System.out.println("ע��ʧ��");
//		}
		//ģ���޸�����
//		User user = new User();
//		user.setUid(5);
//		user.setPwd("147");
//		int count = updPwd(user);
//		if(count == 1) {
//			System.out.println("�޸ĳɹ�");
//		}else {
//			System.out.println("�޸�ʧ��");
//		}
		//ģ���ӳ�Ư��ƿ
//		Bottle bottle = new Bottle();
//		bottle.setMessage("�°��һ��Ư��ƿ");
//		bottle.setKinds(0);
//		bottle.setState(3);
//		bottle.setUid(4);
//		int count = throwBottle(bottle);
//		if(count == 1) {
//			System.out.println("�ɹ��ӳ�");
//		}else {
//			System.out.println("�ӳ�ʧ��");
//		}
//		//��ѯ�ղ�
//		User u = new User();
//		u.setUid(1);
//		List<Bottle> list = showCollection(u);
//		if(list!=null) {
//			System.out.println("��ѯ�ɹ�");
//			for(Bottle b:list) {
//				System.out.println(b.getKinds() + "\t" + b.getMessage());
////				//����Ư��ƿ
////				breakBottle(b);
//			}
//		}else {
//			System.out.println("��ѯʧ��");
//		}
//		//��Ư��ƿ
//		User u = new User();
//		u.setUid(5);
//		Bottle bottle = pickUpBottle(u);
//		if(bottle != null) {
//			System.out.println(bottle);
//			//throwBottle(bottle);
//		}else {
//			System.out.println("��ƿ��ʧ��");
//		}
//		//����Ư��ƿ
//		User u = new User();
//		u.setUid(12);
//		u.setSex(1);
//		u.setUname("С��");
//		int count = updUser(u);
//		System.out.println(count);
		User u = new User();
		u.setUid(1);
		List<Bottle> list = showMyThrow(u);
		if(list!=null) {
			System.out.println("��ѯ�ɹ�");
			for(Bottle b:list) {
				System.out.println(b.getKinds() + "\t" + b.getMessage());
//				//����Ư��ƿ
//				breakBottle(b);
			}
		}else {
			System.out.println("��ѯʧ��");
		}
	}
	public static User login(User user) {
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "login");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			return DataFormParseUtil.Bean(dt);
		}else {
			return null;
		}
	}
	
	public static Integer register(User user) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "register");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				count = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				count = 0;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return count;
		}else {
			return 0;
		}
	}
	
	public static int updPwd(User user) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "updpwd");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				count = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				count = 0;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return count;
		}else {
			return 0;
		}
	}
	
	public static int throwBottle(Bottle bottle) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(bottle, "throw");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				count = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				count = 0;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return count;
		}else {
			return 0;
		}
	}
	
	public static List<Bottle> showCollection(User user) {
		List<Bottle> list = null;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "show");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				list = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				list = null;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return list;
		}else {
			return null;
		}
	}
	
	public static int breakBottle(Bottle bottle) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(bottle, "break");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				count = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				count = 0;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return count;
		}else {
			return 0;
		}
	}
	
	public static Bottle pickUpBottle(User user) {
		Bottle bottle = null;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "pickup");
			dt = client.recive();
			System.out.println("recive-->"+dt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		if("return".equalsIgnoreCase(dt.getService())) {
			try {
				bottle = DataFormParseUtil.Bean(dt);
			} catch (Exception e) {
				bottle = null;
				System.out.println("����ֵ���Ͳ�ƥ��");
			}
			return bottle;
		}else {
			return null;
		}
	}
	public static int updUser(User user) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "updUser");
			dt = client.recive();
			System.out.println("recive-->"+dt);
			if("return".equalsIgnoreCase(dt.getService())) {
				try {
					count = DataFormParseUtil.Bean(dt);
				} catch (Exception e) {
					count = 0;
					System.out.println("����ֵ���Ͳ�ƥ��");
				}
				return count;
			}
		} catch (Exception e) {
			System.out.println("�޸���Ϣ����������");
		} finally {
			client.close();
		}
		return 0;
	}
	
	public static List<Bottle> showMyThrow(User user) {
		List<Bottle> list = null;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "showthrow");
			dt = client.recive();
			System.out.println("recive-->"+dt);
			if("return".equalsIgnoreCase(dt.getService())) {
				try {
					list = DataFormParseUtil.Bean(dt);
				} catch (Exception e) {
					list = null;
					System.out.println("����ֵ���Ͳ�ƥ��");
				}
				return list;
			}
		} catch (Exception e) {
			System.out.println("����������");
		} finally {
			client.close();
		}
		return null;
	}
}
