Feature: Update Pet Details

  Scenario: Update pet details with valid data
    Given I send a PUT request to "pet" with the following body:
      """
      {
        "id": 11,
        "name": "MAK updated",
        "category": {
          "id": 11,
          "name": "Human updated"
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
    And the response body should contain "name" with value "MAK updated"
    And the response body should contain "category.name" with value "Human updated"