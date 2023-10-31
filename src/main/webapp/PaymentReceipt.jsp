<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제 완료</title>
    <!-- 필요한 스타일이나 스크립트를 여기에 추가할 수 있습니다. -->
</head>
<body>
    <div class="container">
        <h1>결제 완료</h1>
        <p>결제가 성공적으로 완료되었습니다.</p>
        
        <!-- 결제 정보 표시 -->
        <h2>결제 정보</h2>
        <ul>
            <li>성명: ${sessionScope.user.userName}</li>
            <li>전화번호: ${sessionScope.user.userPhone}</li>
            <li>주소: ${sessionScope.user.userAddress}</li>
            <li>배송일: ${param.deliverydate}</li>
            <!-- 여기에 더 많은 결제 정보를 추가할 수 있습니다. -->
        </ul>
        
        <!-- 영수증 표시 -->
        <h2>영수증</h2>
        <p>영수증 내용을 여기에 추가하세요.</p>
        
        <!-- 홈으로 돌아가는 링크 -->
        <a href="main.jsp" class="btn btn-primary">홈으로 돌아가기</a>
    </div>
</body>
</html>
