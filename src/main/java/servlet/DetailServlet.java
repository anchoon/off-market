package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.ProductDAO;
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productID");
        request.setCharacterEncoding("UTF-8");
        if (productIdStr != null && !productIdStr.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdStr);

                // Implement the logic to fetch product details based on productId.
                // For example, you can use the ProductDAO to get the product details.
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProductById(productId);


                // Set the product attribute to make it available in the JSP
                request.setAttribute("product", product);

                // Forward to the detail JSP page for this product
                request.getRequestDispatcher("/detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where the 'productID' parameter is not a valid integer.
                // You might want to display an error message to the user.
                e.printStackTrace(); // for debugging purposes
            }
        } else {
            // If 'productID' parameter is missing or empty, redirect to the product list page.
            response.sendRedirect(request.getContextPath() + "/productlist.jsp");
        }
    }
}
