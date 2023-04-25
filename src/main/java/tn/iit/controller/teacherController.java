package tn.iit.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


import tn.iit.DAO.DatabaseConnection;
import tn.iit.DAO.TeacherDAO;
import tn.iit.model.Teacher;

/**
 * Servlet implementation class teacherServlet$
 */
@WebServlet({"", "/import"})
@MultipartConfig
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
		
	    String id = request.getParameter("id");
        if (id != null) {
           doPost(request,response);
        }else {
		rd = application.getRequestDispatcher("/Teacher.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servletContext = getServletContext();
        String id = request.getParameter("id");
        if (id != null) {
        	 teacherDAO.deleteTeacher(Integer.parseInt(id));
        }
        else {
		ServletFileUpload upload = new ServletFileUpload();
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		String filePath = getServletContext().getRealPath("/WEB-INF/uploads/" + fileName);
		filePart.write(filePath);
		boolean success = teacherDAO.importTeachers(filePath);
        }
		response.sendRedirect("/ExamProject/");
	}

}
