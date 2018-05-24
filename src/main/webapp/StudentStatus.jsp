<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Student Informal Page</title>
<base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${student.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name : ${student.name} </p>
            <p>Surname : ${student.surname} </p>
            <p>Address : ${student.address} </p> 
            <p>Group name : ${studentGroup.name} </p>
        </div>
        <div>
            <form action="students/delete" method="post">
                <p> Delete student : </p>
                <input type="hidden" name="deleteStudentId" value="${student.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="students" method="post">
        <p> ${status} student </p>
        Name <input type="text" name="studentName" value="${student.name}"><br>
        Surname <input type="text" name="studentSurname" value="${student.surname}"><br>
        Address <input type="text"name="studentAddress" value="${student.address}"><br>
        Card number <input type="text" name="studentCardNumber" value="${student.cardNumber}"><br>
        Group id <input type="text" name="studentGroup" value="${student.group.id}"><br>
        <input type="hidden" name="studentId" value = "${student.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
