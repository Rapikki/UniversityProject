<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Schedules</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Schedules List</th>
    </tr>
    <c:forEach items="${schedules}" var="schedules">
        <tr>
            <td>
                <c:url value="schedules" var="URL">
                    <c:param name="id" value="${schedules.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${schedules.date}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action ="schedules/new">
    <input type="submit" value="Create new schedule" >
</form>
</body>
</html>
