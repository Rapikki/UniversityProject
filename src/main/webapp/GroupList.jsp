<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Groups</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Groups List</th>
    </tr>
    <c:forEach items="${groups}" var="groups">
        <tr>
            <td>
                <c:url  value="groups" var="URL">
                    <c:param name="id" value="${groups.id}"/>
                </c:url>
                <a href="${URL}"><c:out value="${groups.name}"></c:out> </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "groups/new">
    <input type="submit" value="Create new group" >
</form>
</body>
</html>
