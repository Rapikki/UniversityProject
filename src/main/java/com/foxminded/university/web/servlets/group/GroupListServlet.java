package com.foxminded.university.web.servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;
import com.foxminded.university.domain.Department;
import org.apache.commons.lang3.StringUtils;

import com.foxminded.university.dao.hibernate.GroupDaoImpl;
import com.foxminded.university.dao.hibernate.StudentDaoImpl;
import com.foxminded.university.domain.Group;

@WebServlet("/groups")
public class GroupListServlet extends HttpServlet {
    private GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
    private StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
    private Group group = new Group();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter("id");
        if (StringUtils.isNumeric(groupId)) {
            if (groupDaoImpl.findOne(Long.parseLong(groupId)) != null) {
                forwardToGroupItem(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        String groupId = req.getParameter("groupId");
        Department department = new Department();
        department.setId(Long.parseLong(req.getParameter(("groupDepartmentId"))));
        group.setName(req.getParameter("groupName"));
        group.setDepartment(departmentDao.findOne(Long.parseLong(req.getParameter(("groupDepartmentId")))));
        group.setCourse(Integer.parseInt(req.getParameter("groupCourse")));
        if (groupId != null && groupId != "") {
            group.setId(Long.parseLong(groupId));
            groupDaoImpl.update(group);
        } else {
            groupDaoImpl.create(group);
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/groups");
    }

    private void forwardToGroupItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long groupId = Long.parseLong(req.getParameter("id"));
        req.setAttribute("group", groupDaoImpl.findOne(groupId));
        req.setAttribute("students", studentDaoImpl.findAll());
        req.setAttribute("groupStudents", studentDaoImpl.findAllByGroupId(groupId));
        req.setAttribute("studentsWithoutGroups", studentDaoImpl.findAllWithoutGroup());
        req.getRequestDispatcher("GroupStatus.jsp").forward(req, resp);
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", groupDaoImpl.findAll());
        req.getRequestDispatcher("GroupList.jsp").forward(req, resp);
    }
}
