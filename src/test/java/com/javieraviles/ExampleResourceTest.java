package com.javieraviles;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

@QuarkusTest
public class ExampleResourceTest {

    @Inject
    ExampleResource exampleResource;

    @Test
    public void testHelloEndpoint() {
        assertEquals("hello",exampleResource.hello());
    }

}