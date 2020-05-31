package com.javieraviles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class DeveloperResourceTest {

    @Inject
    DeveloperResource developerResource;

    @Test
    public void getAllDevelopersEndpoint() {
        int devsCollectionLength = 2;

        List<Developer> result = developerResource.getAll();

        assertEquals(devsCollectionLength, result.size());
        assertEquals("Javier Aviles", result.get(0).name);
    }

    @Test
    public void getSearchDevelopersEndpoint() {
        int devsCollectionLength = 1;

        List<Developer> result = developerResource.searchDeveloper("Avil");
        
        assertEquals(devsCollectionLength, result.size());
        assertEquals("Javier Aviles", result.get(0).name);
    }

    @Test
    public void createDeveloperEndpoint() {
        Developer dev = new Developer();
        dev.name = "James Gosling";
        dev.email = "james.gosling@java.net";
        int devsCollectionLength = 1;

        developerResource.createDeveloper(dev);
        
        List<Developer> result = developerResource.searchDeveloper("Gosling");
        
        assertEquals(devsCollectionLength, result.size());
        assertEquals("James Gosling", result.get(0).name);
    }

    @Test
    public void deleteDeveloperEndpoint() {
        long devId = 3;
        int devsCollectionLength = 0;

        developerResource.deleteDeveloper(devId);
        
        List<Developer> result = developerResource.searchDeveloper("Gosling");
        
        assertEquals(devsCollectionLength, result.size());
    }
}