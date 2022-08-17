<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Math expressions</title>
</head>
<body>
    <form action="calculate" method="post">
        <label for="expression">Expression to calc:</label>
        <input type="text" name="expression" id="expression">
        <input type="submit" value="Calculate">
    </form>
    <a href="calculate">Show all expressions</a>
</body>
</html>