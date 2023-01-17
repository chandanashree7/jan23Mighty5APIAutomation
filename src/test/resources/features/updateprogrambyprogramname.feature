Feature: verify program by Name

  Scenario Outline: Update program by program Name
    Given A service with URL and update by programName path
    When "<programName>","<programDescription>","<programStatus>",creationTime,lastModTime are modifed for programName
    And PUT request is made for programName
    Then Save program by programName "<programName>"
    And Validate PUT response status code is 200 for programName
    And Validate required fields <programId>,"<programName>","<programDescription>","<programStatus>" for programName

    Examples: 
      | programId | programName                          | programDescription       | programStatus  |
      |      7078 | Jan23-Mighty5testers-SDET-005-31-124 | API-Hackathon1-postmanss | Active         |
      |      7077 | Jan23-Mighty5testers-SDET-005-30-125 | Cucumber-testng testing  | Active         |
