package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportsDAO;
import models.Employee;
import models.Report;

/**
 * Servlet implementation class ReportsIndexServlet
 */
@WebServlet("/reports/index")
public class ReportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EntityManager em = DBUtil.createEntityManager();
        // Reportクラスにアクセスするため、ReportsDAOをインスタンス化
        ReportsDAO dao = new ReportsDAO();

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
//        List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
//                .setFirstResult(15 * (page -1))
//                .setMaxResults(15)
//                .getResultList();
//
//        long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
//                .getSingleResult();
//
//        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        List<Report> reports;
        long reports_count;

        if (login_employee.getAdmin_flag() == 1) {
            // 検索処理を実行し、Listオブジェクトを取得
            reports = dao.getAllReports(15 * (page -1), 15);

            // 検索処理を実行し、件数を取得
            reports_count = dao.getReportsCount();
        } else {
            int department_id = login_employee.getDepartment_id();
            // 検索処理を実行し、Listオブジェクトを取得
            reports = dao.getApprovalReports(15 * (page -1), 15, department_id);

            // 検索処理を実行し、件数を取得
            reports_count = dao.getApprovalReportsCount(department_id);
        }

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
    }

}
