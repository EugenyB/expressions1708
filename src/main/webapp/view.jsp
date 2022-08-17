<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 17.08.2022
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="expressionBean" type="ua.mk.berkut.expressions1708.beans.ExpressionBean" scope="request"/>
<html>
<head>
    <title>Expressions</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>expression</th>
            <th>result</th>
            <th>X</th>
            <th>E</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${expressionBean.expressions}" var="e">
                <tr>
                <td>${e.id}</td>
                <td>${e.expr}</td>
                <td>${e.result}</td>
                <td><a href="delete?id=${e.id}">delete</a></td>
                <td><a href="edit?id=${e.id}">edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
