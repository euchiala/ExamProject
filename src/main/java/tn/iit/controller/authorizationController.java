package tn.iit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;

import tn.iit.DAO.DatabaseConnection;
import tn.iit.DAO.TeacherDAO;
import tn.iit.DAO.AuthorizationDAO;
import tn.iit.model.Authorization;
import tn.iit.model.Teacher;

/**
 * Servlet implementation class authorizationController
 */
@WebServlet("/authorizationController")
public class authorizationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeacherDAO teacherDAO = new TeacherDAO(DatabaseConnection.getConnection());
	AuthorizationDAO authorizationDAO = new AuthorizationDAO(DatabaseConnection.getConnection());
	public ServletContext servletContext;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public authorizationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static int getRemainingWeeksOfYear() {
		LocalDate now = LocalDate.now();
		int currentWeek = now.get(WeekFields.ISO.weekOfWeekBasedYear());
		int lastWeek = LocalDate.of(now.getYear(), 12, 31).get(WeekFields.ISO.weekOfWeekBasedYear());
		return lastWeek - currentWeek;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = getServletContext();
		RequestDispatcher rd = null;
		String id = request.getParameter("id");
		
		if (id != null) {
			int authorized_hours = this.getRemainingWeeksOfYear() * 4;
			LocalDate localDate = LocalDate.now();
			Date authorization_date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Teacher teacher = teacherDAO.getTeacherById(Integer.parseInt(id));
			Authorization authorization = new Authorization(teacher, authorization_date, authorized_hours);
			request.setAttribute("authorization", authorization);
			rd = application.getRequestDispatcher("/Authorization.jsp");
			rd.forward(request, response);

		} else {
			List<Authorization> authorizations = authorizationDAO.getAllAuthorizations();
			rd = application.getRequestDispatcher("/authorizationList.jsp");
			request.setAttribute("authorizations", authorizations);
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletContext = getServletContext();
		String id = request.getParameter("teacher_id");			
		Teacher teacher = teacherDAO.getTeacherById(Integer.parseInt(id));
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date authorization_date = null;
		try {
		    String inputDateString = request.getParameter("authorization_date");
		    Date inputDate = inputFormat.parse(inputDateString);
		    String outputDateString = outputFormat.format(inputDate);
		    authorization_date = outputFormat.parse(outputDateString);
		} catch (ParseException e) {
		    // Handle the exception if the date string is not in the correct format
		}
		
        String authorizedHoursParam = request.getParameter("authorized_hours");
		int authorized_hours = 0;
        if (authorizedHoursParam != null && !authorizedHoursParam.isEmpty()) {
            try {
            	authorized_hours = Integer.parseInt(authorizedHoursParam);
            } catch (NumberFormatException e) {
                // handle the case where the phone parameter is not a valid integer
            }
        }
        
	    Authorization authorization = new Authorization(teacher, authorization_date, authorized_hours);
	    authorizationDAO.addAuthorization(authorization);
        response.sendRedirect("/ExamProject/");

	}

}
