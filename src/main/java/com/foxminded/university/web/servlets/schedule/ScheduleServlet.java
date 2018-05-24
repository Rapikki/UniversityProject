package com.foxminded.university.web.servlets.schedule;

import com.foxminded.university.dao.hibernate.SubjectDaoImpl;
import com.foxminded.university.dao.hibernate.ScheduleDaoImpl;
import com.foxminded.university.dao.hibernate.TeacherDaoImpl;
import com.foxminded.university.domain.Schedule;
import com.foxminded.university.dao.hibernate.GroupDaoImpl;
import com.foxminded.university.dao.hibernate.LectureHallDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/schedules")
public class ScheduleServlet extends HttpServlet {
    private ScheduleDaoImpl scheduleDaoImpl = new ScheduleDaoImpl();
    private SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
    private LectureHallDaoImpl lectureHallDaoImpl = new LectureHallDaoImpl();
    private TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
    private GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
    private Schedule schedule = new Schedule();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scheduleId = req.getParameter("id");
        if (StringUtils.isNumeric(scheduleId)) {
            if (scheduleDaoImpl.findOne(Long.parseLong(scheduleId)) != null) {
                forwardToScheduleItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Schedule schedule = new Schedule();
        String scheduleId = req.getParameter("scheduleId");
        schedule.setLectureHall(lectureHallDaoImpl.findOne(Long.parseLong(req.getParameter("lectureHallId"))));
        schedule.setGroup(groupDaoImpl.findOne(Long.parseLong(req.getParameter("groupId"))));
        schedule.setTeacher(teacherDaoImpl.findOne(Long.parseLong(req.getParameter("teacherId"))));
        schedule.setSubject(subjectDaoImpl.findOne(Long.parseLong(req.getParameter("subjectId"))));
        String scheduleDate = req.getParameter("scheduleDate");
        try {
            schedule.setDate(convertDate(scheduleDate));
        } catch (ParseException e) {
            throw new ServletException("Incorrect date format", e);
        }
        if (scheduleId != null && scheduleId != "") {
            schedule.setId(Long.parseLong(scheduleId));
            scheduleDaoImpl.update(schedule);
        } else {
            scheduleDaoImpl.create(schedule);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/schedules");
    }

    private void forwardToScheduleItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long scheduleId = Long.parseLong(req.getParameter("id"));
        Schedule itemSchedule = scheduleDaoImpl.findOne(scheduleId);
        req.setAttribute("scheduleSubject", itemSchedule.getSubject());
        req.setAttribute("scheduleLectureHall", itemSchedule.getLectureHall());
        req.setAttribute("scheduleTeacher", itemSchedule.getTeacher());
        req.setAttribute("scheduleGroup", itemSchedule.getGroup());
        req.setAttribute("schedule", itemSchedule);
        req.setAttribute("subjects", subjectDaoImpl.findAll());
        req.setAttribute("lectureHalls", lectureHallDaoImpl.findAll());
        req.setAttribute("teachers", teacherDaoImpl.findAll());
        req.setAttribute("groups", groupDaoImpl.findAll());
        req.getRequestDispatcher("ScheduleStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("schedules", scheduleDaoImpl.findAll());
        req.getRequestDispatcher("ScheduleList.jsp").forward(req, resp);
    }

    private java.sql.Date convertDate(String scheduleDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(scheduleDate);
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        return date;
    }
}

