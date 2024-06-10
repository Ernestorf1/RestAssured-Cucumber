Feature: Request Example for Udemy


Scenario: GET Test to endpoint
Given User sends a get to the https://api.github.com URI
Then User gets a 200 status code


Scenario: Validate amount of inputs into response
Given user sends a get request to the https://jsonplaceholder.typicode.com URI
Then user validate there are 10 items on the /users endpoint


Scenario: validate an element is into response
Given user sends a get request to the https://jsonplaceholder.typicode.com URI
Then user validate there is a value: Bret in the response at /users endpoint

@API
Scenario: validate an nested element is into response
Given user sends a get request to the https://jsonplaceholder.typicode.com URI
Then user validate the nested value: Skiles Walks in the response at /users endpoint


