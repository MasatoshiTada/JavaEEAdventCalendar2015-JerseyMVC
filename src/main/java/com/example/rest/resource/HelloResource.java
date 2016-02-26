package com.example.rest.resource;

import com.example.rest.dto.HelloDto;
import com.example.rest.exception.MyException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import com.example.rest.exception.MyRuntimeException;
import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by tada on 2015/12/24.
 */
@Path("hello")
@RequestScoped
public class HelloResource {

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
    public Viewable resultGet(@QueryParam("name") @DefaultValue("")
            @Size(message = "{name.size}", min = 1, max = 10)
            @Pattern(message = "{name.pattern}", regexp = "[a-zA-Z]*")
            String name) throws Exception {
        System.out.println("========================== resultGet ===================");
        // 例外を起こすサンプル
        throwException(name);
        // 本来の処理
        HelloDto helloDto = new HelloDto();
        helloDto.setMessage("Hello, " + name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("hello", helloDto);
//        return new Viewable("/hello/result.jsp"); // 絶対パス
        return new Viewable("result.jsp", map); // 相対パス
    }

    @POST
    @Path("result")
    @ErrorTemplate(name = "index.jsp") // 相対パス
//    @ErrorTemplate(name = "/hello/index.jsp") // 絶対パス
    public Viewable result(@FormParam("name") @DefaultValue("")
                           @Size(message = "{name.size}", min = 1, max = 10)
                           @Pattern(message = "{name.pattern}", regexp = "[a-zA-Z]*")
                           String name) throws Exception {
        System.out.println("========================== resultPost ===================");
        // 例外を起こすサンプル
        throwException(name);
        // 本来の処理
        HelloDto helloDto = new HelloDto();
        helloDto.setMessage("Hello, " + name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("hello", helloDto);
//        return new Viewable("/hello/result.jsp"); // 絶対パス
        return new Viewable("result.jsp", map); // 相対パス
    }

    private void throwException(String value) throws Exception {
        switch (value) {
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
