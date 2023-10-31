/*
 * package servlet;
 * 
 * import java.io.IOException; import java.io.PrintWriter;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
 * import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import product.Product; import product.ProductDAO; import user.User; import
 * user.UserDAO;
 * 
 * @WebServlet("/PurchaseServlet") public class PurchaseServlet extends
 * HttpServlet { private static final long serialVersionUID = 1L;
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { String productIdStr =
 * request.getParameter("productID"); int productId =
 * Integer.parseInt(productIdStr); // 문자열을 정수로 변환
 * 
 * // 사용자가 입력한 정보 받아오기 String userName = request.getParameter("userName");
 * String userEmail = request.getParameter("userEmail"); String userAddress =
 * request.getParameter("userAddress"); String userPhone =
 * request.getParameter("userPhone");
 * 
 * HttpSession session = request.getSession();
 * 
 * String userId = (String) session.getAttribute("userId");
 * 
 * ProductDAO productDao = new ProductDAO(); UserDAO userDao = new UserDAO();
 * 
 * try { Product product =
 * productDao.getProductByIdWithStockQuantity(productId); User user =
 * userDao.getUserByID(userId);
 * 
 * // 결과 메시지 준비 String message;
 * 
 * if (product != null && user != null) { // Check stock quantity if
 * (product.getStockQuantity() > 0) { // Reduce the stock quantity by 1
 * product.setStockQuantity(product.getStockQuantity() - 1); // Update the
 * database with the new stock quantity productDao.updateProduct(product);
 * 
 * // Update user information with the input data user.setName(userName);
 * user.setEmail(userEmail); user.setAddress(userAddress);
 * user.setPhone(userPhone);
 * 
 * // Update the database with the new user information
 * userDao.updateUser(user);
 * 
 * // 업데이트된 User 객체를 세션에 저장 session.setAttribute("user", user);
 * 
 * message = "제품 '" + product.getProductName() + "'을(를) 성공적으로 구매했습니다. 배송지: " +
 * userAddress; } else { message = "구매 실패: 제품 '" + product.getProductName() +
 * "'의 재고가 없습니다."; }
 * 
 * request.setAttribute("message", message); RequestDispatcher dispatcher =
 * request.getRequestDispatcher("/purchase_result.jsp");
 * dispatcher.forward(request, response);
 * 
 * } else { message = "구매 실패: 제품 또는 사용자 정보가 잘못되었습니다.";
 * 
 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
 * response.getWriter();
 * 
 * out.println("<html><body>"); out.println("<h1>" + message + "</h1>");
 * out.println("</body></html>"); out.close(); }
 * 
 * } catch (Exception e) { throw new ServletException(e); } }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { doPost(request, response); }
 * }
 */