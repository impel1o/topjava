<%--
  Created by IntelliJ IDEA.
  User: d.baskakov
  Date: 27.03.2017
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
meals


<table border="1">
    <tr>
        <th width="80">Id</th>
        <th width="80">Date</th>
        <th width="80">Description</th>
        <th width="80">Calories</th>
        <th width="80"> exceed</th>
        <th width="80"> Update</th>
        <th width="80"> Delete</th>

    </tr>
    <c:forEach items="${list}" var="user">

    <tr>
        <td>${user.id}</td>
        <c:set var="cleanedDateTime" value="${fn:replace(user.dateTime, 'T', ' ')}"/>
        <td>${cleanedDateTime}</td>
        <td>${user.description}</td>
        <c:if test="${user.exceed}">
            <td bgcolor="#a52a2a"><font color="#deb887">${user.calories}</font></td>
        </c:if>
        <c:if test="${!user.exceed}">
            <td bgcolor="#7fff00">${user.calories}</td>
        </c:if>

        <td>${user.exceed}</td>
        <td><a href="meals?action=edit&id=<c:out value="${user.id}"/>">Update</a></td>
        <td><a href="meals?action=delete&id=<c:out value="${user.id}"/>">Delete</a></td>

    </tr>
    </c:forEach>

    <p><a href="meals?action=edit&id=999">Add User</a></p>
</body>
</html>
