<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Linh Vu
  Date: 9/8/2019
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List product</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
</head>
<body>
<div class="container">
<h1>Product List</h1>
<p>
    <a href="/products?action=create">Create a New Customer</a>
</p>
<form method="post" action="${pageContext.request.contextPath}/products?action=search">
    <input type="text" name="txtSearch" id="search">
    <button type="submit">Search</button>
</form>
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
    <c:forEach items="${requestScope['productList']}" var='product'>
        <tr>
            <td><a href="${pageContext.request.contextPath}/products?action=view&id=${product.getId()}">${product.getId()}</a></td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getSupplier()}</td>
            <td><img src="<%=request.getContextPath()%>/images/${product.getPicture()}" height="40px" width="40px"></td>
            <td><a href="${pageContext.request.contextPath}/products?action=update&id=${product.getId()}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/products?action=delete&id=${product.getId()}">Delete</a></td>
        </tr>

    </c:forEach>


</table>


</div>


</body>
</html>
