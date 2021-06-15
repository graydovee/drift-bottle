package cn.graydove.driftbottle.core.dao;

import cn.graydove.driftbottle.core.bean.User;

/**
 * ��ѯ���ݿ�Ľӿ�
 * @author HUIHUI
 *
 */
public interface UserDao {
	/**
	 * ����һ���û�  
	 * @param user ���û�����Ϣ
	 * @return sql�����ֵ
	 */
	int insUser(User user);
	
	/**
	 * ͨ���û�����������û�
	 * @param user �����û�������
	 * @return ��ѯ���
	 */
	User selByUnameAndPwd(User user);
	
	/**
	 * ͨ��uid�����û�
	 * @param user �����û�uid
	 * @return ��ѯ���
	 */
	User selByUid(User user);
	
	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return
	 */
	int updUser(User user);
	
	/**
	 * ���������û�����ƿ����ƿ����
	 * @return
	 */
	int updPickUpAndThrowTime();
	
}
