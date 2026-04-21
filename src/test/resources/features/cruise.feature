Feature: Cruise Search

  Scenario: Search cruises in London
    Given I am on Booking.com homepage for cruise search
    When I search cruises in "London"
    Then Cruise results should be displayed
