<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Students</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Students</th>
    </tr>
    <c:forEach items="${students}" var="students">
        <tr>
            <td>
                <c:url value="students" var="URL">
                    <c:param  name="id" value="${students.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${students.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action ="students/new">
    <input type="submit" value="Create new student" >
</form>
</body>
</html>
