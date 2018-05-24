package com.foxminded.university.web.servlets.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.dao.hibernate.SubjectDaoImpl;
import com.foxminded.university.domain.Subject;

@WebServlet("/subjects/delete")
public class SubjectDeleteServlet extends HttpServlet {
    private SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl();
    private Subject subject = new Subject();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long subjectId = Long.parseLong(req.getParameter("deleteSubjectId"));
        subjectDaoImpl.delete(subjectId);
        resp.sendRedirect(getServletContext().getContextPath() + "/subjects");
    }
}
