<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Teachers</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Teachers</th>
    </tr>
    <c:forEach items="${teachers}" var="teachers">
        <tr>
            <td>
                <c:url value="teachers" var="URL">
                    <c:param name="id" value="${teachers.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${teachers.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "teachers/new">
    <input type="submit" value="Create new teacher" >
</form>
</body>
</html>
