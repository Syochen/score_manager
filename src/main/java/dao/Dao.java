package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
    // データソース
    static DataSource ds;

    public Connection getConnection() throws Exception {
        // データソースが未初期化の場合
        if (ds == null) {
            // 初期コンテキストを作成
            InitialContext ic = new InitialContext();
            // context.xmlで設定したリソースをルックアップ
            // java:comp/env/ の後ろにcontext.xmlのname（jdbc/kaihatu）をつなげる
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/kaihatu");
        }
        // データベースへのコネクションを返却
        return ds.getConnection();
    }
}