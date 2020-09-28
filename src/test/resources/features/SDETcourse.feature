@sdetCourse
  Feature: SDET course api tests

    Scenario: Verify resource is active
      Given Resource "sdetcourse" is up and running
      When I send GET request using query parameter "name" equal to "API Course"
      Then Response code should be 200
      Then Print out and log the response body