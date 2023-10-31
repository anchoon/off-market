import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;
import user.UserDAO;

@WebServlet("/UpdateMyPageServlet")
public class UpdateMyPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자가 입력한 정보 가져오기
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String userGender = request.getParameter("userGender");
        String userEmail = request.getParameter("userEmail");
        String userAddress = request.getParameter("userAddress");
        String userPhone = request.getParameter("userPhone");

        // 업데이트할 사용자 객체 생성 
        User updatedUser = new User(userID, userPassword, userName, userGender, userEmail, userAddress, userPhone);

        // 사용자 정보 업데이트
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.updateUser(updatedUser);

        if (success) {
            // 업데이트 성공 시 마이페이지로 이동
            response.sendRedirect("mypage.jsp"); 
        } else {
            // 업데이트 실패 시 에러 메시지 표시
            response.getWriter().write("업데이트에 실패했습니다.");
        }
    }
}
 