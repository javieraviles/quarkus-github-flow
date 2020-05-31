package com.javieraviles;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import io.quarkus.panache.common.Sort;

@Path("/developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeveloperResource {

    @GET
    public List<Developer> getAll() {
        return Developer.listAll(Sort.by("name"));
    }

    @GET
    @Path("/search/{word}")
    public List<Developer> searchDeveloper(@PathParam("word") String word) {
        return Developer.search(word);
    }

    @POST
    @Transactional
    public void createDeveloper(@RequestBody Developer newDeveloper) {
        Developer.persist(newDeveloper);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteDeveloper(@PathParam("id") Long devId) {
        Developer.deleteById(devId);
    }
}