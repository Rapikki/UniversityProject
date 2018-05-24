<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Department Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${department.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name : ${department.name}</p>
            <p>Faculty name :  ${departmentFaculty.name}</p>
        </div>
        <div>
            <form action="departments/delete" method="post">
                <p> Delete department : </p>
                <input type="hidden" name="deleteDepartmentId" value="${department.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="departments" method="post">
        <p> ${status} department </p>
        Department name <input type="text" name="departmentName" value="${department.name}"><br>
        Faculty id <input type="text" name="departmentFaculty" value="${department.faculty.id}"><br>
        <input type="hidden" name="departmentId" value = "${department.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
