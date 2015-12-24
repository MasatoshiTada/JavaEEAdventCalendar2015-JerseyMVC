package com.example.rest.exception.mapper;

import com.example.rest.exception.MyException;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by tada on 2015/12/24.
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException> {

    @Override
    public Response toResponse(MyException e) {
        Viewable viewable = new Viewable("/error/exception", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(viewable).build();
    }
}
