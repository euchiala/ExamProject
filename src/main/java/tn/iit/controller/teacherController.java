package tn.iit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class teacherServlet$
 */
@WebServlet("/")
public class teacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeacherDAO teacherDAO = new TeacherDAO(DatabaseConnection.getConnection());
	public ServletContext servletContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext application = getServletContext();
		RequestDispatcher rd = null;
		List<Teacher> teachers = teacherDAO.getAllTeachers();
		request.setAttribute("teachers", teachers);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	    String id = request.getParameter("id");
        if (id != null) {
           doPost(request,response);
        }else {
		rd = application.getRequestDispatcher("/Teacher.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servletContext = getServletContext();
        String id = request.getParameter("id");
        if (id != "") {
        	 teacherDAO.deleteTeacher(Integer.parseInt(id));
        }

        response.sendRedirect("/ExamProject/");
	}

}
