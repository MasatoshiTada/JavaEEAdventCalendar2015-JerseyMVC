package com.example.rest.resource;

import com.example.rest.dto.MessageDto;
import com.example.rest.exception.MyException;
import java.io.IOException;
import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by tada on 2015/12/24.
 */
@Path("hello")
@RequestScoped
public class HelloResource {

    @Inject
    private MessageDto messageDto;

    @GET
    @Path("index")
    public Viewable index() {
        return new Viewable("index");
    }

    @GET
    @Path("result")
    @ErrorTemplate(name = "index")
    public Viewable result(@QueryParam("name") @DefaultValue("")
            @Size(message = "{name.size}", min = 1, max = 10)
            @Pattern(message = "{name.pattern}", regexp = "[a-zA-Z]*")
            String name) throws Exception {
        if (name.equals("zzz")) {
            throw new NullPointerException("EXCEPTION IN CONROLLER!!");
        }

        messageDto.setMessage("Hello, " + name);
        return new Viewable("result", messageDto);
    }
}
