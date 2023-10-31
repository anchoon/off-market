package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.BbsDAO;
import bbs.Bbs;


@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		
		// 세션에서 사용자 아이디를 가져옵니다.
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		// 게시글을 데이터베이스에 저장하는 로직을 구현합니다.
		// 예시로 UserDAO의 write 메서드를 사용합니다.
		// 이전에 UserDAO를 사용했던 부분을 BbsDAO로 수정
		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.write(userID, bbsTitle, bbsContent);

		// ... 이하 생략


		if (result == 1) {
			// 게시글 작성 성공 시 처리
			// 작성 성공 메시지를 보여주고 메인 페이지로 리다이렉트
			request.setAttribute("successMessage", "게시글이 성공적으로 작성되었습니다.");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			// 게시글 작성 실패 시 처리
			// 실패 메시지를 보여주고 이전 페이지로 리다이렉트
			request.setAttribute("errorMessage", "게시글 작성 중 오류가 발생했습니다.");
			request.getRequestDispatcher("write.jsp").forward(request, response);
		}
	}
}
