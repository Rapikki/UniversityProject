package com.foxminded.university.web.servlets.faculty;

import com.foxminded.university.dao.hibernate.FacultyDaoImpl;
import com.foxminded.university.domain.Faculty;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/faculties")
public class FacultyListServlet extends HttpServlet {
    private FacultyDaoImpl facultyDaoImpl = new FacultyDaoImpl();
    private Faculty faculty = new Faculty();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String facultyId = req.getParameter("id");
        if (StringUtils.isNumeric(facultyId)) {
            if (facultyDaoImpl.findOne(Long.parseLong(facultyId)) != null) {
                forwardToFacultyItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Faculty faculty = new Faculty();
        String facultyId = req.getParameter("facultyId");
        faculty.setName(req.getParameter("facultyName"));
        if (facultyId != null && facultyId != "") {
            faculty.setId(Long.parseLong(facultyId));
            facultyDaoImpl.update(faculty);
        } else {
            facultyDaoImpl.create(faculty);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/faculties");
    }

    private void forwardToFacultyItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long facultyId = Long.parseLong(req.getParameter("id"));
        req.setAttribute("faculty", facultyDaoImpl.findOne(facultyId));
        req.getRequestDispatcher("FacultyStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("faculties", facultyDaoImpl.findAll());
        req.getRequestDispatcher("FacultyList.jsp").forward(req, resp);
    }
}
