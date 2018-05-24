<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Departments</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Department List</th>
    </tr>
    <c:forEach items="${departments}" var="departments">
        <tr>
            <td>
                <c:url value="departments" var="URL">
                    <c:param name="id" value="${departments.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${departments.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "departments/new">
    <input type="submit" value="Create new department" >
</form>
</body>
</html>
