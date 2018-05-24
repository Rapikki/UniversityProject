package com.foxminded.university.web.servlets.schedule;

import com.foxminded.university.dao.hibernate.TeacherDaoImpl;
import com.foxminded.university.dao.hibernate.GroupDaoImpl;
import com.foxminded.university.dao.hibernate.LectureHallDaoImpl;
import com.foxminded.university.dao.hibernate.SubjectDaoImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/schedules/new")
public class ScheduleCreateServlet extends HttpServlet {
    private SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
    private LectureHallDaoImpl lectureHallDaoImpl = new LectureHallDaoImpl();
    private TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
    private GroupDaoImpl groupDaoImpl = new GroupDaoImpl();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", subjectDaoImpl.findAll());
        req.setAttribute("lectureHalls", lectureHallDaoImpl.findAll());
        req.setAttribute("teachers", teacherDaoImpl.findAll());
        req.setAttribute("groups", groupDaoImpl.findAll());
        req.getRequestDispatcher("/ScheduleStatus.jsp").forward(req, resp);
    }
}
