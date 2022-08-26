Feature: TodoMVCFeature
  This feature allows user to add,edit,delete,complete and filter a list of to dos lists

  Background: User is on the page and the todo list is empty
    Given I am on the todo page
    And I have an empty todo list

  Scenario: Create a new todo list
    When I create a new todo "one"
    Then I see the new todo "one" on my list

  Scenario: Editing an existing todo List
    When I create a new todo "my todo list 1"
    And I edit todo 1 with "updated to do list"
    Then I see the new todo "updated to do list" on my list

  Scenario: Delete existing todo List
    When I create a new todo "my todo list to be deleted"
    And I delete todo list in position 1
    Then the todo list is empty

  Scenario: Complete an existing todo list and filter active and completed todos
    And I have these todos on my list
    | my to do list 1 |
    | my to do list 2 |
    | my to do list 3 |
    When I complete to do list in position 1
    Then 1 todo list is completed
    When I filter active todos
    Then 2 todo list is active
    When I filter completed todos
    Then 1 todo list is completed





