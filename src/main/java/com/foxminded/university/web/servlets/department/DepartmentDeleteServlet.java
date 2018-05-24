package com.foxminded.university.web.servlets.department;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;

@WebServlet("/departments/delete")
public class DepartmentDeleteServlet extends HttpServlet {
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long departmentId = Long.parseLong(req.getParameter("deleteDepartmentId"));
        departmentDao.delete(departmentId);
        resp.sendRedirect(getServletContext().getContextPath() + "/departments");
    }
}
