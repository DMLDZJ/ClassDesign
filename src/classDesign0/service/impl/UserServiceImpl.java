package classDesign0.service.impl;

import classDesign0.entity.UserDO;
import classDesign0.service.UserService;
import classDesign0.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean validateUser(UserDO userDO) {
        String userName = userDO.getUserName();
        String pwdParam = userDO.getPwd();
        String sql = "select pwd from user where user_name = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtil.getConn();
            if(conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                String pwd = resultSet.getString(1);
                if(pwdParam.equals(pwd)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return true;
    }

    @Override
    public boolean addUser(UserDO userDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into user(user_name,pwd)");
        sql.append(" values(?,?)");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,userDO.getUserName());
            ps.setString(2,userDO.getPwd());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
