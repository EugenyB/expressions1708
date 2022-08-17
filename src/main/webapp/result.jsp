<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 17.08.2022
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="expressionBean" scope="request" type="ua.mk.berkut.expressions1708.beans.ExpressionBean"/>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <h1>${expressionBean.current}</h1>
    <h2>${expressionBean.message}</h2>
</body>
</html>
