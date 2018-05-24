package com.foxminded.university.rest;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.hibernate.DepartmentDaoImpl;
import com.foxminded.university.domain.Department;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @GET
    public Response getAllDepartments() {
        List<Department> departments = departmentDao.findAll();
        return Response.status(Response.Status.OK).entity(departments).build();
    }

    @GET
    @Path("/{departmentId}")
    public Response findById(@PathParam("departmentId") long id) {
        Department department = departmentDao.findOne(id);
        if (department != null) {
            return Response.status(Response.Status.OK).entity(department).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/update/{departmentId}")
    public Response update(@PathParam("departmentId") long id, Department department) {
        if (id == department.getId()) {
            departmentDao.update(department);
            return Response.status(Response.Status.OK).entity(department).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/create")
    public Response create(Department department) {
        departmentDao.create(department);
        if (department.getId() != null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{departmentId}")
    public Response delete(@PathParam("departmentId") long id) {
        if (departmentDao.findOne(id) != null) {
            departmentDao.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
