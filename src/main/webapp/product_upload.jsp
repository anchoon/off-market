<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="product.Product"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 업로드</title>
<!-- Bootstrap CSS 링크 추가 -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* 폼을 화면 중앙에 배치하는 스타일 */
.center-form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh; /* 화면 높이의 100%로 설정하여 세로 중앙 정렬 */
}

/* 홈 버튼 스타일 추가 */
.home-button {
	position: absolute;
	top: 20px;
	left: 20px;
}

/* 페이지 색상 및 스타일 수정 */
body {
	background-color: #f0f0f0;
}

.container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<!-- 홈 버튼 추가 -->
	<a href="main.jsp" class="btn btn-primary home-button">홈</a>

	<div class="container center-form">
		<form action="ProductUploadServlet" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="userID">사용자 ID:</label> <input type="text"
					class="form-control" id="userID" name="userID" required
					value="<%=(session.getAttribute("userID") != null) ? session.getAttribute("userID") : ""%>">
			</div>
			<div class="form-group">
				<label for="productName">제품 이름:</label> <input type="text"
					class="form-control" id="productName" name="productName" required>
			</div>
			<div class="form-group">
				<label for="productDescription">제품 설명:</label>
				<textarea class="form-control" id="productDescription"
					name="productDescription" rows="4" cols="50"></textarea>
			</div>
			<div class="form-group">
				<label for="productPrice">제품 가격:</label> <input type="text"
					class="form-control" id="productPrice" name="productPrice" required>
			</div>
			<div class="form-group">
				<label for="productCategory">제품 카테고리:</label> <input type="text"
					class="form-control" id="productCategory" name="productCategory">
			</div>

			<div class="form-group">
				<label for="productSize">제품 사이즈:</label> <select
					class="form-control" id="productSize" name="productSize">
					<option value="">사이즈 선택</option>
					<option value="Small">Small</option>
					<option value="Medium">Medium</option>
					<option value="Large">Large</option>
				</select>
			</div>

			<div class="form-group">
				<label for="productImage">제품 이미지:</label> <input type="file"
					name="productImage" accept="image/*" required>
			</div>
			<div class="form-group">
				<label for="stockQuantity">재고 수량:</label> <input type="number"
					class="form-control" id="stockQuantity" name="stockQuantity"
					min="0">
			</div>

			<input type="submit" value="제품 업로드" class="btn btn-primary">
		</form>
	</div>
</body>
</html>
