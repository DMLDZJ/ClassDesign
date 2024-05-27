package classDesign0.service.impl;

import classDesign0.entity.AdminDO;
import classDesign0.entity.UserDO;
import classDesign0.service.AdminService;
import classDesign0.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String pwdParam = adminDO.getPwd();
        String sql = "select pwd from manager where user_name = ?";

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
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

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
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
