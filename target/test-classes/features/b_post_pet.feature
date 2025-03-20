Feature: Create a New Pet

  Scenario: Create a new pet with valid data
    Given I send a POST request to "pet" with the following body:
      """
      {
        "id": 11,
        "name": "MAK",
        "category": {
          "id": 11,
          "name": "Human"
        },
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """
    Then the response status code should be 200
    And the response body should contain "name" with value "MAK"
    And the response body should contain "category.name" with value "Human"