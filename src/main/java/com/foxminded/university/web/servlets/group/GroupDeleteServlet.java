
package com.foxminded.university.web.servlets.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.GroupDaoImpl;

@WebServlet("/groups/delete")
public class GroupDeleteServlet extends HttpServlet {
    private GroupDaoImpl groupDaoImpl = new GroupDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long groupId = Long.parseLong(req.getParameter("deleteGroupId"));
        groupDaoImpl.delete(groupId);
        resp.sendRedirect(getServletContext().getContextPath() + "/groups");
    }
}

