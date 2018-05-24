package com.foxminded.university.rest;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.hibernate.FacultyDaoImpl;
import com.foxminded.university.domain.Faculty;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/faculties")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacultyResource {
    private FacultyDao facultyDaoImpl = new FacultyDaoImpl();

    @GET
    public Response getAllFaculties() {
        List<Faculty> faculties = facultyDaoImpl.findAll();
        return Response.status(Status.OK).entity(faculties).build();
    }

    @GET
    @Path("/{facultyId}")
    public Response findById(@PathParam("facultyId") long id) {
        Faculty faculty = facultyDaoImpl.findOne(id);
        if (faculty != null) {
            return Response.status(Status.OK).entity(faculty).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/update/{facultyId}")
    public Response update(@PathParam("facultyId") long id, Faculty faculty) {
        if (id == faculty.getId()) {
            facultyDaoImpl.update(faculty);
            return Response.status(Status.OK).entity(faculty).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/create")
    public Response create(Faculty faculty) {
        facultyDaoImpl.create(faculty);
        if (faculty.getId() != null) {
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{facultyId}")
    public Response delete(@PathParam("facultyId") long id) {
        if (facultyDaoImpl.findOne(id) != null) {
            facultyDaoImpl.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}
