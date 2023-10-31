package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.ProductDAO;

@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productID");

        try {
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);

                // Use ProductDAO to delete the product based on productId.
                ProductDAO productDAO = new ProductDAO();
                boolean success = productDAO.deleteProductById(productId);

                if (success) { 
                    request.setAttribute("message", "상품 삭제 완료");
                } else { 
                    request.setAttribute("message", "상품 삭제 실패");
                }
            } else {
                System.out.println("Warning: 'productID' parameter is missing or empty.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
