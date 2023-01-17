Feature: verify program by id

  Scenario Outline: Update program by program ID
    Given The service with URL and update by programId path
    When "<programName>","<programDescription>","<programStatus>",creationTime,lastModTime are updated
    And PUT request is made
    Then Save program by programId <programId>
    And Validate PUT response status code is 200 for programId
    And Validate required fields "<programId>","<programName>","<programDescription>","<programStatus>"

    Examples: 
      | programId | programName                          | programDescription       | programStatus  |
      |      7078 | Jan23-Mighty5testers-SDET-005-31-124 | API-Hackathon1-postmanss | Active         |
      |      7077 | Jan23-Mighty5testers-SDET-005-30-125 | Cucumber-testng          | Active         |
