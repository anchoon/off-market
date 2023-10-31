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
<title>Product Detail</title>
<style>
/* 컨테이너 스타일 */
.product-detail {
	text-align: center;
	padding: 20px;
}

/* 이미지 스타일 */
.product-image {
	width: 400px; /* 원하는 크기로 조정 */
	height: 400px; /* 원하는 크기로 조정 */
	object-fit: cover; /* 이미지 크기 유지 및 가운데 정렬 */
}
</style>
</head>
<body>
	<h1>Product Detail</h1>

	<!-- 상품 정보 가져오기 -->
	<c:set var="product" value="${requestScope.product}" />

	<div class="product-detail">
		<!-- 상품 이미지 표시 -->
		<img
			src="${pageContext.request.contextPath}/upload/${product.productImage}"
			alt="Product Image" class="product-image">

		<!-- 상품 정보 표시 -->
		<h2>Product Name: ${product.productName}</h2>
		<p>Product Price: ${product.productPrice}</p>
		<p>Product Description: ${product.productDescription}</p>
		<!-- "구매하기" 버튼 추가 -->
		<form action="userInformation.jsp" method="post">
			<input type="hidden" name="productId" value="${product.productID}">
			<input type="submit" value="구매하기">
		</form>

		<c:if test="${sessionScope.isAdmin}">
			<form method="post" action="ProductDeleteServlet">
				<input type="hidden" name="productID" value="${product.productID}">
				<input type="submit" value="삭제">
			</form>
		</c:if>
	</div>

	<p>
		<a href="main.jsp">Back to Main Page</a>
	</p>
</body>
<script>
	function confirmDelete() {
		return confirm("관리자 권한으로 삭제하겠습니까?");
	}
</script>
</html>
