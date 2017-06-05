# github-search-repositories-middleware

The middle ware provides services which can search github repositories with different response fromat. Api can response as xml or json format based request header. Technologies were built middleware such as spring framework(spring boot web, spring cache
,spring rest template), java servlet 3, slf4j and maven build tool.

# Enviroment Setup

- Install maven 3+
- Install jdk 8+
- please make sure you setup correctly JAVA_HOME

# How to build and run

- Build project: 

  $ mvn clean install

- Run project:

  $ mvn spring-boot:run
  
# Apis to search respositories

/api/repositories/v1/search

Parameters:

- query: name of repository - mandatory parameter

- page: number of page - optional parameter(default: 0)

- pageSize: number items per page - optional parameter(default: 10,maximum = 100)

- sortBy: The sort field - optional parameter(stars, forks, or updated, Default: stars)

- orderDirection: direction of sort - optional parameter( asc or desc, default: desc)

Header:

Content-Type: application/json or application/xml

Example:

$ curl -H “Content-Type:application/json” http://localhost:8080/api/repositories/v1/search?query=liferay-portal




