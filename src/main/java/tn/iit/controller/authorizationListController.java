package tn.iit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import tn.iit.DAO.AuthorizationDAO;
import tn.iit.DAO.DatabaseConnection;
import tn.iit.DAO.TeacherDAO;
import tn.iit.model.Authorization;
import tn.iit.model.Teacher;

/**
 * Servlet implementation class authorizationListController
 */
@WebServlet("/authorizationListController")
public class authorizationListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeacherDAO teacherDAO = new TeacherDAO(DatabaseConnection.getConnection());
	AuthorizationDAO authorizationDAO = new AuthorizationDAO(DatabaseConnection.getConnection());
	public ServletContext servletContext;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authorizationListController() {
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
        String id = request.getParameter("id");
        
        Authorization authorization = authorizationDAO.getAuthorizationsById(Integer.parseInt(id));
        Teacher teacher = teacherDAO.getTeacherById(authorization.getTeacher_id());
		authorization.setTeacher(teacher);
		request.setAttribute("authorization", authorization);
		rd = application.getRequestDispatcher("/Authorization.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
