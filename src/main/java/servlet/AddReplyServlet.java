package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import help.HelpDAO;

@WebServlet("/AddReplyServlet")
public class AddReplyServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the help ID and reply content from the request
		// Get the help ID and reply content from the request
		String helpId = request.getParameter("helpId");
		String replyContent = request.getParameter("replyContent");

		// Validate helpId and replyContent
		if (helpId == null || helpId.isEmpty() || replyContent == null || replyContent.isEmpty()) {
			// Handle the case where help ID or reply content is empty
			response.getWriter().println("Help ID and reply content are required.");
		} else {
			// Proceed to add the reply
			// Create an instance of HelpDAO to interact with the database
			HelpDAO helpDAO = new HelpDAO();

			// Add the reply to the help record
			boolean isSuccess = helpDAO.addReply(Integer.parseInt(helpId), "admin", replyContent);

			// Close the HelpDAO
			helpDAO.closeConnection();

			if (isSuccess) {
				// Reply added successfully
				// Redirect back to the help list page or wherever you want
				response.sendRedirect("HelpListServlet");
			} else {
				// Handle the case where the reply was not added
				response.getWriter().println("Failed to add the reply.");
			}
		}
	}
}