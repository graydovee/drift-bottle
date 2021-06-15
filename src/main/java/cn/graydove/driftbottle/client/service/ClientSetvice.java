package cn.graydove.driftbottle.client.service;

import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.DataFormParseUtil;

public class ClientSetvice implements DriftBottleService {
	Client client = null;

	@Override
	public User login(User user) {
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "login");
			dt = client.recive();
			System.out.println("recive-->"+dt);

			if("return".equalsIgnoreCase(dt.getService())) {
				return DataFormParseUtil.Bean(dt);
			}
			
		} catch (Exception e) {
			System.out.println("��¼����������");
		} finally {
			client.close();
		}
		return null;
	}

	@Override
	public int register(User user) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "register");
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
			System.out.println("ע������������");
		} finally {
			client.close();
		}
		return 0;
	}

	@Override
	public int updPwd(User user) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "updpwd");
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
			System.out.println("�޸���������������");
		} finally {
			client.close();
		}
		return 0;
	}
	
	@Override
	public int updUser(User user) {
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

	@Override
	public int throwBottle(Bottle bottle) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(bottle, "throw");
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
			System.out.println("����������");
		} finally {
			client.close();
		}
		
		return 0;
	}

	@Override
	public int breakBottle(Bottle bottle) {
		int count = 0;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(bottle, "break");
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
			System.out.println("����������");
		} finally {
			client.close();
		}
		return 0;
	}

	@Override
	public Bottle pickUp(User user) {
		Bottle bottle = null;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "pickup");
			dt = client.recive();
			System.out.println("recive-->"+dt);
			if("return".equalsIgnoreCase(dt.getService())) {
				try {
					bottle = DataFormParseUtil.Bean(dt);
				} catch (Exception e) {
					bottle = null;
					System.out.println("����ֵ���Ͳ�ƥ��");
				}
				return bottle;
			}
		} catch (Exception e) {
			System.out.println("����������");
		} finally {
			client.close();
		}
		return null;
	}

	@Override
	public List<Bottle> showCollections(User user) {
		List<Bottle> list = null;
		DataTrans dt = null;
		try {
			client = new Client();
			client.send(user, "show");
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

	@Override
	public int updPickUpAndThrowTime() {
		System.out.println("��Ȩ�޽��в���");
		return 0;
	}

	@Override
	public List<Bottle> showMyThrow(User user) {
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
