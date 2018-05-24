<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Group Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${group.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
        <c:set var = "status" value = "Update"/>
        <div>
            <p>Name: ${group.name}</p>
            <p>Course: ${group.course}</p>
        </div>
        <div>
            <form action="groups/delete" method="post">
                <p> Delete group : </p>
                <input type="hidden" name="deleteGroupId" value="${group.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
        <div>
            <table border="1">
                <tr>
                    <th>Student name</th>
                    <th>Student surname</th>
                </tr>
                <c:forEach items="${groupStudents}" var="groupStudents">
                    <tr>
                        <td>
                            <c:out value="${groupStudents.name}"/>
                        </td>
                        <td>
                            <c:out value="${groupStudents.surname}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="groups" method="post">
        <p> ${status} group </p>
        Group name : <input type="text" name="groupName" value="${group.name}"><br>
        Department id : <input type="text" name="groupDepartmentId" value="${group.department.id}"><br>
        Course : <input type="text" name="groupCourse" value="${group.course}"><br>
        <input type="hidden" name="groupId" value = "${group.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
</html>
