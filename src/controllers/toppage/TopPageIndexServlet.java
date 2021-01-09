package controllers.toppage;

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
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EntityManager em = DBUtil.createEntityManager();
        // Reportクラスにアクセスするため、ReportsDAOをインスタンス化
        ReportsDAO dao = new ReportsDAO();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
//        List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
//                .setParameter("employee", login_employee)
//                .setFirstResult(15 * (page - 1))
//                .setMaxResults(15)
//                .getResultList();
//
//        long reports_count = (long)em.createNamedQuery("getMyReportsCount", Long.class)
//                .setParameter("employee", login_employee)
//                .getSingleResult();
//
//        em.close();

        int employee_id = login_employee.getId();

        // 検索処理を実行し、Listオブジェクトを取得
        List<Report> reports = dao.getMyAllReports(15 * (page -1), 15, employee_id);

        // 検索処理を実行し、件数を取得
        long reports_count = dao.getMyReportsCount(employee_id);

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}
