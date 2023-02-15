## Kameleoon Trial Task

### Project requirements:

- source code should be published on https://github.com/;
- Docker image should be published on https://hub.docker.com/;
- project should have docker-compose.yaml for local deploy, OR you can deploy it on a public hosting provider (Heroku, etc);
- use any in-memory database (Derby, H2, SQLite);
- use Java and Spring Boot.

### The API should allow:

- creation of a user account (deletion and other CRUD operations not requested). User properties are name, email, password and date of creation;
- addition, viewing (including a method to get a random quote), modification, and deletion of quotes (real deletion from the database, not just via an archive flag). Quote properties include content, date of creation / update, link to user who posted it, link to votes;
- voting on quotes (either upvote or downvote);
- view of the top and worse 10 quotes, the details of each quote, and ideally a graph of the evolution of the votes over time.


### API:

- create user: POST ```http://localhost:8080/api/user/```
- create quote: POST ```http://localhost:8080/api/quote/```
- edit quote: PUT ```http://localhost:8080/api/quote/```
- delete quote: DELETE ```http://localhost:8080/api/quote/{id}```
- get details of the quote: GET ```http://localhost:8080/api/quote/{id}```
- get random quote: GET ```http://localhost:8080/api/quote/random```
- get best 10 quotes by score: GET ```http://localhost:8080/api/quote/best10```
- get worst 10 quotes by score: GET ```http://localhost:8080/api/quote/worst10```
- upvote: POST ```http://localhost:8080/api/vote/{userId}/{quoteId}/{true}```
- downvote: POST ```http://localhost:8080/api/vote/{userId}/{quoteId}/{false}```

### Technologies:
- Java 17
- Maven 3.8.4,
- H2 database
- Lombok

### Environment:
Install:
- JDK 17.0.1
- Maven 3.8.4
- H2 database

### Contacts
Feel free to write me if you have some notes.

[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/levgross)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:levgross@gmail.com)&nbsp;&nbsp;
