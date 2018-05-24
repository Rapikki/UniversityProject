package com.foxminded.university.web.servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.TeacherDaoImpl;

@WebServlet("/teachers/delete")
public class TeacherDeleteServlet extends HttpServlet {
    private TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        teacherDaoImpl.removeAllSubjects(Integer.parseInt(teacherId));
        teacherDaoImpl.delete(Long.parseLong(teacherId));
        resp.sendRedirect(getServletContext().getContextPath() + "/teachers");
    }
}
