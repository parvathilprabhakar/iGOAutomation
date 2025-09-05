Feature: Login Feature

###################################################################################

  Scenario: Registration on ParaBank Demo site
    Given I am on the ParaBank home page
    When I click on Register link
    Then I should be redirected to the SignUp Page
    And I fill the details and register
    Then I navigate to all account service pages
    And I logout of my account

###################################################################################
  @Testplp
  Scenario Outline: ParaBank Login
    Given I am on the ParaBank home page
    When I login to my ParaBank account with '<Username>' and '<Password>'

    Examples:
    |Username|Password|
    |inco    |test    |
