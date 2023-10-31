package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;
import user.UserDAO;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");// 세션에서 현재 로그인한 사용자의 정보 가져오기
		User currentUser = (User) request.getSession().getAttribute("user");
		
		if (currentUser != null) {
			// UserDAO를 사용하여 DB에서 사용자 정보 가져오기
			UserDAO userDAO = new UserDAO();
			User dbUser = userDAO.getUserByID(currentUser.getUserID());

			if (dbUser != null) {
				// 가져온 사용자 정보를 "currentUser"라는 이름으로 request에 설정
				request.setAttribute("currentUser", dbUser);
			}
		}

		// "mypage.jsp"로 포워딩
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        // 세션에서 현재 로그인한 사용자의 정보 가져오기
	        User currentUser = (User) request.getSession().getAttribute("user");

	        if (currentUser != null) {
	            // 사용자가 제출한 정보 가져오기
	            String userName = request.getParameter("userName");
	            String userGender = request.getParameter("userGender");
	            String currentPassword = request.getParameter("userPassword"); // 현재 비밀번호
	            String newPassword = request.getParameter("newUserPassword"); // 새로운 비밀번호

	            // UserDAO를 사용하여 데이터베이스에서 현재 사용자 정보 가져오기
	            UserDAO userDAO = new UserDAO();
	            User dbUser = userDAO.getUserByID(currentUser.getUserID());

	            if (dbUser != null && dbUser.getUserPassword().equals(currentPassword)) {
	                // 현재 비밀번호가 일치하는 경우에만 비밀번호를 변경합니다.
	                // 사용자 정보 업데이트
	                currentUser.setUserName(userName);
	                currentUser.setUserGender(userGender);
	                currentUser.setUserPassword(newPassword); // 새로운 비밀번호 설정

	                // UserDAO를 사용하여 데이터베이스에 사용자 정보 업데이트
	                boolean updated = userDAO.updateUserProfile(currentUser);

	                if (updated) {
	                    // 업데이트 성공
	                    request.getSession().setAttribute("message", "정보가 성공적으로 업데이트되었습니다.");
	                } else {
	                    // 업데이트 실패
	                    request.getSession().setAttribute("errorMessage", "정보 업데이트에 실패했습니다. 다시 시도하세요.");
	                }
	            } else {
	                // 현재 비밀번호가 일치하지 않는 경우
	                request.getSession().setAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 처리: 데이터베이스 연결 문제 등에 대한 예외 처리를 추가하세요.
	    }

	 // mypage.jsp로 리다이렉트
	    response.sendRedirect("mypage.jsp");

	}
}