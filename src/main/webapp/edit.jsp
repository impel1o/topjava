<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: d.baskakov
  Date: 28.03.2017
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="POST" action='meals' name="frmAddUser">
    <%--readonly="readonly"--%>
    id : <input type="text"  name="id"
                value="<c:out value="${meal.id}" />"/> <br/>
    calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    description: <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    dateTime : <input
        type="text" name="dateTime"
        value="<c:out value="${meal.dateTime}" />"/> <br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
