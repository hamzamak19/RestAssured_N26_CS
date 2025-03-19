package org.example;

import io.qameta.allure.restassured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class provides methods to interact with the Pet API,
 * including operations for retrieving, creating, updating,
 * and deleting pets.
 */
public class PetEndPoints {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    /**
     * Retrieves pet details by ID.
     *
     * @param petId The ID of the pet to be retrieved.
     * @return The API response containing pet details.
     */
//    public Response getPetById(int petId) {
//        return given()
//                .filter(new AllureRestAssured()) // Add Allure Rest Assured filter
//                .baseUri(BASE_URI)
//                .basePath("pet/" + petId)
//                .header("Content-Type", "application/json")
//                .when()
//                .get();
//    }


    public Response getPetById(int petId) {
        Response response = given()
                .filter(new AllureRestAssured()) // Allure logging
                .baseUri(BASE_URI)
                .basePath("pet/" + petId)
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then()
                .log().all() // Print response details to console
                .extract()
                .response();

        return response;
    }


    /**
     * Creates a new pet in the system.
     *
     * @param body A JSON string containing pet details.
     * @return The API response containing the created pet details.
     */
    public Response createPet(String body) {
        return given()
                .baseUri(BASE_URI)
                .basePath("pet")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post();
    }

    /**
     * Updates an existing pet's details.
     *
     * @param body A JSON string containing updated pet details.
     * @return The API response after updating the pet.
     */
    public Response updatePet(String body) {
        return given()
                .baseUri(BASE_URI)
                .basePath("pet")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put();
    }

    /**
     * Deletes a pet from the system using its ID.
     *
     * @param petId The ID of the pet to be deleted.
     * @return The API response after deleting the pet.
     */
    public Response deletePetById(int petId) {
        return given()
                .baseUri(BASE_URI)
                .basePath("pet/" + petId)
                .contentType(ContentType.JSON)
                .when()
                .delete();
    }

    /**
     * Configures and returns a request specification with logging enabled.
     *
     * @return A configured {@link RequestSpecification} instance.
     */
    private RequestSpecification given() {
        return RestAssured.given().log().all();
    }
}