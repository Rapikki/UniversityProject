package com.foxminded.university.web.servlets.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;
import com.foxminded.university.dao.hibernate.SubjectDaoImpl;
import com.foxminded.university.domain.Department;
import org.apache.commons.lang3.StringUtils;

import com.foxminded.university.dao.hibernate.TeacherDaoImpl;
import com.foxminded.university.domain.Teacher;

@WebServlet("/teachers")
public class TeacherListServlet extends HttpServlet {
    private TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
    private SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    private Teacher teacher = new Teacher();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("id");
        if (StringUtils.isNumeric(teacherId)) {
            if (teacherDaoImpl.findOne(Long.parseLong(teacherId)) != null) {
                forwardToTeacherItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        Department department = new Department();
        department.setId(Long.parseLong(req.getParameter("teacherDepartmentId")));
        String teacherId = req.getParameter("teacherId");
        teacher.setName(req.getParameter("teacherName"));
        teacher.setSurname(req.getParameter("teacherSurname"));
        teacher.setAddress(req.getParameter("teacherAddress"));
        teacher.setDepartment(departmentDao.findOne(Long.parseLong(req.getParameter("teacherDepartmentId"))));
        teacher.setPosition(req.getParameter("teacherPosition"));
        if (teacherId != null && teacherId != "") {
            teacher.setId(Long.parseLong(teacherId));
            teacherDaoImpl.update(teacher);
        } else {
            teacherDaoImpl.create(teacher);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/teachers");
    }

    private void forwardToTeacherItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long teacherId = Long.parseLong(req.getParameter("id"));
        teacher.setId(teacherId);
        req.setAttribute("teacher", teacherDaoImpl.findOne(teacherId));
        req.setAttribute("teacherSubjects", subjectDaoImpl.findSubjectsByTeacher(teacherId));
        req.setAttribute("teacherDepartment", departmentDao.findByTeacherId(teacherId));
        req.setAttribute("subjects", subjectDaoImpl.findAll());
        req.getRequestDispatcher("TeacherStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teachers", teacherDaoImpl.findAll());
        req.getRequestDispatcher("TeacherList.jsp").forward(req, resp);
    }
}
