Feature: Hotel Search

  Scenario: Search hotels in Nairobi
    Given I am on Booking.com homepage for hotel search
    When I search hotels in "Nairobi"
    Then Hotel results should be displayed
