package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // productID 파라미터를 가져옵니다.
        String productID = request.getParameter("productID");
        
        // TODO: 선택한 상품을 사용자의 장바구니에 추가하는 로직을 작성하세요.
        // (예를 들어, 사용자의 장바구니 데이터를 업데이트하는 등의 작업)
        
        // 장바구니에 추가가 성공하면 장바구니 추가 성공 페이지로 리다이렉트합니다.
        response.sendRedirect("add_to_cart_success.jsp");
    }
}
