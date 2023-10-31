package servlet;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import help.Help;
import help.HelpDAO;
 
@WebServlet("/SubmitHelpServlet")
public class SubmitHelpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in (has an active session)
        HttpSession session = request.getSession(false); // Do not create a new session if it doesn't exist
        request.setCharacterEncoding("UTF-8");
        if (session != null && session.getAttribute("userID") != null) {
            String userID = (String) session.getAttribute("userID");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");

            // Create a Help object with the user input
            Help help = new Help(userID, subject, message); // Use the appropriate constructor

            // Add the help request to the database using HelpDAO
            HelpDAO helpDAO = new HelpDAO();
 
            boolean isSuccess = helpDAO.addHelp(help);
            
	    if (isSuccess) {
                // Request successfully added to the database
                helpDAO.closeConnection(); // Close resources

                // Redirect to the HelpListServlet
                response.sendRedirect(request.getContextPath() + "/HelpListServlet");
                return; // Ensure that the servlet response is terminated after the redirection
	    } else {
		helpDAO.closeConnection(); // Close resources

		// Handle error here if needed
	    }
        }
    }
}
