package com.foxminded.university.web.servlets.department;

import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;
import com.foxminded.university.dao.hibernate.FacultyDaoImpl;
import com.foxminded.university.domain.Department;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments")
public class DepartmentListServlet extends HttpServlet {
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    private FacultyDaoImpl facultyDaoImpl = new FacultyDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentId = req.getParameter("id");
        if (StringUtils.isNumeric(departmentId)) {
            if (departmentDao.findOne(Long.parseLong(departmentId)) != null) {
                forwardToDepartmentItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        String departmentId = req.getParameter("departmentId");
        department.setName(req.getParameter("departmentName"));
        department.setFaculty(facultyDaoImpl.findOne(Long.parseLong(req.getParameter("departmentFaculty"))));
        if (departmentId != null && departmentId != "") {
            department.setId(Long.parseLong(departmentId));
            departmentDao.update(department);
        } else {
            departmentDao.create(department);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/departments");
    }

    private void forwardToDepartmentItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long departmentId = Long.parseLong(req.getParameter("id"));
        req.setAttribute("department", departmentDao.findOne(departmentId));
        req.setAttribute("departmentFaculty", facultyDaoImpl.findByDepartmentId(departmentId));
        req.getRequestDispatcher("DepartmentStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", departmentDao.findAll());
        req.getRequestDispatcher("DepartmentList.jsp").forward(req, resp);
    }
}
