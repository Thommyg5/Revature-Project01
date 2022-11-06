@Assign
Feature: Assign Defect

  Scenario: Assign Defect
    Given The manager is logged in as a manager and is on the home page
    Then The manager should see pending defects
    When The manager clicks on the select button for a defect
    Then The defect description should appear in bold
    When The manager selects a tester from the drop down and assigns defect
    Then The defect should disappear from the list
    Given The assigned tester is on their home page
    Then The tester can can see only defects assigned to them
    Then The tester should see the pending defect
    When The tester changes status of any defect
    Then The tester should see the defect has a different status