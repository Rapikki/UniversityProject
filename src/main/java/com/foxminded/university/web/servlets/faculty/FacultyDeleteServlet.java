
package com.foxminded.university.web.servlets.faculty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.FacultyDaoImpl;

@WebServlet("/faculties/delete")
public class FacultyDeleteServlet extends HttpServlet {
   private FacultyDaoImpl facultyDaoImpl = new FacultyDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long facultyId = Integer.parseInt(req.getParameter("deleteFacultyId"));
        facultyDaoImpl.delete(facultyId);
        resp.sendRedirect(getServletContext().getContextPath() + "/faculties");
    }
}

