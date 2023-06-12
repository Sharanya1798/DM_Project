package com.example.BookStore.controller;


import org.apache.jena.riot.RDFDataMgr;
import org.glassfish.jersey.media.multipart.FormDataParam;
import com.example.BookStore.service.BookService;

import javax.ws.rs.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("books")
public class BookController {
    BookService bookser = new BookService();

    @POST
    @Path("/baseobjectproperties")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public Response searchBaseObjectProperties(@FormDataParam("className") String className) throws URISyntaxException {

        ArrayList<String[]> properties = bookser.fetchBaseObjectProperties(className);

//        System.out.println("In controller ------------");

        if (properties.isEmpty()) {
            return Response.noContent().build();
        } else {
//            System.out.println(jobs);
            return Response.ok().entity(properties).build();
        }
    }








    @POST
    @Path("/objectproperties")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public Response searchObjectProperties(@FormDataParam("className") String className) throws URISyntaxException {

        ArrayList<String[]> properties = bookser.fetchObjectProperties(className);

//        System.out.println("In controller ------------");

        if (properties.isEmpty()) {
            return Response.noContent().build();
        } else {
//            System.out.println(jobs);
            return Response.ok().entity(properties).build();
        }
    }

















    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public Response searchJob(@FormDataParam("skill") String skill,
                              @FormDataParam("location") String location,
                              @FormDataParam("experience") Integer experience) throws URISyntaxException {

        ArrayList<String[]> books1 = bookser.fetchJobs(skill, location, experience);

//        System.out.println("In controller ------------");

        if (books1.isEmpty()) {
            return Response.noContent().build();
        } else {
//            System.out.println(jobs);
            return Response.ok().entity(books1).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response authenticateUser(@FormDataParam("username") String username,
                                     @FormDataParam("password") String password) throws URISyntaxException {

        String loginType = bookser.authenticateUser(username, password);

        System.out.println("Hello");
        if(loginType.isEmpty())
            return Response.noContent().build();
        else if(loginType.equals("admin"))
            return Response.ok().entity("admin").build();
        else
            return Response.ok().entity("user").build();
    }



    @POST
    @Path("/loadbasemodel")
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response loadbasemodel() throws URISyntaxException {

        bookser.loadbasemodel();

        System.out.println("loding base model ");
        return Response.ok().entity("ok").build();
    }







    @POST
    @Path("/d1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasses() throws URISyntaxException {

        System.out.println(" ------d1 in controller------- ");

        ArrayList<String> c=bookser.getClasses(RDFDataMgr.open("library _harsha_rajiv.owl"));

        if (c.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok().entity(c).build();
        }
    }

    @POST
    @Path("/d2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public Response searchJob(@FormDataParam("ont") InputStream in) throws URISyntaxException {
        ArrayList<String> c=bookser.getClasses(in);
        System.out.println(c);
        if (c.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok().entity(c).build();
        }
    }



    @POST
    @Path("/mapping")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public Response mapping(@FormDataParam("bookname") String BOOKNAME,
                            @FormDataParam("author") String AUTHOR,
                            @FormDataParam("edition") String EDITION,
                            @FormDataParam("bookid") String BOOKID,
                            @FormDataParam("class") String className) throws URISyntaxException {
        System.out.println("====in controller mapping===");

        System.out.println(BOOKID+BOOKNAME+EDITION+AUTHOR+className);

        ArrayList<String[]> books1 = bookser.mapping(BOOKNAME,AUTHOR,EDITION,BOOKID,className);

//        System.out.println("In controller ------------");

        if (books1.isEmpty()) {
            return Response.noContent().build();
        } else {
//            System.out.println(jobs);
            return Response.ok().entity(books1).build();
        }
    }


}
