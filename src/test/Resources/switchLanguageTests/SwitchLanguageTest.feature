Ability: The user is able to switch the language on the page

  Background:
    Given I navigate to the Wikipedia homepage

  @Chrome
  Scenario: As a user, I want to switch the language on the Wikipedia homepage - Google Chrome
    When I click on the Română language link on the right-hand side menu
    And I wait for the page to refresh
    Then I check that the language on the homepage has switched to Romanian

  @Firefox
  Scenario: As a user, I want to switch the language on the Wikipedia homepage - Firefox
    When I click on the Română language link on the right-hand side menu
    And I wait for the page to refresh
    Then I check that the language on the homepage has switched to Romanian