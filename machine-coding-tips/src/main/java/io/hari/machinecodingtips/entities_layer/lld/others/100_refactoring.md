- code level
    - create interface / abstract class
    - add comments
    - string --> enum 
    - java 8+ style coding : 
        - if else --> switch(),
        - pass functional interface as argument, 
        - Optional, 
        - Stream, Collectors, Collection
    - JAVA Varvs style coding :
      - Optional
      - try catch exception
    - DTO mapper in mapper package 
      - JPA DTO pattern (Optional): https://youtu.be/FQNxZD2dbaA 
    - testing
        - unit test (optional) + Integration testing using Test Container (Optional) 
        - API testing using automation : python robot framework (optional)
          - API testing suing intellij HTTP client
    - open api spec (optional) : 
        - https://www.youtube.com/playlist?list=PLvuLD3ZzP708WXcQZ8a4ayMO_akidI8CU , 
        - https://www.youtube.com/playlist?list=PLRLgS-J-AO5tPboupInzALf1CvD093lWd
    - use apache / google lib
- container & image
    - simple docker file (for dev testing), 
    - simple docker-compose file with external tools
    - sh file (build project + run dockerfile + run docker-compose)
    - k8s helm chart (optional)
    - buildpack (optional)
    
- spring boot app --> add swagger lib 
    - https://youtu.be/iaVBleTf88U , https://github.com/dailycodebuffer/Spring-MVC-Tutorials/tree/master/openapi-documentation
    - https://youtu.be/7bVQdUPbFvc
```xml
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.4.3</version>
  </dependency>
  ```
