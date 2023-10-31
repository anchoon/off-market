
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="help.Help"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도움말 요청 목록</title>

<!-- 부트스트랩 4.0 CSS 링크 추가 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- 추가적인 CSS 파일 링크 (예: custom.css) -->
<link rel="stylesheet" href="/css/custom.css">
<style>
.btn-yellow {
    background-color: yellow;
    color: #000;
}

.reply-content {
    margin-top: 10px;
    background-color: #f9f9f9;
    padding: 10px;
}

.reply-content p {
    font-weight: bold;
}
</style>

</head>
<body>

	<!-- 네비게이션 바 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Your Logo</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="main.jsp">Home<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="bbs.jsp">자유게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="productlist.jsp">상품목록</a></li>
				<li class="nav-item"><a class="nav-link" href="#">문의</a></li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>User ID</th>
					<th>Subject</th>
					<th>Message</th>
					<th>Timestamp</th>
					<th>Edit Help Request</th>
					<th>Delete Help Request</th>
					<th>Add Reply</th>
				</tr>
			</thead>
			<tbody>
				<!-- Use JSTL to iterate over the help records and display them in the table -->
				<c:forEach var="help" items="${helpList}">
					<tr>
						<td>${help.helpID}</td>
						<td>${help.userID}</td>
						<td>${help.subject}</td>
						<td>${help.message}</td>
						<td><fmt:formatDate value="${help.timestamp}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<!-- Edit button -->
						<td>
							<button type="button" class="btn btn-primary edit-button"
								data-helpid="${help.helpID}">수정</button>
							<form action="UpdateHelpServlet" method="post"
								style="display: none;" class="edit-form">
								<input type='hidden' name='helpId' value='${help.helpID}' />

								<div class="form-group">
									<label for="subject">Subject</label> <input type="text"
										class="form-control" id="subject" name="subject"
										value="${help.subject}" required />
								</div>
								<div class="form-group">
									<labelfor"message">Message</label> <textarea
										class="form-control" id="message" name="message" rows="3">${help.message}</textarea>
								</div>
								<!-- Add other input fields as needed -->
								<input type='submit' value='저장' />
							</form>
						</td>
						<!-- Delete button -->
						<td>
							<form action="DeleteHelpServlet" method="post">
								<input type="hidden" name="helpId" value="${help.helpID}">
								<button type="submit" class="btn btn-danger"
									onclick="return confirm('삭제하시겠습니까?');">삭제</button>

							</form>
						</td>

						<td>
							<form action="AddReplyServlet" method="post">
								<input type="hidden" name="helpId" value="${help.helpID}">
								<textarea class="form-control mb-2" id="message"
									name="replyContent" rows="1"></textarea>

								<!-- Display the reply content if it exists -->
								<c:if test="${not empty help.replyContent}">
									<div class="reply-content">
										<p>${help.replyContent}</p>
									</div>
								</c:if>

								<!-- Check if reply is already added and hide the button -->
								<c:choose>
									<c:when test="${not empty help.replyContent}">
										<%-- Reply already added, hide the button --%>
										<%-- You can also use CSS to hide the button --%>
									</c:when>
									<c:otherwise>
										<%-- Reply not added, show the button --%>
										<button type="submit" class="btn btn-yellow">꼬리말 달기</button>
									</c:otherwise>
								</c:choose>

							</form>
						</td>




					</tr>
				</c:forEach>
				<!-- Check if helpList is empty and display a message if it is -->
			</tbody>
		</table>
	</div>

	<!-- 부트스트랩 4.0 및 jQuery 및 Popper.js 스크립트 추가 -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.edit-button').click(function() {
				// Show the form next to the button
				$(this).next('.edit-form').show();
			});
		});
	</script>
</body>
</html>
