package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Department;
import utils.DatabaseManager;

public class DepartmentsDAO {
    // データベース接続と結果取得のための変数
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Department> getAllDepartments() {
        // メソッドの結果として返すリスト
        List<Department> results = new ArrayList<Department>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select * from departments where delete_flag = 0 order by id";
            pstmt = con.prepareStatement(sql);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            while (rs.next()) {
                // 1件ずつオブジェクトを生成して結果を詰める
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setCode(rs.getString("code"));
                department.setDname(rs.getString("dname"));
                department.setCreated_at(rs.getTimestamp("created_at"));
                department.setUpdated_at(rs.getTimestamp("updated_at"));
                department.setDelete_flag(rs.getInt("delete_flag"));

                // リストに追加
                results.add(department);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseManager.close();
        }
        return results;
    }

}
