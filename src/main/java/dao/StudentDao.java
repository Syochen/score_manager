package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    // 1. フィールド変数 baseSql（共通のSELECT文）
    private String baseSql = "select * from student where school_cd=? ";

    // 2. postFilterメソッド（ResultSetをList<Student>に変換）
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;
    }

    // 3. filterメソッド（学校、入学年度、クラス、在学フラグ指定）
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String condition = "and ent_year=? and class_num=? ";
        String order = "order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true ";
        }

        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
        statement.setString(1, school.getCd());
        statement.setInt(2, entYear);
        statement.setString(3, classNum);

        ResultSet rSet = statement.executeQuery();
        list = postFilter(rSet, school);

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return list;
    }

    // 4. filterメソッド（学校、入学年度、在学フラグ指定）
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String condition = "and ent_year=? ";
        String order = "order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true ";
        }

        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
        statement.setString(1, school.getCd());
        statement.setInt(2, entYear);

        ResultSet rSet = statement.executeQuery();
        list = postFilter(rSet, school);

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return list;
    }

    // 5. filterメソッド（学校、在学フラグ指定：初期表示用）
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String order = "order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true ";
        }

        statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
        statement.setString(1, school.getCd());

        ResultSet rSet = statement.executeQuery();
        list = postFilter(rSet, school);

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return list;
    }
 // 6. getメソッド（学生番号から1件取得）
    public Student get(String no) throws Exception {
        Student student = new Student();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement("select * from student where no=?");
        statement.setString(1, no);

        ResultSet rSet = statement.executeQuery();

        if (rSet.next()) {
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
        } else {
            student = null;
        }

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return student;
    }

    // 7. saveメソッド（登録 or 更新）
    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        // すでに存在するか確認
        Student exist = get(student.getNo());

        if (exist == null) {
            // 存在しない場合は新規登録 (INSERT)
            statement = connection.prepareStatement(
                "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, student.getNo());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getEntYear());
            statement.setString(4, student.getClassNum());
            statement.setBoolean(5, student.isAttend());
            statement.setString(6, student.getSchool().getCd());
        } else {
            // 存在する場合は更新 (UPDATE)
            statement = connection.prepareStatement(
                "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
            );
            statement.setString(1, student.getName());
            statement.setInt(2, student.getEntYear());
            statement.setString(3, student.getClassNum());
            statement.setBoolean(4, student.isAttend());
            statement.setString(5, student.getNo());
        }

        count = statement.executeUpdate();

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return count > 0;
    }
}

