Feature: CRUD operations a person

  Scenario: Find a person
    Given Person exists in database with id 1 and available for search
    When Find a person by id 1
    Then Get a PersonDto with id 1
    And Get a response status code of 200 after finding

  Scenario: Create a new person
    When Create a new person
    Then Get a response status code 201 after creating

  Scenario: Update a person
    Given Person exists in database with id 1 and available for update
    When Update a person with id 1
    Then Get a response status code 200 after updating

  Scenario: Delete a person
    Given Person exists in database with id 1 and available for delete
    When Delete a person with id 1
    Then Get a response status code 204 after deleting