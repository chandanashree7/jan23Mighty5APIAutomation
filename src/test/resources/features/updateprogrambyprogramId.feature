Feature: verify program by id

  Scenario Outline: Update program by program ID
    Given The service with URL and update path
    When "<programName>","<programDescription>","<programStatus>",creationTime,lastModTime are updated
    And PUT request is made
    Then Save program by <programId>
    And Validate Put status code is 200
    And Validate required fields "<programId>","<programName>","<programDescription>","<programStatus>"

    Examples: 
      | programId | programName                          | programDescription       | programStatus  |
      |      7078 | Jan23-Mighty5testers-SDET-005-31-123 | API-Hackathon1-postmanss | Active         |
      |      7077 | Jan23-Mighty5testers-SDET-005-30-123 | Cucumber-testng          | Active         |
