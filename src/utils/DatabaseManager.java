package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // データベース接続と結果取得のための変数
    private static Connection con;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1. ドライバのクラスをJava上で読み込む
        Class.forName("com.mysql.jdbc.Driver");
        // 2. DBと接続する
        con = DriverManager.getConnection(
//                "jdbc:mysql://localhost/daily_report_system2?useSSL=false&useUnicode=true&characterEncoding=utf8",
//                "re2user",
//                "re2pass"
                "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e219fb820161426?useSSL=false&useUnicode=true&characterEncoding=utf8",
                "b102126d7a16cd",
                "6ac9d2b1"
                );

        return con;
    }

    public static void close() {
        // 7. 接続を閉じる
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
