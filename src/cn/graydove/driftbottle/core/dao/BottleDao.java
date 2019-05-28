package cn.graydove.driftbottle.core.dao;

import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;

public interface BottleDao {
	/**
	 * 通过id查找漂流瓶
	 * @param bollte 包含要查找的漂流瓶id
	 * @return 找到的漂流瓶
	 */
	Bottle selById(Bottle bottle);
	
	/**
	 * 通过漂流瓶的状态查找漂流瓶
	 * @param i 要查找的漂流瓶state值
	 * @return 找到的漂流瓶
	 */
	List<Bottle> selByState(int i);
	
	/**
	 * 通过种类与状态查找漂流瓶
	 * @param 包含要查找的漂流瓶uid与kinds
	 * @return
	 */
	List<Bottle> selByKindsAndUid(Bottle bottle);
	
	/**
	 * 通过扔出用户查找漂流瓶
	 * @param 包含要查找的漂流瓶uid
	 * @return
	 */
	List<Bottle> selByUid(int uid);
	
	/**
	 * 新增一个漂流瓶
	 * @param bottle 包含新增漂流瓶的信息
	 * @return 新增的数量
	 */
	int insBottle(Bottle bottle);
	
	/**
	 * 通过id更新漂流瓶
	 * @param bottle 包含要更新漂流瓶的id与新的信息
	 * @return 更新的数量
	 */
	int updById(Bottle bottle);
	
	/**
	 * 通过id删除漂流瓶
	 * @param bottle 包含需要删除的漂流瓶的id
	 * @return 删除的数量
	 */
	int delById(Bottle bottle);
	
	
	
}
