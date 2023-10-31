
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>온라인 스토어</title>
<!-- 부트스트랩 3.0 CSS 링크 추가 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 추가적인 CSS 파일 링크 (예: custom.css) -->
<link rel="stylesheet" href="css/custom.css">

<style>
    body {
        background-image: url('upload/offline.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
    }
 
    .message {
        font-size: 24px;
        font-weight: bold;
        color: green;
        text-align: center;
        padding: 10px;
        background-color: #f0f0f0;
    }
</style>


</head>
<body>
	<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	%>
	<%
	if (session.getAttribute("userID") != null) {
	%>
	<p><%=session.getAttribute("welcomeMessage")%></p>
	<%
	} else {
	%>
	<p>로그인이 필요합니다.</p>
	<%
	}
	%>
<!-- main.jsp -->
<%-- Check if there's a message in the request attribute and display it --%>
<% if (request.getAttribute("message") != null) { %>
    <div id="message" class="message">
        <%= request.getAttribute("message") %>
        <button onclick="hideMessage()">CLEAR</button>
    </div>
<% } %>

<!-- Rest of your main.jsp content -->
 

	<!-- 네비게이션 바 -->
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.jsp">OFFLINE</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="main.jsp">메인</a></li>
					<li><a href="bbs.jsp">게시판</a></li>
					<li><a href="ProductListServlet">상품 목록</a></li>
					<li><a href="product_upload.jsp">상품 등록</a></li>
					<li><a href="help.jsp">고객센터</a></li>
					<li><a href="HelpListServlet">문의게시판</a></li>
					<c:if test="${sessionScope.isAdmin}">
						<li><a href="userList.jsp">회원 목록</a></li>
					</c:if>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
					if (userID == null) {
					%>
					<!-- 세션 없을 때 (로그인 버튼 표시) -->
					<li><a href="login.jsp">로그인</a></li>
					<li><a href="join.jsp">회원 가입</a></li>
					<%
					} else {
					%>
					<li class="dropdown">
						<form action="LogoutServlet" method="post">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">회원
								관리 <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="MyPageServlet">마이페이지</a></li>

								<li><a href="orderSuccess.jsp">구매내역</a></li>

								<li class="divider"></li>
								<li><button type="submit" class="btn-link">로그아웃</button></li>
							</ul>
						</form>
					</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>


	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- 인디케이터 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">이전</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">다음</span>
			</a>
		</div>
	</div>

<script>
function hideMessage() {
    var messageDiv = document.getElementById("message");
    if (messageDiv) {
        messageDiv.style.display = "none";
    }
}
</script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
