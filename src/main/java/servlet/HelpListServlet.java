package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.Help;
import help.HelpDAO;
 
@WebServlet("/HelpListServlet")
public class HelpListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        // Get the list of all help records from the database using HelpDAO
        HelpDAO helpDAO = new HelpDAO();
        List<Help> helpList = helpDAO.getAllHelpRecords();

        // Set the list as a request attribute
        request.setAttribute("helpList", helpList);

        // Forward the request to the helplist.jsp page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/helplist.jsp");
        dispatcher.forward(request, response);
        
        
    }
}
