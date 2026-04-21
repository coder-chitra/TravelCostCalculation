Feature: Login Negative Test

  Scenario: Invalid Google login
    Given I am on Booking.com homepage for login
    When I try to login with invalid email "wrong@emailaddress"
    Then Error message should be displayed
