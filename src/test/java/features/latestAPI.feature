Feature: Test Latest API features
  @Positive @EUR
  Scenario: Validate latest api status code
    Given Foreign Exchange rates for date "latest"
    When The API is available
    Then Validate rate api response status code "200"

  @Positive @EUR
  Scenario: Validate latest api response
    Given Foreign Exchange rates for date "latest"
    When The API is available
    Then Validate rate api response

  @Negative @EUR
  Scenario: Validate error message for incorrect date
    Given Foreign Exchange rates for date "as"
    When The API is available
    Then Validate error message "dateFromatError"

  @Negative @EUR
  Scenario: Validate error message for incorrect url
    Given Foreign Exchange rates for date "/asdas/sadasd"
    When The API is available
    Then Validate error message "incorrectURLError"

  @Positive @EUR
  Scenario: Validate latest api response for future date
    Given Foreign Exchange rates for date "2021-12-31"
    When The API is available
    Then Validate rate api response

  @Positive
  Scenario: Validate latest api status code with different base currency
    Given Foreign Exchange rates for date "latest" with base "USD"
    When The API is available
    Then Validate rate api response status code "200"

  @Positive
  Scenario: Validate latest api response with different base currency
    Given Foreign Exchange rates for date "latest" with base "USD"
    When The API is available
    Then Validate rate api response