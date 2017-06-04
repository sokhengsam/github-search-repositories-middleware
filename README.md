# github-search-repositories-middleware

The middle ware provides services which can search github repositories with different response fromat. Api can response as xml or json format based request header. Technologies were built middleware such as spring framework(spring boot web, spring cache
,spring rest template), java servlet 3, slf4j and maven build tool.

# How to build and run

- Build project: 

  $ mvn clean install

- Run project:

  $ mvn spring-boot:run
  
# Apis to search respositories

/api/repositories/v1

Parameters
<table>

-query: name of repository - mandatory parameter

-page: number of page - optional parameter(default: 0)

-pageSize: number items per page - optional parameter(default: 10,maximum = 100)

-sortBy: The sort field - optional parameter(stars, forks, or updated, Default: stars)

-orderDirection: direction of sort - optional parameter( asc or desc, default: desc)




