<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>

<!-- Add Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="text-center">
			<a href="main.jsp" class="btn btn-primary">홈으로 돌아가기</a> <a
				href="help.jsp" class="btn btn-info">고객센터로 가기</a>
		</div>

		<h1 class="text-success">주문이 완료되었습니다</h1>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>주문 정보 및 사용자 정보:</h2>
			</div>
			<div class="panel-body">
				<p>
					<strong>사용자 아이디:</strong> ${order.userID}
				</p>
				<p>
					<strong>결제 방법:</strong> ${order.paymentMethod}
				</p>
				<p>
					<strong>배송 날짜:</strong> ${order.formattedDeliveryDate}
				</p>
				<p>
					<strong>이름:</strong> ${user.userName}
				</p>
			</div>
		</div>


		<%-- <div class="panel panel-default">
			<div class="panel-heading">
				<h2>상품 정보:</h2>
			</div>
			<div class="panel-body">
				<c:if test="${not empty product.productName}">
					<p>
						<strong>상품 이름:</strong> ${product.productName}
					</p>
				</c:if>

				<c:if test="${not empty product.productImage}">
					<img src="${product.productImage}" alt="상품 이미지"
						class="img-thumbnail">
				</c:if>

				<c:if test="${not empty product.productPrice}">
					<p>
						<strong>가격:</strong> ${product.productPrice}
					</p>
				</c:if>
			</div>
		</div>
 --%>

		<div class="well text-center">
			<p>
				<strong><h1>접수내역 확인후 빠르게 배송해드립니다.</h1></strong>
			</p>
		</div>
	</div>

	<!-- Add Bootstrap JS (for optional functionality) -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
