<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>결제 페이지</title>
<h1> 결제 화면</h1>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
</head>
<style>
body {
	background-color: #f8f9fa;
}

.container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-group {
	margin-bottom: 20px;
}

.hidden {
	display: none;
}
</style>
  
    
 
  <div class="d-flex justify-content-end">
    <a href="javascript:history.back()" class="btn btn-secondary">뒤로가기</a>
    <a href="<%=request.getContextPath()%>/help.jsp" class="btn btn-secondary">고객센터</a>
    <a href="<%=request.getContextPath()%>/main.jsp" class="btn btn-secondary">홈으로</a>
</div>

    
<body>
 

	
	
		<form action="<%=request.getContextPath()%>/SaveUserInformationServlet"
    class="form-horizontal" method="post">
    <input type="hidden" name="userID" value="${sessionScope.userID}">
		<input type="hidden" name="cartId"
			value="<%=request.getParameter("cartId")%>" /> <input type="hidden"
			name="productId" value="<%=request.getParameter("productId")%>" />

		<div class="form-group">
			<label for="name">성명</label> <input name="name" type="text"
				class="form-control" value="${sessionScope.user.userName}" />
		</div>
		<div class="form-group">
			<label for="phonenumber">전화번호</label> <input name="phonenumber"
				type="text" class="form-control"
				value="${sessionScope.user.userPhone}" />
		</div>
		<div class="form-group">
			<label for="address">주소</label> <input name="address" type="text"
				class="form-control" value="${sessionScope.user.userAddress}" />
		</div>
		<div class="form-group">
			<label for="deliverydate">배송일 (yyyy/mm/dd)</label>
			<div class="input-group">
				<input name="deliverydate" type="text" class="form-control"
					placeholder="yyyy/mm/dd" id="deliverydate" />
				<div class="input-group-append">
					<span class="input-group-text"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="paymentmethod">결제 방식</label>
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-secondary" onclick="showBankInput()">
					<input type="radio" name="paymentmethod" id="bankPayment"
					value="은행" autocomplete="off"> 은행
				</label> <label class="btn btn-secondary" onclick="showCardInput()">
					<input type="radio" name="paymentmethod" id="cardPayment"
					value="카드" autocomplete="off"> 카드
				</label>
			</div>
		</div>

		<div class="form-group hidden" id="bankInput">
			<label for="bankname">은행 이름</label>
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-secondary"> <input type="radio"
					name="bankname" value="국민은행" autocomplete="off"> 국민은행
				</label> <label class="btn btn-secondary"> <input type="radio"
					name="bankname" value="농협은행" autocomplete="off"> 농협은행
				</label> <label class="btn btn-secondary"> <input type="radio"
					name="bankname" value="우리은행" autocomplete="off"> 우리은행
				</label> <label class="btn btn-secondary"> <input type="radio"
					name="bankname" value="부산은행" autocomplete="off"> 부산은행
				</label>
			</div>
			<p>
				<label for="accountnumber">계좌번호</label> <input name="accountnumber"
					type="text" class="form-control" />
			</p>
		</div>
		<div class="form-group hidden" id="cardInput">
			<label for="cardname">카드 이름</label>
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-secondary"> <input type="radio"
					name="cardname" value="삼성카드" autocomplete="off"> 삼성카드
				</label> <label class="btn btn-secondary"> <input type="radio"
					name="cardname" value="롯데카드" autocomplete="off"> 롯데카드
				</label> <label class="btn btn-secondary"> <input type="radio"
					name="cardname" value="KB국민카드" autocomplete="off"> KB국민카드
				</label>
			</div>
			<p>
				<label for="cardnumber">카드번호</label> <input name="cardnumber"
					type="text" class="form-control" />
			</p>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="ProductListServlet" class="btn btn-secondary" role="button">취소</a>
				<input type="submit" class="btn btn-primary" value="결제 완료" />
			</div>
		</div>
	</form>

	</div>

	<!-- 필요한 스크립트 및 라이브러리 링크 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<!-- Datepicker 초기화 스크립트 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#deliverydate').datepicker({
				format : 'yyyy/mm/dd',
				language : 'ko', // 한국어로 지정
				autoclose : true,
				todayHighlight : true
			});
		});
		function showBankInput() {
			document.getElementById("bankInput").style.display = "block";
			document.getElementById("cardInput").style.display = "none";
			document.getElementById("accountnumber").required = true; // 은행 선택 시 계좌번호 필수로 설정
			document.getElementById("cardnumber").required = false; // 카드 선택 시 카드번호 필수 해제
		}
		function showCardInput() {
			document.getElementById("cardInput").style.display = "block";
			document.getElementById("bankInput").style.display = "none";
			document.getElementById("accountnumber").required = false; // 은행 선택 시 계좌번호 필수 해제
			document.getElementById("cardnumber").required = true; // 카드 선택 시 카드번호 필수로 설정
		}
	</script>
</body>
</html>
