<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO, user.User, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 목록</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <%
    if (session.getAttribute("message") != null) {
    %>
    <script type="text/javascript">
        alert('<%=session.getAttribute("message")%>');
    </script>
    <%
    session.removeAttribute("message");
    }
    %>
    <%
    UserDAO userDao = new UserDAO();
    List<User> userList = userDao.getAllUsers();
    request.setAttribute("userList", userList);
    %>

    <div class="container">
        <h2>회원 목록</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>

            <c:forEach var="user" items="${userList}">
                <tbody>
                    <tr>
                        <td>${user.userID}</td>
                        <!-- Be aware of exposing sensitive information -->

                        <td>${user.userName}</td>

                        <td>${user.userEmail}</td>
                        <!-- Email can be sensitive information too -->

                        <td>
                            <form method="post"
                                action="${pageContext.request.contextPath}/UserDeleteAllRelatedDataServlet">
                                <input type="hidden" name="userID" value="${user.userID}">
                                <button type="submit" class="btn btn-danger">삭제</button>
                            </form>
                        </td>

                    </tr>
                </tbody>
            </c:forEach>


        </table>


        <script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>
        <script
            src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
    </div>
</body>
</html>
