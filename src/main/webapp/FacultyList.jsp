<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Faculties</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Faculty List</th>
    </tr>
    <c:forEach items="${faculties}" var="faculties">
        <tr>
            <td>
                <c:url value="faculties" var="URL">
                    <c:param name="id" value="${faculties.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${faculties.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "faculties/new">
    <input type="submit" value="Create new faculty" >
</form>
</body>
</html>
