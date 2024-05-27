package classDesign0.service.impl;

import classDesign0.entity.LessonDO;
import classDesign0.request.LessonRequest;
import classDesign0.res.TableDTO;
import classDesign0.service.LessonService;
import classDesign0.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class LessonServiceImpl implements LessonService {
    @Override
    public boolean del(int selectedLessonId) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from lesson where idLesson = ").append(selectedLessonId);
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

    @Override
    public TableDTO retrieveLesson(LessonRequest request) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from lesson");
        if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
            sql.append(" where nameLesson like '%").append(request.getSearchKey().trim()).append("%' ");
        }
        sql.append(" order by idLesson asc limit ").append(request.getStart()).append(",").append(request.getPageSize());

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();

        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from lesson ");
            if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
                sql.append(" where nameLesson like '%").append(request.getSearchKey().trim()).append("%' ");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
            return tableDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public TableDTO retrieveLesson1(LessonRequest request) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from lesson");
        if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
            sql.append(" where lessonTeacher like '%").append(request.getSearchKey().trim()).append("%' ");
        }
        sql.append(" order by idLesson asc limit ").append(request.getStart()).append(",").append(request.getPageSize());

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();

        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from lesson ");
            if(request.getSearchKey() != null && !request.getSearchKey().trim().isEmpty()) {
                sql.append(" where lessonTeacher like '%").append(request.getSearchKey().trim()).append("%' ");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
            return tableDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean addLesson(LessonDO lessonDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into lesson(idLesson,nameLesson,HourLesson,classNumLesson,lessonTeacher)");
        sql.append(" values(?,?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,lessonDO.getId());
            ps.setString(2,lessonDO.getLessonName());
            ps.setInt(3,lessonDO.getHour());
            ps.setInt(4,lessonDO.getClassNum());
            ps.setString(5,lessonDO.getLessonTeacher());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LessonDO lessonDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update lesson set idLesson = ?, nameLesson = ?, HourLesson = ?, classNumLesson = ?" +
                ", lessonTeacher = ? where idLesson = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,lessonDO.getId());
            ps.setString(2,lessonDO.getLessonName());
            ps.setInt(3,lessonDO.getHour());
            ps.setInt(4,lessonDO.getClassNum());
            ps.setString(5,lessonDO.getLessonTeacher());
            ps.setInt(6,lessonDO.getId());
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
    public LessonDO getById(int selectedLessonId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from lesson where idLesson = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LessonDO lessonDO = new LessonDO();

        try {
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedLessonId);
            rs = ps.executeQuery();
            while(rs.next()) {
                // 处理查出的每一条记录，放在table里去
                Vector<Object> oneRecord = new Vector<>();
                int id = rs.getInt("idLesson");
                String name = rs.getString("nameLesson");
                int hour = rs.getInt("HourLesson");
                int classNum = rs.getInt("classNumLesson");
                String lessonTeacher = rs.getString("lessonTeacher");
                lessonDO.setId(id);
                lessonDO.setLessonName(name);
                lessonDO.setHour(hour);
                lessonDO.setClassNum(classNum);
                lessonDO.setLessonTeacher(lessonTeacher);
            }
            return lessonDO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()) {
            // 处理查出的每一条记录，放在table里去
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("idLesson");
            String name = rs.getString("nameLesson");
            int hour = rs.getInt("HourLesson");
            int classNumLesson = rs.getInt("classNumLesson");
            String lessonLesson = rs.getString("lessonTeacher");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(hour);
            oneRecord.addElement(classNumLesson);
            oneRecord.addElement(lessonLesson);
            data.addElement(oneRecord);
        }
        return data;
    }
}
