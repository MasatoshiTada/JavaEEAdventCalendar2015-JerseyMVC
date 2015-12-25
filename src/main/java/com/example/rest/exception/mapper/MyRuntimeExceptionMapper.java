package com.example.rest.exception.mapper;

import com.example.rest.exception.MyRuntimeException;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by tada on 2015/12/24.
 */
@Provider
public class MyRuntimeExceptionMapper implements ExceptionMapper<MyRuntimeException> {

    @Override
    public Response toResponse(MyRuntimeException e) {
        Viewable viewable = new Viewable("/error/exception", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(viewable).build();
    }
}
