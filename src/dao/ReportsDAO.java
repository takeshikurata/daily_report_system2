package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Report;
import utils.DatabaseManager;

public class ReportsDAO {
    // データベース接続と結果取得のための変数
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Report> getMyAllReports(int offset, int limit, int employee_id) {
        // メソッドの結果として返すリスト
        List<Report> results = new ArrayList<Report>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
//            String sql = "select * from reports where employee_id = ? order by id desc limit ?, ?";
            String sql = "select a.*, b.name as employee_name from reports a inner join employees b"
                    + " on a.employee_id = b.id where a.employee_id = ? order by a.id desc limit ?, ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,employee_id);
            pstmt.setLong(2,offset);
            pstmt.setLong(3,limit);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            while (rs.next()) {
                // 1件ずつReportオブジェクトを生成して結果を詰める
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setReport_date(rs.getDate("report_date"));
                report.setTitle(rs.getString("title"));
                report.setContent(rs.getString("content"));
                report.setCreated_at(rs.getTimestamp("created_at"));
                report.setUpdated_at(rs.getTimestamp("updated_at"));
                report.setEmployee_id(rs.getInt("employee_id"));
                report.setEmployee_name(rs.getString("employee_name"));

                // リストに追加
                results.add(report);
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

    public List<Report> getAllReports(int offset, int limit) {
        // メソッドの結果として返すリスト
        List<Report> results = new ArrayList<Report>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
//            String sql = "select * from reports order by id desc limit ?, ?";
            String sql = "select a.*, b.name as employee_name from reports a inner join employees b"
                    + " on a.employee_id = b.id order by a.id desc limit ?, ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,offset);
            pstmt.setLong(2,limit);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            while (rs.next()) {
                // 1件ずつEmployeeオブジェクトを生成して結果を詰める
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setReport_date(rs.getDate("report_date"));
                report.setTitle(rs.getString("title"));
                report.setContent(rs.getString("content"));
                report.setCreated_at(rs.getTimestamp("created_at"));
                report.setUpdated_at(rs.getTimestamp("updated_at"));
                report.setEmployee_id(rs.getInt("employee_id"));
                report.setEmployee_name(rs.getString("employee_name"));

                // リストに追加
                results.add(report);
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

    public long getMyReportsCount(int employee_id) {
        // メソッドの結果として返す値
        long count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select count(*) as count from reports where employee_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,employee_id);

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

    public long getReportsCount() {
        // メソッドの結果として返す値
        long count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "select count(*) as count from reports";
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

    public Report getReport(int id) {
        // メソッドの結果として返すオブジェクト
        Report report = new Report();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
//            String sql = "select * from reports where id = ?";
            String sql = "select a.*, b.name as employee_name from reports a inner join employees b"
                    + " on a.employee_id = b.id where a.id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);

            // 4,5. Select文の実行と結果を格納/代入
            rs = pstmt.executeQuery();

            // 6. 結果を格納する
            rs.next();
            report.setId(rs.getInt("id"));
            report.setReport_date(rs.getDate("report_date"));
            report.setTitle(rs.getString("title"));
            report.setContent(rs.getString("content"));
            report.setCreated_at(rs.getTimestamp("created_at"));
            report.setUpdated_at(rs.getTimestamp("updated_at"));
            report.setEmployee_id(rs.getInt("employee_id"));
            report.setEmployee_name(rs.getString("employee_name"));

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
        return report;
    }

    public int insertReport(Report report) {
        // メソッドの結果として返す値
        int count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "insert into reports (title, content, report_date, created_at, updated_at, employee_id) "
                    + "values (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,report.getTitle());
            pstmt.setString(2,report.getContent());
            pstmt.setDate(3,report.getReport_date());
            pstmt.setTimestamp(4,report.getCreated_at());
            pstmt.setTimestamp(5,report.getUpdated_at());
            pstmt.setInt(6,report.getEmployee_id());

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

    public int updateReport(Report report) {
        // メソッドの結果として返す値
        int count = 0;

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口(statement)オブジェクトの作成
            String sql = "update reports set title = ?, content = ?, report_date = ?, updated_at = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,report.getTitle());
            pstmt.setString(2,report.getContent());
            pstmt.setDate(3,report.getReport_date());
            pstmt.setTimestamp(4,report.getUpdated_at());
            pstmt.setInt(5,report.getId());

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
