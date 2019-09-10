<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Linh Vu
  Date: 9/8/2019
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
</head>
<body>
<div class="container">
    <h1> Update Product </h1>
    <p>
    <c:if test="${requestScope['message']!=null}">
        <span class="message">${requestScope['message']} </span>
    </c:if>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/products"> Back to Product List</a>
    </p>
    <form method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Product information:</legend>
            <table>
                <tr>
                    <td> ID</td>
                    <td><p>${requestScope.product.getId()}</p></td>
                </tr>
                <tr>
                    <td> Product name</td>
                    <td><input type="text" id="name" name="productName" value="${requestScope["product"].getName()}" ></td>

                </tr>
                <tr>
                    <td> Product Price</td>
                    <td><input type="text" id="price" name="productPrice" value="${requestScope["product"].getPrice()}"></td>
                </tr>
                <tr>
                    <td>Product Description</td>
                    <td><input type="text" id="description" name="productDescription" value="${requestScope["product"].getDescription()}"></td>

                </tr>
                <tr>
                    <td>Product Supplier</td>
                    <td><input type="text" id="supplier" name="productSupplier" value="${requestScope["product"].getSupplier()}"></td>

                </tr>
                <tr>
                    <td> Product Picture</td>
                    <td>
                        <img src="<%=request.getContextPath()%>/images/${requestScope["product"].getPicture()}" height="30px">
                        <input type="file" name="productPicture" id="picture" >
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="update" value="Update Product"></td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>

</body>
</html>
