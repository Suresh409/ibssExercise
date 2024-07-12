Feature: Google

  Background:
    Given url "http://www.google.co.uk" is launched

  @regression1
  Scenario: Mission statement can be seen
    When About page is shown
    Then page displays "Our mission is to organise the world’s information and make it universally accessible and useful"

  @regression1
  Scenario: Search for BBC News
    When searching for "BBC News"
    Then results contain "Home - BBC News"
    And result stats are displayed
    And number of "results" is more than 1
    And number of "seconds" is more than 0

