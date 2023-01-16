Feature: To Verify Get batch by ProgramId API automation with Rest Assured

  Scenario Outline: Test Get batch by program Id
    Given The service with URL and batch path
    When Get request by <programId> is made
    Then Validate the Get status code is 200
    And Validate the total batch <count>

    Examples: 
     	| programId | count |
      |      2542 |    5 |
