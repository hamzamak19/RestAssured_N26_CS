Feature: Get Pet Details

  Scenario: Retrieve pet details by ID
    Given I send a GET request to "pet/10"
    Then the response status code should be 200
    And the response body should not be empty