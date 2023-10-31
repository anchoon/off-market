package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;
import user.User;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 사용자가 입력한 아이디와 비밀번호를 가져옵니다.
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        request.setCharacterEncoding("UTF-8");

        // 하드코딩된 관리자 계정 정보
        String adminID = "admin";
        String adminPassword = "1234";

        // UserDAO를 사용하여 로그인 처리를 합니다.
        UserDAO userDAO = new UserDAO();

        if (userID.equals(adminID) && userPassword.equals(adminPassword)) {
            // 관리자 계정으로 로그인 성공 시 처리
            HttpSession session = request.getSession();
            session.setAttribute("userID", adminID);
            session.setAttribute("isAdmin", true); // 관리자임을 나타내는 플래그 설정
            session.setAttribute("welcomeMessage", "관리자로 로그인하셨습니다."); // 관리자용 환영 메시지
            response.sendRedirect("main.jsp");
        } else {
            int result = userDAO.login(userID, userPassword);

            if (result == 1) { 
                // 로그인 성공 시 처리 (일반 사용자)
                User user = new User();
                user.setUserID(userID);
                user.setUserName(userDAO.getUserName(userID));
                user.setUserEmail(userDAO.getUserEmail(userID));
                user.setUserAddress(userDAO.getUserAddress(userID));
                user.setUserPhone(userDAO.getUserPhone(userID));

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userID", userID);
                session.setAttribute("welcomeMessage", "환영합니다, " + user.getUserName() + "님."); // 사용자용 환영 메시지
                response.sendRedirect("main.jsp");
            } else { 
                // 로그인 실패 시 처리
                String errorMessage = "로그인에 실패하였습니다.";
                if (result == 0) {
                    errorMessage = "비밀번호가 틀립니다.";
                } else if (result == -1) {
                    errorMessage = "존재하지 않는 아이디입니다.";
                } else if (result == -2) {
                    errorMessage = "DB 오류가 발생했습니다.";
                }

                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
