
@BraveNewCoin
Feature: BraveNewCoin API scenarios

Rule: When user send a POST request to the endpoint, user receives a Token to acces

Scenario: As a user can retrieve a Token when 
Given I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
When  user sends a POST request with the valid body to the endpoint /oauth/token endpoint
Then user can receive a valid in the response


