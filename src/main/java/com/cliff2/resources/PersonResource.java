package com.cliff2.resources;

import com.cliff2.api.Person;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
public class PersonResource {
    private Jdbi jdbi;

    public PersonResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    public Response getPerson() {
        //return "Get Person";
        List<Person> people = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"person\" ORDER BY \"name\"")
                    .mapToBean(Person.class)
                    .list();
        });
        System.out.println(people.get(0).getName());
        return Response.noContent().build();
    }

    @POST
    public String postPerson() {
        return "POST Person";
    }
}

