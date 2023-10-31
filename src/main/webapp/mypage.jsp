<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.User"%>
<%@ page import="user.UserDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JSP 게시판 웹 사이트 - 마이페이지</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<c:if test="${not empty sessionScope.message}">
		<div class="alert alert-success" role="alert">
			${sessionScope.message}</div>
	</c:if>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">

						<h3 class="card-title text-center">마이페이지</h3>
						<!-- 세션에서 가져온 회원 정보를 표시 -->
						<form method="post" action="MyPageServlet">
							<div class="form-group">
								<label for="userID">아이디</label>
								<!-- Access user data from request -->
								<input type="text" class="form-control" id="userID"
									name="userID" value="${currentUser.userID}" readonly>
							</div>
							<div class="form-group">
								<label for="userPassword">비밀번호</label> <input type="password"
									class="form-control" id="userPassword" name="userPassword"
									maxlength="20" required>
								<div class="form-group">
									<label for="newUserPassword">새로운 비밀번호</label> <input
										type="password" class="form-control" id="newUserPassword"
										name="newUserPassword" maxlength="20" required>
								</div>

							</div>
							<div class="form-group">
								<label for="userName">이름</label>
								<!-- Access user data from request -->
								<input type="text" class="form-control" id="userName"
									name="userName" maxlength="20" value="${currentUser.userName}"
									required>
							</div>
							<div class="form-group">
								<label for="userGender">성별</label> <select class="form-control"
									id="userGender" name="userGender">
									<option value="남자"
										${currentUser.userGender == '남자' ? 'selected' : ''}>남자</option>
									<option value="여자"
										${currentUser.userGender == '여자' ? 'selected' : ''}>여자</option>
								</select>
							</div>
							<div class="form-group">
								<label for="userEmail">이메일</label>
								<!-- Access user data from request -->
								<input type="email" class="form-control" id="userEmail"
									placeholder="이메일" name="userEmail" maxlength="50"
									value="${currentUser.userEmail}">
							</div>
							<div class="form-group">
								<label for="userAddress">주소</label>
								<!-- Access user data from request -->
								<input type="text" class="form-control" id="userAddress"
									placeholder="주소" name="userAddress" maxlength="100"
									value="${currentUser.userAddress}">
							</div>
							<div class="form-group">
								<label for="userPhone">전화번호</label>
								<!-- Access user data from request -->
								<input type="text" class="form-control" id="userPhone"
									placeholder="전화번호" name="userPhone" maxlength="20"
									value="${currentUser.userPhone}">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">정보
									수정</button>
							</div>
							<div class="text-center">
								<a href="main.jsp" class="btn btn-primary">홈으로</a>
							</div>



						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
    // 서블릿에서 설정한 메시지를 가져와서 표시
    var infoMessage = "<%=request.getAttribute("infoMessage")%>
		";

		if (infoMessage) {
			// 정보 변경 완료 메시지가 설정된 경우
			document.getElementById("infoMessage").innerHTML = infoMessage;
			document.getElementById("infoMessage").style.display = "block";
		}
	</script>
</body>
</html>
