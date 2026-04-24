package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
    private String baseSql = "select * from test where school_cd=? ";

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection conn = getConnection();
        // 入学年度とクラス番号で学生を絞り込み、その成績を結合するSQL
        String sql = "select s.no, s.name, t.point from student s " +
                     "left join test t on s.no = t.student_no and t.subject_cd = ? and t.no = ? " +
                     "where s.ent_year = ? and s.class_num = ? and s.school_cd = ? order by s.no asc";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, subject.getCd());
            st.setInt(2, num);
            st.setInt(3, entYear);
            st.setString(4, classNum);
            st.setString(5, school.getCd());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                Student s = new Student();
                s.setNo(rs.getString("no"));
                s.setName(rs.getString("name"));
                t.setStudent(s);
                t.setPoint(rs.getInt("point"));
                if (rs.wasNull()) t.setPoint(-1); // 未受験
                list.add(t);
            }
        } finally { conn.close(); }
        return list;
    }

    public boolean save(List<Test> list, School school) throws Exception {
        Connection conn = getConnection();
        try {
            for (Test test : list) {
                // 点数が空文字（-1）でなければ保存処理（MERGE文等の利用が一般的）
                String sql = "replace into test (student_no, subject_cd, school_cd, no, point, class_num) values (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, test.getStudent().getNo());
                    st.setString(2, test.getSubject().getCd());
                    st.setString(3, school.getCd());
                    st.setInt(4, test.getNo());
                    st.setInt(5, test.getPoint());
                    st.setString(6, test.getClassNum());
                    st.executeUpdate();
                }
            }
            return true;
        } finally { conn.close(); }
    }
}