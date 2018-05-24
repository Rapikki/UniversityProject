<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Teacher Informal Page</title>
    <base href="<%=request.getContextPath()%>/" />
</head>
<body>
<c:choose>
    <c:when test= "${teacher.name == null}">
        <div>
            <c:set var = "status" value = "Create"/>
        </div>
    </c:when>
    <c:otherwise>
    <c:set var = "status" value = "Update"/>
        <div>
            <p>Name : ${teacher.name}</p>
            <p>Surname : ${teacher.surname}</p>
            <p>Position : ${teacher.position}</p>
            <p>Address : ${teacher.address}</p>
            <p>Department name : ${teacherDepartment.name}</p>
        </div>
        <div>
            <form action="teachers/delete" method="post">
                <p> Delete teacher : </p>
                <input type="hidden" name="teacherId" value="${teacher.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
		<div style="margin-top: 2%">
		    <table border="1">
		        <tr>
		            <th>Subjects :</th>
		        </tr>
		        <c:forEach items="${teacherSubjects}" var="teacherSubjects">
		            <tr>
		                <td>
		                    <c:out value="${teacherSubjects.name}"/>
		                </td>
		            </tr>
		        </c:forEach>
		    </table>
		</div>
		<form action="teachers/addSubject" method = "post">
		<p>Add subject from list : </p>
		  <select name="subjectId">
			<c:forEach items="${subjects}" var="subjects">
			  <option value="${subjects.id}">${subjects.name}</option>
			</c:forEach>
		  </select>
		  <input type="hidden" name="teacherId" value = "${teacher.id}">
		  <input type="submit" value="Add">
		</form>
		<form action="teachers/removeSubject" method = "post">
		<p>Remove subject from list : </p>
		  <select name="subjectId">
			<c:forEach items="${teacherSubjects}" var="teacherSubjects">
			  <option value="${teacherSubjects.id}">${teacherSubjects.name}</option>
			</c:forEach>
		  </select>
		  <input type="hidden" name="teacherId" value = "${teacher.id}">
		  <input type="submit" value="Remove">
		</form>
	</c:otherwise>
</c:choose>
<div>
    <form action="teachers" method="post">
        <p> ${status} teacher </p>
        Name <input type="text" name="teacherName" value="${teacher.name}"><br>
        Surname <input type="text" name="teacherSurname" value="${teacher.surname}"><br>
        Address <input type="text"name="teacherAddress" value="${teacher.address}"><br>
        Position <input type="text" name="teacherPosition" value="${teacher.position}"><br>
        Department id <input type="text" name="teacherDepartmentId" value="${teacher.department.id}"><br>
        <input type="hidden" name="teacherId" value = "${teacher.id}">
        <input type="submit" name="Execute" value="${status}">
    </form>
</div>
</body>
</html>
