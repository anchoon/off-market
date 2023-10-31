<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP 게시판 웹 사이트 - 회원가입</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="main.jsp">메인</a></li>
                <li class="nav-item"><a class="nav-link" href="bbs.jsp">게시판</a></li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="login.jsp">로그인</a></li>
                <li class="nav-item active"><a class="nav-link" href="join.jsp">회원가입</a></li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center">회원가입 화면</h3>
                        <form method="post" action="JoinServlet">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20" required>
                            </div>
                            <div class="form-group text-center">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-primary active">
                                        <input type="radio" name="userGender" autocomplete="off" value="남자" checked> 남자
                                    </label>
                                    <label class="btn btn-primary">
                                        <input type="radio" name="userGender" autocomplete="off" value="여자"> 여자
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="50" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="주소" name="userAddress" maxlength="100">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="전화번호" name="userPhone" maxlength="20">
                            </div>
                            <div class="form-group" id="message-box"></div>

                                <button type="submit" class="btn btn-primary btn-block" onclick="displayMessage()">회원가입</button>
							
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    
 <script>
        function displayMessage() {
            // Get the message box element
            var messageBox = document.getElementById("message-box");

            // Display the message in the box
            messageBox.innerHTML = '<div class="alert alert-info">환영 합니다.</div>';
        }
    </script>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
