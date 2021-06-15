package cn.graydove.driftbottle.core.dao;

import cn.graydove.driftbottle.core.bean.User;

/**
 * 查询数据库的接口
 * @author HUIHUI
 *
 */
public interface UserDao {
	/**
	 * 新增一个用户  
	 * @param user 新用户的信息
	 * @return sql命令返回值
	 */
	int insUser(User user);
	
	/**
	 * 通过用户名密码查找用户
	 * @param user 包含用户名密码
	 * @return 查询结果
	 */
	User selByUnameAndPwd(User user);
	
	/**
	 * 通过uid查找用户
	 * @param user 包含用户uid
	 * @return 查询结果
	 */
	User selByUid(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int updUser(User user);
	
	/**
	 * 重置所有用户的捞瓶与扔瓶次数
	 * @return
	 */
	int updPickUpAndThrowTime();
	
}
