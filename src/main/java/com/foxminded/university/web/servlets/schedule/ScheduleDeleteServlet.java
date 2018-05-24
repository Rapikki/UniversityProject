package com.foxminded.university.web.servlets.schedule;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.ScheduleDaoImpl;

@WebServlet("/schedules/delete")
public class ScheduleDeleteServlet extends HttpServlet {
    private ScheduleDaoImpl scheduleDaoImpl = new ScheduleDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long scheduleId = Long.parseLong(req.getParameter("deleteScheduleId"));
        scheduleDaoImpl.delete(scheduleId);
        resp.sendRedirect(getServletContext().getContextPath() + "/schedules");
    }
}

