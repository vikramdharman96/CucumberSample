@all
Feature: User Login
Registered user should be able to login to access account details

Background:
Given User navigates to login page

@login @smoke
Scenario Outline: Login with valid credentials
When User enters valid email <email>
And Enters valid password <password>
And Click on login button
Then User should login successfully

Examples:
|email       								|password|
|vikramdharman96@gmail.com 	|9843117279|


@invalidlogin 	
Scenario: Login with invalid credentials
When User enters invalid Email address
And Enters invalid Password "123456789"
And Click on login button
Then User should get a proper warning message about credentials mismatch