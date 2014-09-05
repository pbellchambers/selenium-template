Feature: Test PDF Feature
  Demonstrate basic cucumber/selenium PDF functionality

  Scenario: Search
    Given I am on the PDF homepage
    Then I should see PDF text: "This is a test PDF"
    And I should see PDF text: "You are from: Rome"