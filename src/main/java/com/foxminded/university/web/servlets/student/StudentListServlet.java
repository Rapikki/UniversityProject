package com.foxminded.university.web.servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.GroupDaoImpl;
import com.foxminded.university.domain.Group;
import org.apache.commons.lang3.StringUtils;

import com.foxminded.university.dao.hibernate.StudentDaoImpl;
import com.foxminded.university.domain.Student;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
    private StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
    private GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
    private Student student = new Student();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("id");
        if (StringUtils.isNumeric(studentId)) {
            student.setId(Long.parseLong(studentId));
            if (studentDaoImpl.findOne(Long.parseLong(studentId)) != null) {
                forwardToStudentItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        Group group = new Group();
        String studentId = req.getParameter("studentId");
        group.setId(Long.parseLong(req.getParameter("studentGroup")));
        student.setGroup(groupDaoImpl.findOne(Long.parseLong(req.getParameter("studentGroup"))));
        student.setName(req.getParameter("studentName"));
        student.setSurname(req.getParameter("studentSurname"));
        student.setAddress(req.getParameter("studentAddress"));
        student.setCardNumber(req.getParameter("studentCardNumber"));
        if (studentId != null && studentId != "") {
            student.setId(Long.parseLong(studentId));
            studentDaoImpl.update(student);
        } else {
            studentDaoImpl.create(student);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/students");
    }

    private void forwardToStudentItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long studentId = Long.parseLong(req.getParameter("id"));
        student.setId(studentId);
        req.setAttribute("student", studentDaoImpl.findOne(studentId));
        req.setAttribute("studentGroup", groupDaoImpl.findByStudent(studentId));
        req.getRequestDispatcher("StudentStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentDaoImpl.findAll());
        req.getRequestDispatcher("StudentList.jsp").forward(req, resp);
    }
}
