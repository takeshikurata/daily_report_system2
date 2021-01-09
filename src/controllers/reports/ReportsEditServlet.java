package controllers.reports;

import java.io.IOException;

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
 * Servlet implementation class ReportsEditServlet
 */
@WebServlet("/reports/edit")
public class ReportsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EntityManager em =DBUtil.createEntityManager();
//
//        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
//
//        em.close();

        // Reportクラスにアクセスするため、ReportsDAOをインスタンス化
        ReportsDAO dao = new ReportsDAO();
        // 検索処理を実行し、オブジェクトを取得
        Report r = dao.getReport(Integer.parseInt(request.getParameter("id")));

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
//        if (r != null && login_employee.getId() == r.getEmployee().getId()) {
        if (r != null && login_employee.getId() == r.getEmployee_id()) {
                           request.setAttribute("report", r);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("report_id", r.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp");
        rd.forward(request, response);
    }

}
