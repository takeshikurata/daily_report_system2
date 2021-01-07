package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import utils.DatabaseManager;

public class EmployeesDAO {
    // データベース接続と結果取得のための変数
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Employee> getAllEmployees(int offset, int limit) {
        // メソッドの結果として返すリスト
        List<Employee> results = new ArrayList<Employee>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select * from employees order by id desc limit ?, ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,offset);
            pstmt.setLong(2,limit);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            while (rs.next()) {
                // 1件ずつEmployeeオブジェクトを生成して結果を詰める
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setCode(rs.getString("code"));
                employee.setName(rs.getString("name"));
                employee.setPassword(rs.getString("password"));
                employee.setAdmin_flag(rs.getInt("admin_flag"));
                employee.setCreated_at(rs.getTimestamp("created_at"));
                employee.setUpdated_at(rs.getTimestamp("updated_at"));
                employee.setDelete_flag(rs.getInt("delete_flag"));

                // リストに追加
                results.add(employee);
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

    public List<Employee> getAllEmployees() {
        // メソッドの結果として返すリスト
        List<Employee> results = new ArrayList<Employee>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select * from employees order by id desc";
            pstmt = con.prepareStatement(sql);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            while (rs.next()) {
                // 1件ずつEmployeeオブジェクトを生成して結果を詰める
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setCode(rs.getString("code"));
                employee.setName(rs.getString("name"));
                employee.setPassword(rs.getString("password"));
                employee.setAdmin_flag(rs.getInt("admin_flag"));
                employee.setCreated_at(rs.getTimestamp("created_at"));
                employee.setUpdated_at(rs.getTimestamp("updated_at"));
                employee.setDelete_flag(rs.getInt("delete_flag"));

                // リストに追加
                results.add(employee);
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

    public long getEmployeesCount() {
        // メソッドの結果として返す値
        long count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select count(*) as count from employees";
            pstmt = con.prepareStatement(sql);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            rs.next();
            count = rs.getInt("count");

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
        return count;
    }

}
