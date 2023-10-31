
<%@ page import="java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="product.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buy Product</title>
<style>
/* 스타일을 원하는 대로 추가하세요 */
body {
	font-family: Arial, sans-serif;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

.product {
	border: 1px solid #ddd;
	padding: 20px;
	margin-bottom: 20px;
}

.product img {
	max-width: 100%;
	height: auto;
}

.product-details {
	margin-top: 20px;
}

.product-details h2 {
	font-size: 24px;
	margin-bottom: 10px;
}

.product-details p {
	margin-bottom: 5px;
}

.purchase-form {
	margin-top: 20px;
	border-top: 1px solid #ddd;
	padding-top: 20px;
}

.purchase-form label {
	display: block;
	margin-bottom: 5px;
}

.purchase-form input[type="text"], .purchase-form input[type="number"],
	.purchase-form select {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

.purchase-form input[type="submit"] {
	background-color: #007BFF;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Buy Product</h1>

		<c:if test="${product != null}">
			<div class="product">
				<img
					src="${pageContext.request.contextPath}/upload/${product.productImage}"
					alt="Product Image">
				<div class="product-details">
					<h2>${product.productName}</h2>
					<p>Product Price: $${product.productPrice}</p>
					<p>Product Description: ${product.productDescription}</p>
					<!-- 추가된 부분: Product Size 표시 -->
				</div>
			</div>

			<div class="purchase-form">
				<h2>Purchase Information</h2>
				
				<form action="PurchaseServlet" method="post">
					<input type="hidden" name="productID" value="${product.productID}">
					<label for="quantity">수량:</label> <input type="number"
						id="quantity" name="quantity" min="1" required>
					<!-- 수정된 부분: Product Size 선택 옵션을 표시하는 조건 추가 -->
					<c:if test="${not empty product.productSizes}">
						<label for="productSize"> Size:</label>
						<select id="productSize" name="productSize" required>
							<c:forEach var="size" items="${product.productSizes}">
								<option value="${size}">${size}</option>
							</c:forEach>
						</select>
					</c:if>
					<input type="submit" value="Purchase">
				</form>
			</div>

		</c:if>
	</div>

	<p>
		<a href="main.jsp">Back to Main Page</a>
	</p>
</body>
</html>
