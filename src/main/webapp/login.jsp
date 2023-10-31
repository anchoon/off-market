<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP 게시판 웹 사이트 - 로그인</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* 추가한 스타일 */
        body {
            background-color: #f5f5f5;
        }

        .center-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .jumbotron {
            background-color: white;
        }
    </style>
     <script>
        function setLoginFormAction() {
            var isAdmin = false; // 현재 사용자가 관리자인지 여부를 판단하는 조건을 설정

            if (isAdmin) {
                document.getElementById("loginForm").action = "AdminLoginServlet";
            } else {
                document.getElementById("loginForm").action = "LoginServlet";
            }
        }
    </script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="main.jsp">ON-OFF</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="main.jsp">메인</a></li>
                <li class="nav-item"><a class="nav-link" href="bbs.jsp">게시판</a></li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <% if (session.getAttribute("userID") != null) { %>
                            <%= session.getAttribute("userName") %>님 환영합니다.
                        <% } else { %>
                            접속하기
                        <% } %>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <% if (session.getAttribute("userID") != null) { %>
                            <li><a href="logout.jsp">로그아웃</a></li>
                        <% } else { %>
                            <li class="active"><a href="login.jsp">로그인</a></li>
                            <li><a href="join.jsp">회원가입</a></li>
                        <% } %>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container center-container">
        <div class="col-lg-4">
            <div class="jumbotron">
                <form method="post" action="LoginServlet">
                    <h3 style="text-align: center;">로그인 화면</h3>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="로그인">
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
