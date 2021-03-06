package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import models.Department;
import models.Employee;

/**
 * Servlet implementation class EmployeeEditServlet
 */
@WebServlet("/employees/edit")
public class EmployeesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesEditServlet() {
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

        // Departmentクラスにアクセスするため、DepartmentsDAOをインスタンス化
        DepartmentsDAO ddao = new DepartmentsDAO();
        // 検索処理を実行し、Listオブジェクトを取得
        List<Department> departments = ddao.getAllDepartments();


        request.setAttribute("employee", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("employee_id", e.getId());
        request.setAttribute("departments", departments);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
        rd.forward(request, response);
    }

}
