package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import user.UserDAO;
 
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 회원 정보를 받아오기
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		String userAddress = request.getParameter("userAddress");
		String userPhone = request.getParameter("userPhone");

		// User 객체 생성
		User user = new User();
		user.setUserID(userID);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		user.setUserGender(userGender);
		user.setUserEmail(userEmail);
		user.setUserAddress(userAddress);
		user.setUserPhone(userPhone);

		// UserDAO를 사용하여 회원 가입 처리
		UserDAO userDAO = new UserDAO();
		int result = userDAO.join(user);
		if (result == 1) {
			// 회원 가입 성공 시
			request.setAttribute("message", "회원 가입이 완료되었습니다.");
		} else {
			// 회원 가입 실패 시
			request.setAttribute("errorMessage", "회원 가입에 실패했습니다.");
		}

		// 회원 가입 성공 또는 실패 메시지를 설정한 후에 join.jsp로 포워딩
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
