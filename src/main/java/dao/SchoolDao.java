package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao {

    // 学校コードを指定して学校インスタンスを1件取得するメソッド
    public School get(String cd) throws Exception {
        // 学校インスタンスを初期化
        School school = new School();
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from school where cd=?");
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, cd);

            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 学校インスタンスに学校コードと学校名をセット
                school.setCd(rSet.getString("cd"));
                school.setName(rSet.getString("name"));
            } else {
                // リザルトセットが存在しない場合
                // 学校インスタンスにnullをセット
                school = null;
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

        return school;
    }
}