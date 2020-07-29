Feature: Test Latest API features

  Scenario: Validate latest api status code
    Given Rates API for Latest Foreign Exchange rates
    When The API is available
    Then Validate response status code "200"

  Scenario: Validate latest api status code
    Given Rates API for Latest Foreign Exchange rates
    When The API is available
    Then Validate latest response
