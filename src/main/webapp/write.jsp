<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        @import url("http://fonts.googleapis.com/earlyaccess/nanumgothic.css");
        @import url("http://fonts.googleapis.com/earlyaccess/hanna.css");
        * {
            font-family: 'Nanum Gothic';
        }
        h1 {
            font-family: 'Hanna';
        }

        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #337ab7;
            border: none;
        }

        .navbar-brand {
            color: #fff;
        }

        .navbar-brand:hover {
            color: #ff9900;
        }

        .navbar-nav > li > a {
            color: #333;
        }

        .navbar-nav > li > a:hover {
            color: #337ab7;
        }

        .container {
            margin-top: 20px;
        }

        .panel {
            margin-left: 20px;
            width: 80%;
        }

        .form-control {
            width: 100%;
        }

        /* Customize the form elements */
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
        }
    </style>
    <title>JSP 게시판 웹 사이트</title>
</head>
<body>
    <%
        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }
    %>
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expand="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="main.jsp">메인</a></li>
                <li class="active"><a href="bbs.jsp">게시판</a></li>
            </ul>
            <%
                if (userID == null) {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                        aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="login.jsp">로그인</a></li>
                        <li><a href="join.jsp">회원가입</a></li>
                    </ul>
                </li>
            </ul>
            <%
                } else {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                        aria-haspopup="true" aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="LogoutServlet">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <form method="post" action="WriteServlet">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">게시판 글쓰기 양식</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="bbsTitle">글 제목</label>
                            <input type="text" class="form-control" id="bbsTitle" name="bbsTitle"
                                maxlength="50">
                        </div>
                        <div class="form-group">
                            <label for="bbsContent">글 내용</label>
                            <textarea class="form-control" id="bbsContent" name="bbsContent"
                                maxlength="2048" rows="10"></textarea>
                        </div>
                        <input type="submit" class="btn btn-primary pull-right" value="글쓰기">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
