# SOCRUD
SOCRUD is a REST API made in Java Spring Boot meeting the following need :
```
As a pedagogical manager in a new university, I need to digitalize the process 
of searching for studies for those interested in the university. In order for 
a person who wants to study to be able to search and find the right program that 
corresponds to him, we need to provide him with a tool that will match his keywords 
with the most relevant courses for him.
```

# Features
* Publish a program. This is reserved for the administration.
* List all programs.
* Get and edit the description of a program with the following information:
  * Title: Software Engineering
  * Campus: e.g. Paris
  * Duration: e.g. 24 MONTH
  * Type: e.g. Master
  * Fee: 12k
  * Presential: e.g. 50% Remote / 50% Presential
  * Starting: February 2023 session
  * Modules: S1:Subjects
  * Description: e.g. A master's degree dedicated to the study of information systems and their implementation in companies.
* Search for programs by degree and/or campus.
* Search for programs by keywords.
* Export program as PDF

# Documentation
All endpoints are documented using Swagger-ui.\
The Swagger-ui documentation can be found at `swagger-ui/index.html`.

# Usage
Run `docker-compose up`\
This command will download all the necessary images and start the application.\
Some endpoints require the user to be authenticated with an admin account. A default admin user can be created with the following command:
```bash
echo "DEFAULT_USERNAME=root
DEFAULT_PASSWORD=root" > .env && docker-compose up
```

# Tests
Run `mvn test`

# Design Pattern
We have implemented 2 design patterns:
* Builder in models/request/UpdateSubjectRequest.java
* Template in service/ProgramComparatorTemplate.java

# CICD
A CICD pipeline is setup and is composed of the following steps:
- Build the application
- Run tests
- Perform code analysis
- Build a docker image
- Push the image

The different platforms used to perform these steps are :
- [CircleCI](https://app.circleci.com/pipelines/github/EpiTobby/SOCRUD)
- [SonarCloud](https://sonarcloud.io/project/overview?id=EpiTobby_SOCRUD)
- [Docker Hub](https://hub.docker.com/r/gabray/socrud)
- [Github Actions](https://github.com/EpiTobby/SOCRUD/actions) (for redundancy on build and tests)

All these platforms, except for docker hub, are linked to this github repository to automatically trigger actions on push and pull requests.

# Authors
Gabriel Bouhnik\
Gabriel Rayzal\
Yanis Chaabane\
Tony Heng
