package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.Help;
import help.HelpDAO;
@WebServlet("/EditHelpServlet")
public class EditHelpServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String helpId = request.getParameter("helpId");
        request.setCharacterEncoding("UTF-8");
        // Get the actual help record from the database using the helpId
        HelpDAO helpDAO = new HelpDAO();
        Help helpRecord = helpDAO.getHelpRecord(helpId);

        // Set the record as a request attribute
        request.setAttribute("helpRecord", helpRecord);

        // Forward to the edit page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editHelp.jsp");
        dispatcher.forward(request, response);
    }
}
