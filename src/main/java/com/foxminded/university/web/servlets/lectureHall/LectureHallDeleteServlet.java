package com.foxminded.university.web.servlets.lectureHall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.LectureHallDaoImpl;

@WebServlet("/lectureHalls/delete")
public class LectureHallDeleteServlet extends HttpServlet {
    private LectureHallDaoImpl lectureHallDaoImpl = new LectureHallDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long lectureHallsId = Long.parseLong(req.getParameter("deleteLectureHallsId"));
        lectureHallDaoImpl.delete(lectureHallsId);
        resp.sendRedirect(getServletContext().getContextPath() + "/lectureHalls");
    }
}

