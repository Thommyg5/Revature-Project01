@Navigation
Feature: Navigation

  Background: Logged in as a Manager
    Given The manager is logged in and is on the home page

  Scenario Outline: Check Links and Use Back Navigation
    Then The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview
    When The manager clicks on <link>
    Then The title of the page should be <title>
    When The manager clicks the browser back button
    Then The manager should be on the home page and the title of page is Home

    Examples:
      | link           | title               |
      | "Matrices"       | "Matrix Overivew"     |
      | "Test Cases"     | "Test Case Overivew"  |
      | "Report a Defect"| "Defect Reporter"     |
      | "Defect Overview"| "Defect Overview"     |
