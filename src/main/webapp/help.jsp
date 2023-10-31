<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>문의게시판</title>

<!-- 부트스트랩 4.0 CSS 링크 추가 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 추가적인 CSS 파일 링크 (예: custom.css) -->
<link rel="stylesheet" href="css/custom.css">
</head>
<body>

	<!-- 네비게이션 바 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="#">문의게시판</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="main.jsp">홈</a></li>

					<!-- 로그인 버튼 -->
					<c:if test="${empty sessionScope.userID}">
						<li><a href="login.jsp">로그인</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-5">
		<h2>문의게시판</h2>

		<!-- Check if the user is logged in -->
		<c:if test="${not empty sessionScope.userID}">
			<!-- Help Request Submission Form -->
			<form action="SubmitHelpServlet" method="post">
				<!-- 수정된 부분 -->
				<div class="form-group">
					<label for="subject">Subject:</label> <select class="form-control"
						id="subject" name="subject" required>
						<option value="">-- 문의 주제 선택 --</option>
						<option value="상품 문의">상품 문의</option>
						<option value="주문 및 결제 문의">주문 및 결제 문의</option>
						<option value="배송 및 배송 조회">배송 및 배송 조회</option>
						<option value="반품 및 교환 문의">반품 및 교환 문의</option>
						<option value="회원 및 계정 관리">회원 및 계정 관리</option>
					</select>


				</div>

				<div class="form-group">
					<label for="message">Message:</label>
					<textarea class="form-control" id="message" name="message" rows="4"
						required></textarea>
				</div>
				 
				
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</c:if>

		<c:if test="${empty sessionScope.userID}">
			<p>You must be logged in to post a message.</p>
		</c:if>
	</div>

	<!-- 부트스트랩 4.0 및 jQuery 및 Popper.js 스크립트 추가 -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
