package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    // getメソッド：クラス番号と学校からClassNumインスタンスを取得
    public ClassNum get(String class_num, School school) throws Exception {
        ClassNum classNum = new ClassNum();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement("select * from class_num where class_num=? and school_cd=?");
        statement.setString(1, class_num);
        statement.setString(2, school.getCd());

        ResultSet rSet = statement.executeQuery();

        if (rSet.next()) {
            classNum.setClass_num(rSet.getString("class_num"));
            classNum.setSchool(school);
        } else {
            classNum = null;
        }

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return classNum;
    }

    // filterメソッド：学校に所属するクラス番号（文字列）のリストを取得
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement("select class_num from class_num where school_cd=? order by class_num asc");
        statement.setString(1, school.getCd());

        ResultSet rSet = statement.executeQuery();

        while (rSet.next()) {
            list.add(rSet.getString("class_num"));
        }

        if (statement != null) { statement.close(); }
        if (connection != null) { connection.close(); }

        return list;
    }
}