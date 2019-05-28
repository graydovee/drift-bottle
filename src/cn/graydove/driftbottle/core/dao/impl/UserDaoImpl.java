package cn.graydove.driftbottle.core.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.dao.UserDao;

public class UserDaoImpl implements UserDao{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null; 
	public void getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/java?useSSL=false", "root", "password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int insUser(User user) {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("insert into t_user values(default,?,?,?,?,default,default)");
			ps.setObject(1, user.getUname());
			ps.setObject(2, user.getPwd());
			ps.setObject(3, user.getSex());
			ps.setObject(4, user.getBirth());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}

	@Override
	public User selByUnameAndPwd(User user) {
		User ret = null;
		try {
			getConn();
			ps = conn.prepareStatement("select * from t_user where uname=? and pwd=?");
			ps.setObject(1, user.getUname());
			ps.setObject(2, user.getPwd());
			rs = ps.executeQuery();
			while(rs.next()) {
				ret = new User();
				ret.setUid(rs.getInt("uid"));
				ret.setUname(rs.getString("uname"));
				ret.setPwd(rs.getString("pwd"));
				ret.setSex(rs.getInt("sex"));
				ret.setBirth(rs.getDate("birth"));
				ret.setPickUpTimes(rs.getInt("pick_up_times"));
				ret.setThrowTimes(rs.getInt("throw_times"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		
		return ret;
	}

	@Override
	public int updUser(User user) {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("update t_user set uname=?,pwd=?,sex=?,birth=?,pick_up_times=?,throw_times=? where uid=?");
			ps.setObject(1, user.getUname());
			ps.setObject(2, user.getPwd());
			ps.setObject(3, user.getSex());
			ps.setObject(4, user.getBirth());
			ps.setObject(5, user.getPickUpTimes());
			ps.setObject(6, user.getThrowTimes());
			ps.setObject(7, user.getUid());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}
	
	@Override
	public User selByUid(User user) {
		User ret = null;
		try {
			getConn();
			ps = conn.prepareStatement("select * from t_user where uid=?");
			ps.setObject(1, user.getUid());
			rs = ps.executeQuery();
			while(rs.next()) {
				ret = new User();
				ret.setUid(rs.getInt("uid"));
				ret.setUname(rs.getString("uname"));
				ret.setPwd(rs.getString("pwd"));
				ret.setSex(rs.getInt("sex"));
				ret.setBirth(rs.getDate("birth"));
				ret.setPickUpTimes(rs.getInt("pick_up_times"));
				ret.setThrowTimes(rs.getInt("throw_times"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return ret;
	}
	
	public void release() {
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updPickUpAndThrowTime() {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("update t_user set pick_up_times=default,throw_times=default");
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}

	

}
