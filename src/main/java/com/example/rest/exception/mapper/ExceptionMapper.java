package com.example.rest.exception.mapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by tada on 2015/12/24.
 */
@Provider
@Priority(Priorities.USER - 100)
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        Viewable viewable = new Viewable("/error/exception", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(viewable).build();
    }
}
