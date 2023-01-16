Feature: To Verify Delete Program API by program name automation with Rest Assured

  Scenario Outline: Test Delete program by program Name
    Given The service with URL and delete path
    When Delete request by "<programName>" is made
    Then Validate the status code is 200

    Examples: 
      | programName                      |
      | Jan23-Mighty5testers-SDET-005-30 |
      | Jan23-Mighty5testers-SDET-005-31 |
