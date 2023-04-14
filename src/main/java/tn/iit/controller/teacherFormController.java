package tn.iit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.iit.DAO.DatabaseConnection;
import tn.iit.DAO.TeacherDAO;
import tn.iit.model.Teacher;

/**
 * Servlet implementation class teacherFormController
 */
@WebServlet("/teacherFormController")
public class teacherFormController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TeacherDAO teacherDAO = new TeacherDAO(DatabaseConnection.getConnection());
    public ServletContext servletContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = getServletContext();
        RequestDispatcher rd = null;

        String id = request.getParameter("id");
        if (id != null) {
            Teacher teacher = teacherDAO.getTeacherById(Integer.parseInt(id));
            request.setAttribute("teacher", teacher);
        }

        rd = application.getRequestDispatcher("/teacherForm.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletContext = getServletContext();
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String institution = request.getParameter("institution");
        String phoneParam = request.getParameter("phone");
        int phone = 0;
        if (phoneParam != null && !phoneParam.isEmpty()) {
            try {
                phone = Integer.parseInt(phoneParam);
            } catch (NumberFormatException e) {
                // handle the case where the phone parameter is not a valid integer
            }
        }
        String id = request.getParameter("id");
        if (id != "") {
            Teacher teacher = teacherDAO.getTeacherById(Integer.parseInt(id));
            teacher.setFirst_name(first_name);
            teacher.setLast_name(last_name);
            teacher.setEmail(email);
            teacher.setInstitution(institution);
            teacher.setPhone(phone);
            teacherDAO.updateTeacher(Integer.parseInt(id),teacher);
        } else {
            Teacher teacher = new Teacher(first_name, last_name, email, institution, phone);
            teacherDAO.addTeacher(teacher);
        }

        response.sendRedirect("/ExamProject/");
    }
}