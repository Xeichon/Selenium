Feature: Registration Page
  Scenario: Registering a new user
    Given Im on the page "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When Fill "01/09/1924" in "dp"
    And Click on "#signup_form > .row:nth-child(3)"
    And Fill "Bob" in "member_firstname"
    And Fill "Svensson" in "member_lastname"
    And Fill "erik@live.se" in "member_emailaddress"
    And Fill "erik@live.se" in "member_confirmemailaddress"

    And Click on ".md-checkbox:nth-child(2) > label > .box"
    And Click on ".md-checkbox > .md-checkbox:nth-child(1) > label"
    But Password in "signupunlicenced_password" and "signupunlicenced_confirmpassword"
    And Click on "#signup_form > div > input[name='join']"
    Then I should see a confirmation message "Registration successful"
