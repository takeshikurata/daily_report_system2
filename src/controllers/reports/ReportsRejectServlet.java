package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportsDAO;
import models.Report;

/**
 * Servlet implementation class ReporsApprovalRequestServlet
 */
@WebServlet("/reports/reject")
public class ReportsRejectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsRejectServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            // Reportクラスにアクセスするため、ReportsDAOをインスタンス化
            ReportsDAO dao = new ReportsDAO();
            // 検索処理を実行し、オブジェクトを取得
            Report r = dao.getReport(Integer.parseInt(request.getParameter("id")));

            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            r.setApproval_status(0);

            // 更新処理を実行し、件数を取得
            int count = dao.updateReport(r);

            request.getSession().setAttribute("flush", "却下が完了しました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");
        }
    }

}
