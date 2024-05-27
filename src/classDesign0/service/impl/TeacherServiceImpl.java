package classDesign0.service.impl;

import classDesign0.entity.TeacherDO;
import classDesign0.request.TeacherRequest;
import classDesign0.res.TableDTO;
import classDesign0.service.TeacherService;
import classDesign0.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TeacherServiceImpl implements TeacherService {
    @Override
    public TableDTO retrieveTeachers(TeacherRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from teacher");
        if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
            sql.append(" where teacherName like '%").append(request.getSearchKey().trim()).append("%'");
        }
        sql.append(" order by id asc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();
        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            // 查询记录
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from teacher ");
            if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
                sql.append(" where teacherName like '%").append(request.getSearchKey().trim()).append("%'");
                System.out.println(request.getSearchKey().trim());
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
            return tableDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean addTeacher(TeacherDO teacherDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into teacher(id,teacherName,teacherGender,teacherAge,section,major,lessonNum)");
        sql.append(" values(?,?,?,?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,teacherDO.getId());
            ps.setString(2,teacherDO.getTeacherName());
            ps.setString(3,teacherDO.getTeacherGender());
            ps.setInt(4,teacherDO.getTeacherAge());
            ps.setString(5,teacherDO.getSection());
            ps.setString(6,teacherDO.getMajor());
            ps.setInt(7, 0);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
    public TableDTO sortHours() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from teacher order by lessonNum desc limit 0,10");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            tableDTO.setData(fillData(rs));
            return tableDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
    }

    @Override
    public TeacherDO getById(int selectedTeacherId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from teacher where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TeacherDO teacherDO = new TeacherDO();

        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedTeacherId);
            rs = ps.executeQuery();
            while(rs.next()) {
                // 处理查出的每一条记录，放在table里去
                Vector<Object> oneRecord = new Vector<>();
                int id = rs.getInt("id");
                String name = rs.getString("teacherName");
                String gender = rs.getString("teacherGender");
                int age = rs.getInt("teacherAge");
                String section = rs.getString("section");
                String major = rs.getString("major");
                int lessonNum = rs.getInt("lessonNum");
                teacherDO.setId(id);
                teacherDO.setTeacherName(name);
                teacherDO.setTeacherGender(gender);
                teacherDO.setTeacherAge(age);
                teacherDO.setSection(section);
                teacherDO.setMajor(major);
                teacherDO.setLessonNum(lessonNum);
            }
            return teacherDO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
    }

    @Override
    public boolean update(TeacherDO teacherDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update teacher set id = ?, teacherName = ?, teacherGender = ?, teacherAge = ?" +
                ", section = ?, major = ?, lessonNum = ? where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,teacherDO.getId());
            ps.setString(2,teacherDO.getTeacherName());
            ps.setString(3,teacherDO.getTeacherGender());
            ps.setInt(4,teacherDO.getTeacherAge());
            ps.setString(5,teacherDO.getSection());
            ps.setString(6,teacherDO.getMajor());
            ps.setInt(7, teacherDO.getLessonNum());
            ps.setInt(8,teacherDO.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean del(int selectedTeacherId) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from teacher where id = ").append(selectedTeacherId);
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }


    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()) {
            // 处理查出的每一条记录，放在table里去
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("teacherName");
            String gender = rs.getString("teacherGender");
            int age = rs.getInt("teacherAge");
            String section = rs.getString("section");
            String major = rs.getString("major");
            int lessonNum = rs.getInt("lessonNum");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(gender);
            oneRecord.addElement(age);
            oneRecord.addElement(section);
            oneRecord.addElement(major);
            oneRecord.addElement(lessonNum);
            data.addElement(oneRecord);
        }
        return data;
    }
}