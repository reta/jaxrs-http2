package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.example.model.Person;

import reactor.core.publisher.Flux;

@Service
@Path("/people")
public class PeopleResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Flux<Person> getPeople() {
        return Flux.just(new Person("a@b.com", "Tom", "Knocker"));
    }
}
