# AllStore
RESTful Web application using Spring, Hibernate, And AngularJS

This project is running at http://85.255.8.105:8080 

#Adding GUI
Clone this repo: https://github.com/TomOw/AllStoreGui  
Create folders: `src/main/resources/templates` and `src/main/resources/static`.  
Copy main.html to `src/main/resources/templates`.  
Copy the rest of the files to `src/main/resources/static`.
#How to run

In order to run REST backend alone:   
Clone the repository  
Add file application.properties in src/main/resources
And specify the Database connection properties.  
```db.driver:  
db.url:  
db.username:  
db.password:  

hibernate.dialect:   
hibernate.show_sql: true  
hibernate.hbm2ddl.auto: update  
entitymanager.packagesToScan: com.shoponeo  
hibernate.connection.useUnicode: true  
hibernate.connection.characterEncoding: UTF-8  

spring.thymeleaf.mode=LEGACYHTML5  
spring.thymeleaf.cache=false
```

Then in cmd/terminal type: `mvn spring-boot:run` in directory where pom.xml is.

