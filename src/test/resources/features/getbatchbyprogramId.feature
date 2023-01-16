Feature: To Verify Get batch by ProgramId API automation with Rest Assured

  Scenario Outline: Test Get batch by program Id
    Given The batch by programId service with URL and path
    When Get request by <programId> is made
    Then Validate the Get status code is 200
    And Validate the total batch <count>

    Examples: 
      | programId | count |
      |      1017 |     10|
