package controllers.employees;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import models.Employee;

/**
 * Servlet implementation class EmployeesShowServlet
 */
@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EntityManager em = DBUtil.createEntityManager();
//
//        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
//
//        em.close();

        // Employeeクラスにアクセスするため、EmployeesDAOをインスタンス化
        EmployeesDAO dao = new EmployeesDAO();
        // 検索処理を実行し、オブジェクトを取得
        Employee e = dao.getEmployee(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("employee", e);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

}
