package cn.graydove.driftbottle.core.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.dao.BottleDao;

public class BottleDaoImpl implements BottleDao {

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
	public Bottle selById(Bottle bottle) {
		Bottle ret = null;
		if(bottle==null) {
			return null;
		}
		try {
			getConn();
			ps = conn.prepareStatement("select * from bottle where id=?");
			ps.setObject(1, bottle.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				ret = new Bottle();
				ret.setId(rs.getInt(1));
				ret.setMessage(rs.getString(2));
				ret.setKinds(rs.getInt(3));
				ret.setState(rs.getInt(4));
				ret.setCreateTime(rs.getDate(5));
				ret.setUid(rs.getInt(6));
				ret.setUpdateTime(rs.getDate(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return ret;
	}

	@Override
	public List<Bottle> selByState(int i) {
		List<Bottle> list = new ArrayList<>();
		try {
			getConn();
			ps = conn.prepareStatement("select * from bottle where state=?");
			ps.setObject(1, i);
			rs = ps.executeQuery();
			while(rs.next()) {
				Bottle ret = new Bottle();
				ret.setId(rs.getInt(1));
				ret.setMessage(rs.getString(2));
				ret.setKinds(rs.getInt(3));
				ret.setState(rs.getInt(4));
				ret.setCreateTime(rs.getDate(5));
				ret.setUid(rs.getInt(6));
				ret.setUpdateTime(rs.getDate(7));
				list.add(ret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return list;
	}
	
	@Override
	public List<Bottle> selByUid(int uid) {
		List<Bottle> list = new ArrayList<>();
		try {
			getConn();
			ps = conn.prepareStatement("select * from bottle where uid=?");
			ps.setObject(1, uid);
			rs = ps.executeQuery();
			while(rs.next()) {
				Bottle ret = new Bottle();
				ret.setId(rs.getInt(1));
				ret.setMessage(rs.getString(2));
				ret.setKinds(rs.getInt(3));
				ret.setState(rs.getInt(4));
				ret.setCreateTime(rs.getDate(5));
				ret.setUid(rs.getInt(6));
				ret.setUpdateTime(rs.getDate(7));
				list.add(ret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return list;
	}

	@Override
	public int insBottle(Bottle bottle) {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("insert into bottle values(default,?,?,?,now(),?,now())");
			ps.setObject(1, bottle.getMessage());
			ps.setObject(2, bottle.getKinds());
			ps.setObject(3, bottle.getState());
			ps.setObject(4, bottle.getUid());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}

	@Override
	public int updById(Bottle bottle) {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("update bottle set message=?,state=?,update_time=now() where id=?");
			ps.setObject(1, bottle.getMessage());
			ps.setObject(2, bottle.getState());
			ps.setObject(3, bottle.getId());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}

	@Override
	public int delById(Bottle bottle) {
		int count = 0;
		try {
			getConn();
			ps = conn.prepareStatement("delete from bottle where id=?");
			ps.setObject(1, bottle.getId());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return count;
	}

	@Override
	public List<Bottle> selByKindsAndUid(Bottle bottle) {
		List<Bottle> list = new ArrayList<>();
		try {
			getConn();
			ps = conn.prepareStatement("select * from bottle where uid=? and kinds=?");
			ps.setObject(1, bottle.getUid());
			ps.setObject(2, bottle.getKinds());
			rs = ps.executeQuery();
			while(rs.next()) {
				Bottle ret = new Bottle();
				ret.setId(rs.getInt(1));
				ret.setMessage(rs.getString(2));
				ret.setKinds(rs.getInt(3));
				ret.setState(rs.getInt(4));
				ret.setCreateTime(rs.getDate(5));
				ret.setUid(rs.getInt(6));
				ret.setUpdateTime(rs.getDate(7));
				list.add(ret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return list;
	}

}
