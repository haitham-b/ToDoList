//package org.leanix.resources;
//
//import com.codahale.metrics.annotation.Timed;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import org.leanix.model.ToDo;
//
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Path("/")
//@Produces(MediaType.APPLICATION_JSON)
//public class ToDoListResource {
//    private final String template;
//    private final String defaultName;
//    private final AtomicLong counter = new AtomicLong();
//
//    public ToDoListResource(String template, String defaultName) {
//        this.template = template;
//        this.defaultName = defaultName;
//    }
//
//    @GET
//    @Timed
//    @Path("/helloworld")
//    public ToDo sayHello(@QueryParam("name") Optional<String> name) {
//        final String value = String.format(template, name.orElse(defaultName));
//        return new ToDo("title", "description");
//    }
//}
