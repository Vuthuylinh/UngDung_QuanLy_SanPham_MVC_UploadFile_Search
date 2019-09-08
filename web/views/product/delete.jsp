<%--
  Created by IntelliJ IDEA.
  User: Linh Vu
  Date: 9/8/2019
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Form</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
</head>
<body>
<div class="container">
    <h1> Delete Product</h1>
    <p>
        <a href="/products"> Back to Product List</a>
    </p>
    <form method="post">
        <h3>Are you sure?</h3>
        <fieldset>
            <legend>Product Information</legend>
            <table>
        <tr>
            <td>Product Id</td>
            <td>${requestScope["product"].getId()}</td>
        </tr>

        <tr>
            <td>Product Name</td>
            <td>${requestScope["product"].getName()}</td>
        </tr>
        <tr>
            <td>Product Price</td>
            <td>${requestScope["product"].getPrice()}</td>
        </tr>
        <tr>
            <td> Description</td>
            <td>${requestScope["product"].getDescription()}</td>
        </tr>
        <tr>
            <td> Picture</td>
            <td><img src = "<%=request.getContextPath()%>/images/${requestScope["product"].getPicture()}" height="50px"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Delete Product"></td>
            <td><a href="/products">Back to Product List</a></td>
        </tr>
            </table>
        </fieldset>
    </form>

</div>
</body>
</html>
