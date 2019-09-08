<%--
  Created by IntelliJ IDEA.
  User: Linh Vu
  Date: 9/8/2019
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product </title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
</head>
<body>
<div class="container">
    <h1> Create a New Product </h1>
    <c:if test="${requestScope['message']!=null}">
        <span class="message">${requestScope['message']} </span>
    </c:if>
    <p>
        <a href="/products"> Back to Product List</a>
    </p>
    <form method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Product information:</legend>
            <table>
                <tr>
                    <td> Product ID</td>
                    <td><input type="text" name="productId"></td>
                </tr>
                <tr>
                    <td> Product name</td>
                    <td><input type="text" name="productName"></td>

                </tr>
                <tr>
                    <td> Product Price</td>
                    <td><input type="text" name="productPrice"></td>
                </tr>
                <tr>
                    <td>Product Description</td>
                    <td><input type="text" name="productDescription"></td>

                </tr>
                <tr>
                    <td>Product Supplier</td>
                    <td><input type="text" name="productSupplier"></td>

                </tr>
                <tr>
                    <td> Product Picture</td>
                    <td><input type="file" name="productPicture" value="Upload File"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="create" value="Save Product"></td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>
</body>
</html>
