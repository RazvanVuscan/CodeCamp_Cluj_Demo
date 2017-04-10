Ability: The user is able to search for a term on Wikipedia using Chrome

  Background:
    Given I navigate to the Wikipedia homepage

  @Chrome
  Scenario: As a user, I want to search for and read about the term Airplane using Wikipedia - Google Chrome
    When I enter Airplane in the search field
    And I click the search button
    Then I wait for the Airplane page to load
    And I check that the title matches

  @Firefox
  Scenario: As a user, I want to search for and read about the term Airplane using Wikipedia - Firefox
    When I enter Airplane in the search field
    And I click the search button
    Then I wait for the Airplane page to load
    And I check that the title matches


