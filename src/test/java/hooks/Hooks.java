package hooks;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hooks {

    private Response response;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Example base URL
    }

    @Test
    @Step("Verify GET /posts/1 API response")
    public void testGetPost() {
        Hooks.logApiStep("Sending GET request to /posts/1");

        response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .extract()
                .response();
    }

    @Step("Logging API step: {message}")
    public static void logApiStep(String message) {
        System.out.println("API Step: " + message);
    }

    @AfterEach
    public void attachLogs() {
        Hooks.attachRequestLog("Sample request log for debugging");
        Hooks.attachResponseLog("{ \"status\": \"success\", \"message\": \"API executed\" }");
    }
    @Attachment(value = "Request Log", type = "text/plain")
    public static String attachRequestLog(String log) {
        return log;
    }

    @Attachment(value = "Response Log", type = "text/plain")
    public static String attachResponseLog(String response) {
        return response;
    }
}