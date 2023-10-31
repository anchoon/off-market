package servlet;

import java.io.IOException;
import java.sql.Date;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.Order;
import order.OrderDAO;
import product.Product;

@WebServlet("/SaveUserInformationServlet")
public class SaveUserInformationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Get user information from the session
        String userID = (String) request.getSession().getAttribute("userID");

        // Get form data
        String paymentMethod = request.getParameter("paymentmethod");

        // Parse the date in the "yyyy-MM-dd" format
        String deliveryDateString = request.getParameter("deliverydate");
        deliveryDateString = deliveryDateString.replace('/', '-'); // Convert "/" to "-"
        Date deliveryDate = Date.valueOf(deliveryDateString);

        String bankName = "";
        String accountNumber = "";
        String cardName = "";
        String cardNumber = "";

        if ("은행".equals(paymentMethod)) {
            bankName = request.getParameter("bankname");
            accountNumber = request.getParameter("accountnumber");
        } else if ("카드".equals(paymentMethod)) {
            cardName = request.getParameter("cardname");
            cardNumber = request.getParameter("cardnumber");
        }

        // Create a Product object
        Product product = new Product();
        product.setProductName("상품 이름");
        product.setProductImage("상품 이미지 URL");
        product.setProductPrice(new BigDecimal("1000.00"));

        // Set the product information in the request attribute
        request.setAttribute("product", product); 

        // Create an OrderDAO object
        OrderDAO orderDAO = new OrderDAO();

        // Create an Order object
        Order order = new Order(userID, paymentMethod, deliveryDate, bankName, accountNumber, cardName, cardNumber);

        // Insert the order into the database
        orderDAO.insertOrder(order);

        // Close the database connection
        orderDAO.close();

        request.getSession().setAttribute("order", order);

        request.getRequestDispatcher("/orderSuccess.jsp").forward(request, response);
    }
} 
