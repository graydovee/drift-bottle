package cn.graydove.driftbottle.core.dao;

import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;

public interface BottleDao {
	/**
	 * ͨ��id����Ư��ƿ
	 * @param bollte ����Ҫ���ҵ�Ư��ƿid
	 * @return �ҵ���Ư��ƿ
	 */
	Bottle selById(Bottle bottle);
	
	/**
	 * ͨ��Ư��ƿ��״̬����Ư��ƿ
	 * @param i Ҫ���ҵ�Ư��ƿstateֵ
	 * @return �ҵ���Ư��ƿ
	 */
	List<Bottle> selByState(int i);
	
	/**
	 * ͨ��������״̬����Ư��ƿ
	 * @param ����Ҫ���ҵ�Ư��ƿuid��kinds
	 * @return
	 */
	List<Bottle> selByKindsAndUid(Bottle bottle);
	
	/**
	 * ͨ���ӳ��û�����Ư��ƿ
	 * @param ����Ҫ���ҵ�Ư��ƿuid
	 * @return
	 */
	List<Bottle> selByUid(int uid);
	
	/**
	 * ����һ��Ư��ƿ
	 * @param bottle ��������Ư��ƿ����Ϣ
	 * @return ����������
	 */
	int insBottle(Bottle bottle);
	
	/**
	 * ͨ��id����Ư��ƿ
	 * @param bottle ����Ҫ����Ư��ƿ��id���µ���Ϣ
	 * @return ���µ�����
	 */
	int updById(Bottle bottle);
	
	/**
	 * ͨ��idɾ��Ư��ƿ
	 * @param bottle ������Ҫɾ����Ư��ƿ��id
	 * @return ɾ��������
	 */
	int delById(Bottle bottle);
	
	
	
}
