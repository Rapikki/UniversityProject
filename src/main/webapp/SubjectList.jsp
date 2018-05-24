<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subjects</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Subjects List</th>
    </tr>
    <c:forEach items="${subjects}" var="subjects">
        <tr>
            <td>
                <c:url value="subjects" var="URL">
                    <c:param name="id" value="${subjects.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${subjects.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action ="subjects/new">
    <input type="submit" value="Create new subject" >
</form>
</body>
</html>
