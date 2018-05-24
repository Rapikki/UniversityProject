package com.foxminded.university.web.servlets.lectureHall;

import com.foxminded.university.dao.hibernate.LectureHallDaoImpl;
import com.foxminded.university.domain.LectureHall;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lectureHalls")
public class LectureHallServlet extends HttpServlet {
    private LectureHallDaoImpl lectureHallDaoImpl = new LectureHallDaoImpl();
    private LectureHall lectureHall = new LectureHall();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lectureHallId = req.getParameter("id");
        if (StringUtils.isNumeric(lectureHallId)) {
            if (lectureHallDaoImpl.findOne(Long.parseLong(lectureHallId)) != null) {
                forwardToLectureHallItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LectureHall lectureHall = new LectureHall();
        String lectureHallId = req.getParameter("lectureHallId");
        lectureHall.setName(req.getParameter("lectureHallName"));
        if (lectureHallId != null && lectureHallId != "") {
            lectureHall.setId(Long.parseLong(lectureHallId));
            lectureHallDaoImpl.update(lectureHall);
        } else {
            lectureHallDaoImpl.create(lectureHall);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/lectureHalls");
    }

    private void forwardToLectureHallItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long lectureHallId = Long.parseLong(req.getParameter("id"));
        lectureHall.setId(lectureHallId);
        req.setAttribute("lectureHall", lectureHallDaoImpl.findOne(lectureHallId));
        req.getRequestDispatcher("LectureHallStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lectureHalls", lectureHallDaoImpl.findAll());
        req.getRequestDispatcher("LectureHallList.jsp").forward(req, resp);
    }
}
