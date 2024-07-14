Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.
    Assumptions : Password length should be 8 char, at least 1 Uppercase , 1 lowercase, 1 Special char, and following special char not allowed : ()%= And Username is prefilled.

    Scenario: (Success)Verify Successful password change with valid input
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "Correct@Pass1"
      And the user confirms their new password "Correct@Pass1"
      Then the password change should be successful
      And a confirmation message should be displayed

    Scenario: (Fail)Verify password change when new password and confirm password do not match
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "Correct@Pass1"
      And the user confirms their new password "Different@Pass1"
      Then the password change should fail
      And an error message should be displayed stating "New password and confirm password do not match"

    Scenario: (Fail)Verify change password with less than 8 characters
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "Corr1!"
      And the user confirms their new password "Corr1!"
      Then the password change should fail
      And an error message should be displayed stating "Password must be at least 8 characters long"

    Scenario: (Fail)Verify change password without uppercase letter
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "correct@pass1"
      And the user confirms their new password "correct@pass1"
      Then the password change should fail
      And an error message should be displayed stating "Password must contain at least one uppercase letter"

    Scenario: (Fail)Verify change password without lowercase letter
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "CORRECT@PASS1"
      And the user confirms their new password "CORRECT@PASS1"
      Then the password change should fail
      And an error message should be displayed stating "Password must contain at least one lowercase letter"

    Scenario: (Fail)Verify change password without special character
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "CorrectPass1"
      And the user confirms their new password "CorrectPass1"
      Then the password change should fail
      And an error message should be displayed stating "Password must contain at least one special character"

    Scenario Outline:(Fail)Verify change password with not allowed special characters
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "<newPassWord>"
      And the user confirms their new password "<confirmPassWord>"
      Then the password change should fail
      And an error message should be displayed stating "Password contains disallowed special characters"
     Examples:
      |newPassWord|confirmPassWord|
      |CorrectPass(1|CorrectPass(1|
      |CorrectPass)1|CorrectPass)1|
      |CorrectPass%1|CorrectPass%1|
      |CorrectPass=1|CorrectPass=1|

    Scenario Outline: (Success)Verify Successful change password with mixed valid characters
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password "<newPassWord>"
      And the user confirms their new password "<confirmPassWord>"
      Then the password change should be successful
      And a confirmation message should be displayed
      Examples:
        |newPassWord|confirmPassWord|
        |CorrectP!$£1|CorrectP!$£1  |
        |CorrectP.?1|CorrectPass.?1|
        |CorrectPass%1|CorrectPass%1|
        |CorrectPass=1|CorrectPass=1|

    Scenario: (Fail)Verify change password with invalid old password
      Given the user is logged in and on change password screen
      When the user enters invalid valid old password
      And the user enters their new password "Correct@Pass1"
      And the user confirms their new password "Correct@Pass1"
      Then the password change should fail
      And an error message should be displayed stating "Username and password did not match"

    Scenario: (Fail)Verify change password with new password which is used in previous 3 password change
      Given the user is logged in and on change password screen
      When the user enters valid old password
      And the user enters their new password which is used on last 2 times
      And the user confirms their new password which is used on last 2 times
      Then the password change should fail
      And an error message should be displayed stating "The new password entered should not be from last 2 password histories"

