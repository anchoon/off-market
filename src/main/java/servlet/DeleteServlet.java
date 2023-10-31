package servlet;

import bbs.BbsDAO;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bbs.BbsDAO;
import bbs.Bbs;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션에서 사용자 아이디를 가져옵니다.
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		if (userID == null) {
			// 로그인되지 않은 사용자에게 경고 메시지를 보여주고 로그인 페이지로 이동
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
		} else {
			int bbsID = 0;
			if (request.getParameter("bbsID") != null) {
				bbsID = Integer.parseInt(request.getParameter("bbsID"));
			}
			if (bbsID == 0) {
				// 유효하지 않은 글일 경우 경고 메시지를 보여주고 이전 페이지로 이동
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('유효하지 않은 글입니다');");
				script.println("location.href = 'bbs.jsp';");
				script.println("</script>");
			} else {
				BbsDAO bbsDAO = new BbsDAO();
				Bbs bbs = bbsDAO.getBbs(bbsID);

				if (!userID.equals(bbs.getUserID())) {
					// 권한이 없는 사용자에게 경고 메시지를 보여주고 이전 페이지로 이동
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('권한이 없습니다');");
					script.println("location.href = 'bbs.jsp';");
					script.println("</script>");
				} else {
					int result = bbsDAO.delete(bbsID);

					if (result == -1) {
						// 글 삭제에 실패한 경우
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('글 삭제에 실패했습니다.');");
						script.println("history.back();");
						script.println("</script>");
					} else {
						// 글 삭제에 성공한 경우
						response.sendRedirect("bbs.jsp");
					}
				}
			}
		}
	}
}
