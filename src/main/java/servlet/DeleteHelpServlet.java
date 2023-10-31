package servlet;
//DeleteHelpServlet.java
import javax.servlet.*;
import javax.servlet.http.*;

import help.HelpDAO;

import java.io.IOException;
import javax.servlet.annotation.*;


@WebServlet("/DeleteHelpServlet")
public class DeleteHelpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String helpId = request.getParameter("helpId");
        request.setCharacterEncoding("UTF-8");
        // TODO: Validate the input

        // Delete the help record in the database using the provided ID
        HelpDAO helpDAO = new HelpDAO();
        boolean success = helpDAO.deleteHelpRecord(helpId);

        if (success) {
            // Redirect back to the help list page after deletion
            response.sendRedirect("/test/HelpListServlet");
        } else {
            // Redirect to an error page or show an error message
            response.sendRedirect("/error.jsp");  // This is just an example; replace with your actual error handling code
         }
    }
}

