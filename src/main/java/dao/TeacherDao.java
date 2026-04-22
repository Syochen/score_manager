package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    // 講師IDとパスワードを指定して講師情報を取得するメソッド
    public Teacher login(String id, String password) throws Exception {
        // 講師インスタンスを初期化
        Teacher teacher = null;
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // 学校Daoを初期化
        SchoolDao sDao = new SchoolDao();

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from teacher where id=? and password=?");
            // プリペアードステートメントに講師IDをバインド
            statement.setString(1, id);
            // プリペアードステートメントにパスワードをバインド
            statement.setString(2, password);

            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 講師インスタンスを生成
                teacher = new Teacher();
                // 講師インスタンスに各フィールドの値をセット
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));
                // 学校コードをもとに学校情報を取得し、セット
                School school = sDao.get(rSet.getString("school_cd"));
                teacher.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                statement.close();
            }
            // コネクションを閉じる
            if (connection != null) {
                connection.close();
            }
        }

        return teacher;
    }
}