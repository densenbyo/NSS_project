# About Project
A project for memorizing foreign words based on the flashcard and periodic learning repetition methods. 
Flashcard is an abstract two-sided card, the word in the user’s native language is written on the hidden side,
and the word in the chosen language is written on another side.

# Technologies
- [Spring Boot](https://spring.io/)
- [ReactJS](https://reactjs.org/)
- [Bootstrap](https://react-bootstrap.github.io/)
- [Kafka](https://kafka.apache.org)
- [Hazelcast](https://hazelcast.com)
- [Elastic](https://en.wikipedia.org/wiki/Elasticsearch)

# Basic Instruction
You can test backend side of webapp on [Pocket Lingo](https://pocketlingo.herokuapp.com).
**Before testing, be sure that you are using postman or something similar to it.**
Application is needed to be started on web server. Before starting need to configure settings
of application as link to database.
Go to 
**`src/main/resources/application.properties`**.
Our application uses PostgreSQL.

Application can be used in two ways.
First, simple backend application with HTTP/JSON requests and
Second, with simple front-end.

To start with front-end will be needed to install all compuslory node_modules
by following commands in terminal.

```
cd path/src/main/webapp/pocket
-npm install
-npm start
```

To start without front-end. Run LingoApplication in IDE.

Application has been tested by [Postman](https://www.postman.com/).
In our repository can be found our simple postman scenario.

List of [REST commands](https://docs.google.com/spreadsheets/d/1Ygypo5pBWKg3PPsv57oQaRn93tDpLtIgThrzHjo3Ic8/edit#gid=0).

# Getting Started with link above
Already exists 2 type of user.
- Admin
```
  {
    "username": "admin",
    "password": "admin"
   }
```
- User
```
  {
    "username": "user",
    "password": "user"
   }
```

# Getting Started only with Spring on local
Go to Run -> Edit configuration -> Modify options. Add there Environment variables.
In Environment variables text box write `spring.config.name=localcfg`
Run LingoApplication in IDE

# Start with React Front-End
In NodeJS or IDE terminal

```
cd path/src/main/webapp/pocket
npm install
npm start 
```

**back-end has to be started to work correctly**

# Start with Kafka
1. Start on local zookeeper server
2. Start on local kafka server
3. Start Spring
4. Start React `NOT NECESSARY`

# Configure Setting
Go to 
**`src/main/resources/localcfg.properties`**

Change settings, to needed db

``` 
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5050/postgres
spring.datasource.username=postgres 
spring.datasource.password=postgres
```

# Set admin
After registration user. To make user admin. Need to change in DB his role to 'ADMIN'

``` 
User = {
"username":"admin",
"mail":"admin@admin.com",
"password": "admin"
}
```

# Requirements
Společné požadavky:
- [X] vyber vhodne technologie a jazyka: Java/SpringBoot, Java, C#, … (povinné) `DONE`
- [X] readme v gitu s popisem co je hotove a kde se funkcionalita nachazi (povinné) `DONE`
- [X] vyuziti spolecne DB (relacni nebo grafova) (povinné) `DONE`
- [X] vyuziti cache (napriklad Hazelcast) (volitelné -2b pokud není) `DONE`
- [x] vyuziti messaging principu (Kafka nebo JMS) (volitelné -2b pokud není) `DONE`
- [X] aplikace bude zabezpecena pomoci bud basic authorization nebo pomoci OAuth2 (volitelné -2b
pokud není) `DONE`
- [x] vyuziti Inteceptors (alespon jedna trida) - napriklad na logovani (prijde request a zapiseme ho do
logu) (volitelné -2b pokud není) `DONE`
- [X] vyuziti jedne z technologie: SOAP, REST, graphQL, Java RMI, Corba, XML-RPC (volitelné -2b
pokud není) `DONE`
- [X] nasazeni na produkcni server napriklad Heroku (povinné) `DONE`
- [X] vyber vhodne architektury (event base, pipe and filter, …) (povinné) `DONE`
- [X] inicializacni postup (jak aplikaci deploynout, kde jsou zakladni data do nove DB typu admin apod)
(povinné) `DONE`
- [ ] vyuziti elasticsearch (volitelné -2b pokud není) `DONE`
- [X] pouziti alespon 5 design patternu (musi davat smysl :) ) (povinné) `DONE`
- [X] za kazdeho clena tymu 2 UC (use cases - aby SW nebyl trivialni) (povinné)
bonusove body `DONE`
- [ ] cloud sluzby (azure, aws) +2 bod `LOADING`

# Contacts
1. Ivan Godunov
    - shalaiva@fel.cvut.cz
2. Mukan Atazhanov
   - atazhanovmukan@gmail.com
3. Anna Kachmasheva
   - annakachmasheva@gmail.com
4. Aiya Rakhimova
   - rakhiaiy@fel.cvut.cz
