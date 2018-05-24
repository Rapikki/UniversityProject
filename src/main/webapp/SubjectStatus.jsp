<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subject Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${subject.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name : ${subject.name}</p>
			<p>Department name : ${subjectDepartment.name}</p>
        </div>
        <div>
            <form action="subjects/delete" method="post">
                <p> Delete subject : </p>
                <input type="hidden" name="deleteSubjectId" value="${subject.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="subjects" method="post">
        <p> ${status} subject </p>
        Name <input type="text" name="subjectName" value="${subject.name}"><br>
        Department id <input type="text" name="subjectDepartmentId" value="${subjectDepartment.id}"><br>
        <input type="hidden" name="subjectId" value = "${subject.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
</html>
