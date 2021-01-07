package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import models.Employee;

/**
 * Servlet implementation class EmployeesIndexServlet
 */
@WebServlet("/employees/index")
public class EmployeesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EntityManager em = DBUtil.createEntityManager();
        // Employeeクラスにアクセスするため、EmployeesDAOをインスタンス化
        EmployeesDAO dao = new EmployeesDAO();

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) { }

//        List<Employee> employees = em.createNamedQuery("getAllEmployees", Employee.class)
//                                    .setFirstResult(15 * (page -1))
//                                    .setMaxResults(15)
//                                    .getResultList();
//
//        long employees_count = (long)em.createNamedQuery("getEmployeesCount", Long.class)
//                                        .getSingleResult();
//
//        em.close();

        // 検索処理を実行し、Listオブジェクトを取得
        List<Employee> employees = dao.getAllEmployees(15 * (page -1), 15);

        // 検索処理を実行し、件数を取得
        long employees_count = dao.getEmployeesCount();

        request.setAttribute("employees", employees);
        request.setAttribute("employees_count", employees_count);
        request.setAttribute("page", page);
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp");
        rd.forward(request, response);
    }

}
