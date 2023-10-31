<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="help.Help"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도움말 요청 수정</title>

<!-- 부트스트랩 4.0 CSS 링크 추가 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- 추가적인 CSS 파일 링크 (예: custom.css) -->
<link rel="stylesheet" href="/css/custom.css">
</head>
<body>
	<div class="container mt-5">
		<h1>도움말 요청 수정</h1>

		<!-- Edit form -->
		<form action="/UpdateHelpServlet" method="post">
			<input type='hidden' name='helpId' value='${help.helpID}' />
			<div class="form-group">
				<label for="userId">User ID</label> <input type="text"
					class="form-control" id="userId" name="userId"
					value="${help.userID}" required />
			</div>
			<div class="form-group">
				<label for="subject">Subject</label> <input type="text"
					class="form-control" id="subject" name="subject"
					value="${help.subject}" required />
			</div>
			<div class="form-group">
				<label for="message">Message</label>
				<textarea class="form-control" id="message" name="message" rows="3">${help.message}</textarea>
			</div>
			<button type="submit" class="btn btn-primary">저장</button>
		</form>
	</div>
	<!-- 부트스트랩 4.0 및 jQuery 및 Popper.js 스크립트 추가 -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
</html>
