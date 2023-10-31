package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.HelpDAO;

/**
 * Servlet implementation class UpdateHelpServlet
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateHelpServlet")
public class UpdateHelpServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String helpId = request.getParameter("helpId");
		String userId = "fixedUserId"; // Set the fixed user ID here
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		request.setCharacterEncoding("UTF-8");
		// TODO: Validate the input

		// Update the help record in the database using the provided data
		HelpDAO helpDAO = new HelpDAO();
		boolean success = helpDAO.updateHelpRecord(helpId, userId, subject, message);

		if (success) {
			// Redirect back to the help list page
			response.sendRedirect("/test/HelpListServlet");
		} else {
			// Redirect to an error page or show an error message
			response.sendRedirect("/error.jsp"); // This is just an example; replace with your actual error handling
													// code
		}
	}
}
