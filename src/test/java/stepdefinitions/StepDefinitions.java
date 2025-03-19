package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.example.PetEndPoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StepDefinitions {

    private Response response;
    private final PetEndPoints petEndpoint = new PetEndPoints();

    /**
     * Allure Report Configuration.
     */
    @Before
    public void setup() {
        // Attach Allure to RestAssured for all HTTP requests in this test
        RestAssured.filters(new AllureRestAssured());
    }

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint The API endpoint for retrieving a pet.
     */
    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        int petId = Integer.parseInt(endpoint.split("/")[1]); // Extract pet ID from endpoint
        response = petEndpoint.getPetById(petId);
    }

    /**
     * Sends a POST request to the specified endpoint with the given request body.
     *
     * @param endpoint The API endpoint for creating a pet.
     * @param body     The JSON body containing pet details.
     */
    @Given("I send a POST request to {string} with the following body:")
    public void i_send_a_post_request_to_with_body(String endpoint, String body) {
        response = petEndpoint.createPet(body);
    }

    /**
     * Sends a PUT request to the specified endpoint with the given request body.
     *
     * @param endpoint The API endpoint for updating a pet.
     * @param body     The JSON body containing updated pet details.
     */
    @Given("I send a PUT request to {string} with the following body:")
    public void i_send_a_put_request_to_with_body(String endpoint, String body) {
        response = petEndpoint.updatePet(body);
    }

    /**
     * Sends a DELETE request to the specified endpoint.
     *
     * @param endpoint The API endpoint for deleting a pet.
     */
    @Given("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        int petId = Integer.parseInt(endpoint.split("/")[1]); // Extract pet ID from endpoint
        response = petEndpoint.deletePetById(petId);
    }

    /**
     * Validates that the response status code matches the expected value.
     *
     * @param statusCode The expected HTTP status code.
     */
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    /**
     * Validates that the response body is not empty.
     */
    @Then("the response body should not be empty")
    public void the_response_body_should_not_be_empty() {
        assertFalse(response.getBody().asString().isEmpty());
    }

    /**
     * Validates that the response body contains a specific key-value pair as a string.
     *
     * @param key   The JSON key to verify.
     * @param value The expected string value.
     */
    @Then("the response body should contain {string} with value {string}")
    public void the_response_body_should_contain_with_value(String key, String value) {
        assertEquals(value, response.jsonPath().getString(key));
    }

    /**
     * Validates that the response body contains a specific key-value pair as an integer.
     *
     * @param key   The JSON key to verify.
     * @param value The expected integer value.
     */
    @Then("the response body should contain {string} with value {int}")
    public void the_response_body_should_contain_with_value(String key, int value) {
        assertEquals(value, response.jsonPath().getInt(key));
    }
}