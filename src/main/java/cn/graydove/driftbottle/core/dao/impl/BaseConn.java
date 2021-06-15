package cn.graydove.driftbottle.core.dao.impl;

import java.sql.*;

/**
 * @author graydove
 */
public abstract class BaseConn {

    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    public void getConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drift_bottle?useSSL=false", "root", "Du87688524");
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
}
