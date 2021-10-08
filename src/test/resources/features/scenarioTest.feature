@all
Feature: Login Choco

  @login @positive
  Scenario Outline: Login into choco app
    Given user launch the app
    When user taps on country code
    Then user is on the countries listing
    When user search for country or code <search>
    Then country <country> with code <code> is displayed in search result
    When user taps <country>
    Then user see <code> on screen
    When user enters phone number <phoneNumber>
    And user taps "Continue"
    Then user see "We just sent you an SMS with a code. Please type it below" on screen
    And user see "We sent the code to %s" on screen
    When user enter sms code "1234"
    Then user verify the success message "Welcome to Choco!!"
    Examples:
      | testcase                   | search  | country   | code  | phoneNumber   |
      | "search with country code" | "49"    | "Germany" | "+49" | "16200000001" |
      | "search with country name" | "Germa" | "Germany" | "+49" | "16200000001" |

  @login  @negative
  Scenario Outline: Search for non existential country
    Given user launch the app
    When user taps on country code
    Then user is on the countries listing
    When user search for country or code <search>
    Then user do not see "+"
    Examples:
      | testcase                        | search |
      | Login with invalid country code | "922"  |

  @login  @negative
  Scenario Outline: Country code is invalid
    Given user launch the app
    When user enters phone number <phoneNumber>
    And user taps "Continue"
    Then user see <errorMessage> on screen
    Examples:
      | phoneNumber   | errorMessage                                                            |
      | "16200000001" | "That phone number isn’t valid, are you sure you entered it correctly?" |

  @login @negative
  Scenario Outline: Invalid sms code
    Given user launch the app
    When user taps on country code
    When user search for country or code <search>
    Then country <country> with code <code> is displayed in search result
    When user taps <country>
    When user enters phone number <phoneNumber>
    And user taps "Continue"
    When user enter sms code <smsCode>
    Then user see <errorMessage> on screen
    Examples:
      | search | country   | code  | phoneNumber   | smsCode | errorMessage                                                                 |
      | "49"   | "Germany" | "+49" | "16200000001" | "4321"  | "The code you entered was incorrect, are you sure you entered it correctly?" |

  @login  @negative
  Scenario Outline: Phone number is invalid
    Given user launch the app
    When user taps on country code
    When user search for country or code <search>
    When user taps <country>
    Then user see <code> on screen
    When user enters phone number <phoneNumber>
    And user taps "Continue"
    Then user see <errorMessage> on screen
    Examples:
      | search | country   | code  | phoneNumber    | errorMessage                                                            |
      | "49"   | "Germany" | "+49" | "12345"        | "That phone number isn’t valid, are you sure you entered it correctly?" |
      | "49"   | "Germany" | "+49" | "162000000011" | "That phone number isn’t valid, are you sure you entered it correctly?" |
      | "49"   | "Germany" | "+49" | ""             | "That phone number isn’t valid, are you sure you entered it correctly?" |
