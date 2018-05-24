<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Faculty Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${faculty.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name: ${faculty.name} </p>
        </div>
        <div>
            <form action="faculties/delete" method="post">
                <p> Delete faculty : </p>
                <input type="hidden" name="deleteFacultyId" value="${faculty.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="faculties" method="post">
        <p> ${status} faculty </p>
        Name <input type="text" name="facultyName" value="${faculty.name}"><br>
        <input type="hidden" name="facultyId" value = "${faculty.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
