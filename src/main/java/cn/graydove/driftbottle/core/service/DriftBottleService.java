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
	 * 用户登录
	 * @param user 包含用户登录时输入的信息
	 * @return 返回用户信息，返回null为登入失败
	 */
	User login(User user);
	
	/**
	 * 用户注册
	 * @param user 包含用户注册时输入的信息
	 * @return 注册成功返回1，否则返回0
	 */
	int register(User user);
	
	/**
	 * 修改密码
	 * @param user 包含用户uid,新密码等信息
	 * @return 修改成功返回1，否则返回0
	 */
	int updPwd(User user);
	
	/**
	 * 修改用户信息
	 * @param user 包含用户信息
	 * @return
	 */
	int updUser(User user);
	
	/**
	 * 扔漂流瓶
	 * @param bottle
	 * @return 扔的个数
	 */
	int throwBottle(Bottle bottle);
	
	/**
	 * 打破漂流瓶
	 * @param bottle 需要打破的漂流瓶
	 * @return 打破的个数
	 */
	int breakBottle(Bottle bottle);
	
	/**
	 * 捞漂流瓶
	 * @param user 捞漂流瓶的用户
	 * @return 捞起的漂流瓶
	 */
	Bottle pickUp(User user);
	
	/**
	 * 显示所有收藏的漂流瓶
	 * @param user 包含查询操作的用户的信息
	 * @return 所有的收藏
	 */
	List<Bottle> showCollections(User user);
	
	/**
	 * 显示扔出的漂流瓶
	 * @param user 包含查询操作的用户的信息
	 * @return 所有该用户扔出的漂流瓶
	 */
	List<Bottle> showMyThrow(User user);
	
	/**
	 * 重置所用用户扔瓶子与捞瓶子次数
	 * @return
	 */
	int updPickUpAndThrowTime();
}
