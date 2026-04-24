package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "select sj.name, t.subject_cd, t.no, t.point from test t " +
                     "join subject sj on t.subject_cd = sj.cd and t.school_cd = sj.school_cd " +
                     "where t.student_no = ? order by t.subject_cd asc, t.no asc";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, student.getNo());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TestListStudent bean = new TestListStudent();
                bean.setSubjectName(rs.getString("name"));
                bean.setSubjectCd(rs.getString("subject_cd"));
                bean.setNum(rs.getInt("no"));
                bean.setPoint(rs.getInt("point"));
                list.add(bean);
            }
        } finally { conn.close(); }
        return list;
    }
}