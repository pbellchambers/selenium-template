Feature: Test Feature
  Demonstrate basic google search using cucumber datatable functionality and selenium
  Demonstrate selenium scenario outline


  Scenario: Search Google
    Given I am on the Google homepage
    When I search for How to make cheese
    Then I should see wikiHow

  Scenario Outline: Display Eligibility Category
    Given I am on the Google homepage
    When I search for <Search>
    Then I should see <Result>

  Examples:
    |Search     |Result                                       |
    |slashdot   |Slashdot: News for nerds, stuff that matters |
    |reddit blog|blog.reddit -- what's new on reddit          |
    |wikipedia  |Wikipedia, the free encyclopedia             |