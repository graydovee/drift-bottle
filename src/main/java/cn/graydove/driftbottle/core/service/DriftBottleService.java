package cn.graydove.driftbottle.core.service;

import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.User;

/**
 * 
 * @author HUIHUI
 *
 */
public interface DriftBottleService {
	/**
	 * �û���¼
	 * @param user �����û���¼ʱ�������Ϣ
	 * @return �����û���Ϣ������nullΪ����ʧ��
	 */
	User login(User user);
	
	/**
	 * �û�ע��
	 * @param user �����û�ע��ʱ�������Ϣ
	 * @return ע��ɹ�����1�����򷵻�0
	 */
	int register(User user);
	
	/**
	 * �޸�����
	 * @param user �����û�uid,���������Ϣ
	 * @return �޸ĳɹ�����1�����򷵻�0
	 */
	int updPwd(User user);
	
	/**
	 * �޸��û���Ϣ
	 * @param user �����û���Ϣ
	 * @return
	 */
	int updUser(User user);
	
	/**
	 * ��Ư��ƿ
	 * @param bottle
	 * @return �ӵĸ���
	 */
	int throwBottle(Bottle bottle);
	
	/**
	 * ����Ư��ƿ
	 * @param bottle ��Ҫ���Ƶ�Ư��ƿ
	 * @return ���Ƶĸ���
	 */
	int breakBottle(Bottle bottle);
	
	/**
	 * ��Ư��ƿ
	 * @param user ��Ư��ƿ���û�
	 * @return �����Ư��ƿ
	 */
	Bottle pickUp(User user);
	
	/**
	 * ��ʾ�����ղص�Ư��ƿ
	 * @param user ������ѯ�������û�����Ϣ
	 * @return ���е��ղ�
	 */
	List<Bottle> showCollections(User user);
	
	/**
	 * ��ʾ�ӳ���Ư��ƿ
	 * @param user ������ѯ�������û�����Ϣ
	 * @return ���и��û��ӳ���Ư��ƿ
	 */
	List<Bottle> showMyThrow(User user);
	
	/**
	 * ���������û���ƿ������ƿ�Ӵ���
	 * @return
	 */
	int updPickUpAndThrowTime();
}
