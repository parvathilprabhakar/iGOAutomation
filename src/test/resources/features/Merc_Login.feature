Feature: Login Feature

#  Background:
#    Given I prepare for execution


  Scenario Outline: Login on Mercury Demo page
    Given I am on the login page
    When I enter valid credentials username as '<username>' and password as '<password>'
    Then I should be redirected to the homepage

    Examples:
      | username | password |
      | aaa      | bbb      |