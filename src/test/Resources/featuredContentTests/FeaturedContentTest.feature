Ability: The user is able to navigate to the Featured Content page and pick an article that is Featured

  Background:
    Given I navigate to the Wikipedia homepage

  @Chrome
  Scenario: As a user, I want to find an article that is featured on Wikipedia - Google Chrome
    When I click the Featured content link from the left-hand side menu of the Wikipedia homepage
    Then I wait for the Featured page to load
    When I click on the Featured articles link from the Featured content page
    Then I wait for the Featured articles page to load
    When I click on the Myst link from the Video Gaming section of the Featured articles page
    Then I wait for the Myst page to load
    And I check if the Featured Star appears on the page

  @Firefox
  Scenario: As a user, I want to find an article that is featured on Wikipedia - Firefox
    When I click the Featured content link from the left-hand side menu of the Wikipedia homepage
    Then I wait for the Featured page to load
    When I click on the Featured articles link from the Featured content page
    Then I wait for the Featured articles page to load
    When I click on the Myst link from the Video Gaming section of the Featured articles page
    Then I wait for the Myst page to load
    And I check if the Featured Star appears on the page