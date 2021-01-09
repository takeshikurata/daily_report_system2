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
//            String sql = "select * from employees order by id desc limit ?, ?";
            String sql = "select a.*, b.dname from employees a inner join departments b"
                    + " on a.department_id = b.id order by a.id desc limit ?, ?";
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
                employee.setDepartment_id(rs.getInt("department_id"));
                employee.setDname(rs.getString("dname"));

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

    public long checkRegisteredCode(String code) {
        // メソッドの結果として返す値
        long count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select count(*) as count from employees where code = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,code);

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

    public Employee checkLoginCodeAndPassword(String code, String password) {
        // メソッドの結果として返すオブジェクト
        Employee employee = new Employee();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
//            String sql = "select * from employees where delete_flag = 0 and code = ? and password = ?";
            String sql = "select a.*, b.dname from employees a inner join departments b"
                    + " on a.department_id = b.id where a.delete_flag = 0 and a.code = ? and a.password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,code);
            pstmt.setString(2,password);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            rs.next();
            employee.setId(rs.getInt("id"));
            employee.setCode(rs.getString("code"));
            employee.setName(rs.getString("name"));
            employee.setPassword(rs.getString("password"));
            employee.setAdmin_flag(rs.getInt("admin_flag"));
            employee.setCreated_at(rs.getTimestamp("created_at"));
            employee.setUpdated_at(rs.getTimestamp("updated_at"));
            employee.setDelete_flag(rs.getInt("delete_flag"));
            employee.setDepartment_id(rs.getInt("department_id"));
            employee.setDname(rs.getString("dname"));

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
        return employee;
    }

    public Employee getEmployee(int id) {
        // メソッドの結果として返すオブジェクト
        Employee employee = new Employee();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
//            String sql = "select * from employees where id = ?";
            String sql = "select a.*, b.dname from employees a inner join departments b"
                    + " on a.department_id = b.id where a.id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            rs.next();
            employee.setId(rs.getInt("id"));
            employee.setCode(rs.getString("code"));
            employee.setName(rs.getString("name"));
            employee.setPassword(rs.getString("password"));
            employee.setAdmin_flag(rs.getInt("admin_flag"));
            employee.setCreated_at(rs.getTimestamp("created_at"));
            employee.setUpdated_at(rs.getTimestamp("updated_at"));
            employee.setDelete_flag(rs.getInt("delete_flag"));
            employee.setDepartment_id(rs.getInt("department_id"));
            employee.setDname(rs.getString("dname"));

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
        return employee;
    }

    public int insertEmployee(Employee employee) {
        // メソッドの結果として返す値
        int count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "insert into employees "
                    + "(code, name, password, admin_flag, created_at, updated_at, delete_flag, department_id) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,employee.getCode());
            pstmt.setString(2,employee.getName());
            pstmt.setString(3,employee.getPassword());
            pstmt.setInt(4,employee.getAdmin_flag());
            pstmt.setTimestamp(5,employee.getCreated_at());
            pstmt.setTimestamp(6,employee.getUpdated_at());
            pstmt.setInt(7,employee.getDelete_flag());
            pstmt.setInt(8,employee.getDepartment_id());

            // 4,5. Select文の実行と結果を格納/代入
            count = pstmt.executeUpdate();

            // 6. 結果を格納する

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

    public int updateEmployee(Employee employee) {
        // メソッドの結果として返す値
        int count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "update employees set "
                    + "code = ?, name = ?, password = ?, admin_flag = ?, updated_at = ?, department_id = ? "
                    + "where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,employee.getCode());
            pstmt.setString(2,employee.getName());
            pstmt.setString(3,employee.getPassword());
            pstmt.setInt(4,employee.getAdmin_flag());
            pstmt.setTimestamp(5,employee.getUpdated_at());
            pstmt.setInt(6,employee.getDepartment_id());
            pstmt.setInt(7,employee.getId());

            // 4,5. Select文の実行と結果を格納/代入
            count = pstmt.executeUpdate();

            // 6. 結果を格納する

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

    public int deleteEmployee(Employee employee) {
        // メソッドの結果として返す値
        int count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "update employees set updated_at = ?, delete_flag = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1,employee.getUpdated_at());
            pstmt.setInt(2,employee.getDelete_flag());
            pstmt.setInt(3,employee.getId());

            // 4,5. Select文の実行と結果を格納/代入
            count = pstmt.executeUpdate();

            // 6. 結果を格納する

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
