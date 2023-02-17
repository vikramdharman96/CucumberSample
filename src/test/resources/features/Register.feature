@all
Feature: User Registeration

Background:
Given User navigates to Register Account Page

@register @smoke
Scenario: Register with mandatory fields
When User enters below details into the fields
|firstname    |Vikram|
|lastname			|Dharman|
|telephone 		|9843117279|
|password			|9843117279|

And Selects privacy policy field
And Click on Continue button
Then Account should get successfully created

@registerallfields
Scenario: Register with all fields
When User enters below details into the fields
|firstname    |Vikram|
|lastname			|Dharman|
|telephone 		|9843117279|
|password			|9843117279|

And Select Yes for Newsletter
And Selects privacy policy field
And Click on Continue button
Then Account should get successfully created

Scenario: User creates duplicate account
When User enters below details into the fields with duplicate account
|firstname    |Vikram|
|lastname			|Dharman|
|emailaddress |vikramdharman96@gmail.com|
|telephone 		|9843117279|
|password			|9843117279|

And Selects privacy policy field
And Click on Continue button
Then User should get proper warning about duplicate email
