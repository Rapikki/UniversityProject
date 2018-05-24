<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lecture Halls</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Lecture Hall list</th>
    </tr>
    <c:forEach items="${lectureHalls}" var="lectureHalls">
        <tr>
            <td>
                <c:url value="lectureHalls" var="URL">
                    <c:param name="id" value="${lectureHalls.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${lectureHalls.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action ="lectureHalls/new">
    <input type="submit" value="Create new lecture hall" >
</form>
</body>
</html>
