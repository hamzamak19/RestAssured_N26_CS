Feature: Delete Pet

  Scenario: Delete a pet by ID
    Given I send a DELETE request to "pet/101"
    Then the response status code should be 200