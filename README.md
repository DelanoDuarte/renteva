# Getting Started
This is a custom example of folder by feature using Java and Spring Boot.

## API Health
http://localhost:8090/actuator/health

## API Swagger Docs
http://localhost:8090/swagger-ui/

## Guides

Register a new user as **Owner** or **Renter**

#### Owner
**POST** http://localhost:8090/user/owner

#### Renter
**POST** http://localhost:8090/user/renter

Then, authenticate with the email and password created.

**POST** http://localhost:8090/user/sign-in

In case of you receive something like the response below, you're successfully 
authenticated.

```json
{
    "id": 1,
    "fullName": "James Sullivan",
    "email": "j.sulli@email.com",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjMxNDcxMzQzfQ.PUb0GzqTCHsaZwEOY9XTTf6tsDSTuscGaRB7Sp82cnQaX5vDSyTFvMG3eJ4TdBGITFtF5QkMPRTm84KGLGLUFA"
}
```


### Additional Links


