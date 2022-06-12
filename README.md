# SOCRUD
[![CircleCI](https://circleci.com/gh/EpiTobby/SOCRUD/tree/master.svg?style=svg&circle-token=28bdad8d364b9293d2505594c58e15188dbfe5c9)](https://circleci.com/gh/EpiTobby/SOCRUD/tree/master)
[![Java CI with Maven](https://github.com/EpiTobby/SOCRUD/actions/workflows/maven.yml/badge.svg)](https://github.com/EpiTobby/SOCRUD/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=EpiTobby_SOCRUD&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=EpiTobby_SOCRUD)

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
Clone the repo and run `docker-compose up`\
This command will download all the necessary images and start the application.

Some endpoints require the user to be authenticated with an admin account. A default admin user can be created with the following command:
```bash
echo "DEFAULT_USERNAME=root
DEFAULT_PASSWORD=root" > .env && docker-compose up
```
You can then login on /admin/login using `root` as username and password and use the returned jwt for subsequent requests.

# Tests
Run `mvn test`

# Design Pattern
We have implemented 2 design patterns:
* Builder in [UpdateSubjectRequest.java](https://github.com/EpiTobby/SOCRUD/blob/master/src/main/java/fr/tobby/socrud/model/request/UpdateSubjectRequest.java). The builder is useful to build in a convinient and explanatory way objects that require several arguments. It can come particularly handy while making tests. The builder in this class is for demonstration purposes, in other classes we use the `@Builder` shortcut annotation from lombok.
* Template in [ProgramComparatorTemplate.java](https://github.com/EpiTobby/SOCRUD/blob/master/src/main/java/fr/tobby/socrud/service/ProgramComparator.java). This design pattern is used to make an algorithm which implementation can change over time. It defines a general behavior while leaving implementation details to subclasses. This way we can provide several variants of the algorithm regrouped under the same interface and usage. In the scope of this project, as the code is not destined to evolve, this is clearly a YAGNI anti pattern.

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
