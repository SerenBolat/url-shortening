# URL Shortening Service
### This is simple URL shortening Service

@author Seren Bolat


 
## USAGE
## SHORTENING 
### Take a URL and return a much shorter URL.
 - Send POST request to the (http://<span></span>localhost:8080/generate) with JSON data 
 
    `{
   "url":"http://localhost:8080/generate"
   }` 
   
 - Response
 
    `{
   "shortUrl": "http://localhost:8080/0kN65m"
   }`

## REDIRECTION
### Take a short URL and redirect to the original URL.
- Send GET request to the (http://<span></span>localhost:8080/0kN65m)

- Response

  `{
  "shortUrl": "http://localhost:8080/generate"
  }`

## Custom URL Shortening
###  Allow the users to pick custom shortened URL.
- Send POST request to the (http://<span></span>localhost:8080/customGenerate) with JSON data

  `{
  "shortUrl":"test",
  "originalUrl":"http://localhost:8080/generate"
  }`

- Response

  `{
  "shortUrl": "http://localhost:8080/test"
  }`
- 
## STATISTICS
### How many people clicked the shortened URL

 - Send GET request to (http://<span></span>localhost:8080/statistic/shortUrl)
   
   `{
   "test": 4
   }`

## Application Components

|    Package    |                  Description                 |
|---------------|----------------------------------------------|
|  Controller   |  The Rest Interface and Implementation.      |
|  Service      |  It contains the business logic              |
|  Repository   |  It contains all the data repositories       |

## Technology Stack

- Spring Framework
- Hibernate
- Java 
- Maven
- Rest

## Project Setup

- git clone https://github.com/SerenBolat/solactive-case-study.git
- mvn clean install