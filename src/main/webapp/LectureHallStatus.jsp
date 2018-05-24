<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Lecture Hall Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${lectureHall.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name : ${lectureHall.name}</p>
        </div>
        <div>
            <form action="lectureHalls/delete" method="post">
                <p> Delete student : </p>
                <input type="hidden" name="deleteLectureHallsId" value="${lectureHall.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="lectureHalls" method="post">
        <p> ${status} lecture hall </p>
        Lecture hall number : <input type="text" name="lectureHallName" value="${lectureHall.name}"><br>
        <input type="hidden" name="lectureHallId" value = "${lectureHall.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
