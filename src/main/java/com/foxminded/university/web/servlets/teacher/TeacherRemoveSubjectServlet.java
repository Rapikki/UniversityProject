package com.foxminded.university.web.servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.TeacherDaoImpl;

@WebServlet("/teachers/removeSubject")
public class TeacherRemoveSubjectServlet extends HttpServlet {
    TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        int subjectId = Integer.parseInt(req.getParameter("subjectId"));
        teacherDaoImpl.removeSubject(teacherId, subjectId);
        resp.sendRedirect(getServletContext().getContextPath() + "/teachers");
    }
}
