package cn.graydove.driftbottle.core.service.impl;

import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.dao.BottleDao;
import cn.graydove.driftbottle.core.dao.UserDao;
import cn.graydove.driftbottle.core.dao.impl.BottleDaoImpl;
import cn.graydove.driftbottle.core.dao.impl.UserDaoImpl;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.StringUtil;

public class DriftBottleServiceImpl implements DriftBottleService{
	BottleDao bottleDao;
	UserDao userDao;
	
	
	
	public DriftBottleServiceImpl() {
		userDao = new UserDaoImpl();
		bottleDao = new BottleDaoImpl();
	}
	

	@Override
	public User login(User user) {
		return userDao.selByUnameAndPwd(user);
	}

	@Override
	public int register(User user) {
		return userDao.insUser(user);
	}

	@Override
	public int updPwd(User user) {
		User oldUser = userDao.selByUid(user);
		int count = 0;
		if(oldUser != null) {
			oldUser.setPwd(user.getPwd());
			count = userDao.updUser(oldUser);
		}
		System.out.println(oldUser);
		return count;
	}

	@Override
	public int throwBottle(Bottle bottle) {
		User user = new User();
		user.setUid(bottle.getUid());
		user = userDao.selByUid(user);
		int count = 0;
		if(user!=null && user.getThrowTimes()>0) {
			System.out.println(bottle);
			Bottle b = bottleDao.selById(bottle);
			System.out.println(b);
			bottle.setState(0);
			if(b==null) {
				count =  bottleDao.insBottle(bottle);
				user.setThrowTimes(user.getThrowTimes()-1);
				userDao.updUser(user);
			}
			else {
				b.setState(0);
				b.setMessage(bottle.getMessage());
				count = bottleDao.updById(b);
			}
		}
		return count;
	}

	@Override
	public int breakBottle(Bottle bottle) {
		return bottleDao.delById(bottle);
	}

	@Override
	public Bottle pickUp(User user) {
		if(user!=null) {
			User u = userDao.selByUid(user);
			if(u!=null && u.getPickUpTimes()>0) {
				List<Bottle> list = bottleDao.selByState(0);
				int len = list.size();

				Bottle ret = null;
				if(len>0) {
					ret = list.get((int)(Math.random()*len));
					ret.setState(u.getUid());
					bottleDao.updById(ret);
					
					u.setPickUpTimes(u.getPickUpTimes()-1);
					userDao.updUser(u);
				}
				return ret;
			}
		}
		return null;
	}


	@Override
	public List<Bottle> showCollections(User user) {
		List<Bottle> list = bottleDao.selByState(user.getUid());
		return list;
	}


	@Override
	public int updPickUpAndThrowTime() {
		return userDao.updPickUpAndThrowTime();
	}


	@Override
	public int updUser(User user) {
		User oldUser = userDao.selByUid(user);
		int count = 0;
		if(oldUser != null) {
			if(!StringUtil.empty(user.getUname())) {
				oldUser.setUname(user.getUname());
			}
			if(user.getBirth()!=null) {
				oldUser.setBirth(user.getBirth());
			}
			oldUser.setSex(user.getSex());
			count = userDao.updUser(oldUser);
		}
		System.out.println(oldUser);
		return count;
	}


	@Override
	public List<Bottle> showMyThrow(User user) {
		return bottleDao.selByUid(user.getUid());
	}

}
