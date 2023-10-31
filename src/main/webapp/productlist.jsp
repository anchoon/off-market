<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            padding: 20px;
        }

        .product-item {
            flex: 0 0 calc(25% - 10px);
            margin-bottom: 20px;
            text-align: center;
            border: 1px solid #ddd;
            padding: 10px;
        }

        .product-image {
            max-width: 100%;
            height: auto;
        }

        .product-details {
            margin-top: 10px;
        }

        .back-button {
            display: block;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container"> 
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="main.jsp">ON OFF</a>
        </nav>
        <h1 class="mt-4">Product List</h1>

        <div class="product-container">
            <c:forEach var="product" items="${productList}">
                <div class="product-item">
                    <img src="${pageContext.request.contextPath}/upload/${product.productImage}"
                         alt="Product Image" class="product-image">
                    <div class="product-details">
                        <h4>${product.productName}</h4>
                        <p>Price: $${product.productPrice}</p>
                        <p>Size: ${product.productSize}</p>
                        <p>In Stock: ${product.stockQuantity} available</p>
                    </div>
                    <a href="userInformation.jsp?productID=${product.productID}" class="btn btn-primary">Buy Now</a>
                    <a href="DetailServlet?productID=${product.productID}" class="btn btn-info">Details</a>
                </div>
            </c:forEach>
        </div>
        
        <a href="main.jsp" class="btn btn-secondary back-button">Back to Main Page</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
