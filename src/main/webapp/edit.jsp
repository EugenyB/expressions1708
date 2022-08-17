<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 17.08.2022
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="expressionBean" scope="request" type="ua.mk.berkut.expressions1708.beans.ExpressionBean"/>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <form action="edit" method="post">
        <label for="expr">Expression</label>
        <input type="text" name="expr" id="expr" value="${expressionBean.expression.expr}">
        <input type="hidden" name="id" value="${expressionBean.expression.id}">
        <input type="submit" value="Edit">
    </form>
</body>
</html>
