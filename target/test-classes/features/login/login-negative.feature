@LoginNeg
Feature: Login

  Scenario Outline: Invalid Login input
    Given The employee is on the login page
    When  The employee types <username> into username input
    When The employee types <password> into password input
    When The employee clicks on the login button
    Then The employee should see an alert saying <alert>

    Examples:
      | username   | password | alert |
      | "g8tor"    | "chomp!"  | "Wrong password for User" |
      | "sicEmDawgs"| "natchamps"| "Username not found"|

