package com.foxminded.university.web.servlets.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;
import com.foxminded.university.domain.Department;
import org.apache.commons.lang3.StringUtils;

import com.foxminded.university.dao.hibernate.SubjectDaoImpl;
import com.foxminded.university.domain.Subject;

@WebServlet("/subjects")
public class SubjectListServlet extends HttpServlet {
    private SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    private Subject subject = new Subject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectId = req.getParameter("id");
        if (StringUtils.isNumeric(subjectId)) {
            if (subjectDaoImpl.findOne(Long.parseLong(subjectId)) != null) {
                forwardToSubjectItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = new Subject();
        String subjectId = req.getParameter("id");
        Department department = new Department();
        department.setId(Long.parseLong(req.getParameter("subjectDepartmentId")));
        subject.setName(req.getParameter("subjectName"));
        subject.setDepartment(departmentDao.findOne(Long.parseLong(subjectId)));
        if (subjectId != null && subjectId != "") {
            subject.setId(Long.parseLong(subjectId));
            subjectDaoImpl.update(subject);
        } else {
            subjectDaoImpl.create(subject);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/subjects");
    }

    private void forwardToSubjectItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long subjectId = Long.parseLong(req.getParameter("id"));
        subject.setId(subjectId);
        req.setAttribute("subject", subjectDaoImpl.findOne(subjectId));
        req.setAttribute("subjectDepartment", departmentDao.findBySubjectId(subjectId));
        req.getRequestDispatcher("SubjectStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", subjectDaoImpl.findAll());
        req.getRequestDispatcher("SubjectList.jsp").forward(req, resp);
    }
}
