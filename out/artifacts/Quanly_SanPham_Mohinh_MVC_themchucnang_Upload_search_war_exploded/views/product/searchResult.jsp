<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Linh Vu
  Date: 9/8/2019
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
    <link rel="stylesheet" type="text/css" href="/css/Style.css">
</head>
<body>
<div class="container">
    <h1>Product Search Result </h1>
    <p>
        <a href="/products">Back to Product List</a>
    </p>
    <table>
        <tr>
            <td> Product ID</td>
            <td> Product Name</td>
            <td> Product Price</td>
            <td>Product Description</td>
            <td>Product Supplier</td>
            <td> Product Picture</td>
            <td>Update</td>
            <td>Delete</td>
        </tr>
        <c:forEach items="${requestScope['productSearch']}" var='product'>
            <tr>
                <td>${product.getId()}</td>
                <td>${product.getName()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.getDescription()}</td>
                <td>${product.getSupplier()}</td>
                <td><img src="<%=request.getContextPath()%>/images/${product.getPicture()}" height="40px" width="40px"></td>
                <td><a href="/products?action=update&id=${product.getId()}">Edit</a></td>
                <td><a href="/products?action=delete&id=${product.getId()}">Delete</a></td>
            </tr>

        </c:forEach>


    </table>


</div>
</body>
</html>
