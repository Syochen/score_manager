package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "select s.no, s.name, t.no as test_no, t.point from student s " +
                     "left join test t on s.no = t.student_no and t.subject_cd = ? " +
                     "where s.ent_year = ? and s.class_num = ? and s.school_cd = ? order by s.no asc, t.no asc";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, subject.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, school.getCd());
            ResultSet rs = st.executeQuery();
            
            TestListSubject current = null;
            while (rs.next()) {
                String sNo = rs.getString("no");
                if (current == null || !current.getStudentNo().equals(sNo)) {
                    current = new TestListSubject();
                    current.setStudentNo(sNo);
                    current.setStudentName(rs.getString("name"));
                    current.setEntYear(entYear);
                    current.setClassNum(classNum);
                    list.add(current);
                }
                int tNo = rs.getInt("test_no");
                if (!rs.wasNull()) {
                    current.putPoint(tNo, rs.getInt("point"));
                }
            }
        } finally { conn.close(); }
        return list;
    }
}