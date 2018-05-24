<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Schedule Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${schedule.id == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Subject name : ${scheduleSubject.name}</p>
            <p>Number hall : ${scheduleLectureHall.name}</p>
            <p>Teacher name :${scheduleTeacher.name}</p>
            <p>Group name : ${scheduleGroup.name}</p>
            <p>Date : ${schedule.date}</p>
        </div>
        <div>
            <form action="schedules/delete" method="post">
                <p> Delete schedule : </p>
                <input type="hidden" name="deleteScheduleId" value="${schedule.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <form action="schedules" method = "post">
        <p>${status} subject from list : </p>
        <select name="subjectId">
            <c:forEach items="${subjects}" var="subjects">
                <option value="${subjects.id}" ${subjects.id == scheduleSubject.id  ? 'selected="selected"' : ''}>
                        ${subjects.name}
                </option>
            </c:forEach>
        </select>
        <input type="hidden" name="subjectId" value = "${subject.id}">
        <p>${status} teacher from list : </p>
        <select name="teacherId">
            <c:forEach items="${teachers}" var="teachers">
                <option value="${teachers.id}" ${teachers.id == scheduleTeacher.id  ? 'selected="selected"' : ''}>
                        ${teachers.name}
                </option>
            </c:forEach>
        </select>
        <p>${status} lecture hall from list : </p>
        <select name="lectureHallId">
            <c:forEach items="${lectureHalls}" var="lectureHalls">
                <option value="${lectureHalls.id}" ${lectureHalls.id == scheduleLectureHall.id  ? 'selected="selected"' : ''}>
                        ${lectureHalls.name}
                </option>
            </c:forEach>
        </select>
        <p>${status} group hall from list : </p>
        <select name="groupId">
            <c:forEach items="${groups}" var="groups">
                <option value="${groups.id}" ${groups.id == scheduleGroup.id  ? 'selected="selected"' : ''}>
                        ${groups.name}
                </option>
            </c:forEach>
        </select><br>
        <p> Date  : </p> <input type="text" name="scheduleDate" value="${schedule.date}">
        <input type="hidden" name="scheduleId" value = "${schedule.id}"><br>
        <input type="submit" value="${status}">
    </form>
</div>
</body>
</html>
