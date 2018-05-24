package com.foxminded.university.web.servlets.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.StudentDaoImpl;

@WebServlet("/students/delete")
public class StudentDeleteServlet extends HttpServlet {
    private StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long studentId = Long.parseLong(req.getParameter("deleteStudentId"));
        studentDaoImpl.delete(studentId);
        resp.sendRedirect(getServletContext().getContextPath() + "/students");
    }
}

