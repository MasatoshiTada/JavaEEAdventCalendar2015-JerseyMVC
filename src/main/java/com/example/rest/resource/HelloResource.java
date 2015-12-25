package com.example.rest.resource;

import com.example.rest.dto.HelloDto;
import com.example.rest.exception.MyException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.example.rest.exception.MyRuntimeException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by tada on 2015/12/24.
 */
@Path("hello")
@RequestScoped
public class HelloResource {

    @Inject
    private HelloDto helloDto;

    @GET
    @Path("index")
    public Viewable index() {
//        return new Viewable("/hello/index.jsp"); // 絶対パス
        return new Viewable("index.jsp"); // 相対パス
    }

    @GET
    @Path("result")
    @ErrorTemplate(name = "index.jsp") // 相対パス
//    @ErrorTemplate(name = "/hello/index.jsp") // 絶対パス
    public Viewable result(@QueryParam("name") @DefaultValue("")
            @Size(message = "{name.size}", min = 1, max = 10)
            @Pattern(message = "{name.pattern}", regexp = "[a-zA-Z]*")
            String name) throws Exception {
        // 例外を起こすサンプル
        switch (name) {
            case "null":
                throw new NullPointerException("NULLPO!");
            case "myrun":
                throw new MyRuntimeException("MyRuntime!");
            case "run":
                throw new RuntimeException("Runtime!");
            case "io":
                throw new IOException("IOE!");
            case "myex":
                throw new MyException("MY EXCEPTION!");
            case "ex":
                throw new Exception("EXCEPTION!");
        }

        // 本来の処理
        helloDto.setMessage("Hello, " + name);
//        return new Viewable("/hello/result.jsp"); // 絶対パス
        return new Viewable("result.jsp"); // 相対パス
    }

    @GET
    @Path("redirect")
    public Response redirect(@Context UriInfo uriInfo)
            throws URISyntaxException {
        URI location = uriInfo.getBaseUriBuilder() // http://localhost:8080/<contextRoot>/api
                .path(HelloResource.class) // http://localhost:8080/<contextRoot>/api/hello
                .path("redirect2") // http://localhost:8080/<contextRoot>/api/hello/redirect2
                .build();
        return Response.status(Response.Status.FOUND)
                .location(location).build();
    }

    @GET
    @Path("redirect2")
    public Viewable redirect2() {
//        return new Viewable("/hello/redirect2.jsp"); // 絶対パス
        return new Viewable("redirect2.jsp"); // 相対パス
    }
}
