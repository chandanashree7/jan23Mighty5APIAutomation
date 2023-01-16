Feature: To Verify Delete Program API by program name automation with Rest Assured

  Scenario Outline: Test Delete program by program Name
    Given The delete service with URL and path
    When Delete request by "<programName>" is made
    Then Validate the status code is 200

    Examples: 
      | programName                      |
      | Jan23-Mighty5testers-SDET-005-11 |
      | Jan23-Mighty5testers-SDET-005-12 |
