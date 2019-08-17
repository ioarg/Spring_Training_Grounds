##Spring Training Grounds

This is a Web Application project for experimenting with different features of 
Java and Spring. It's meant for personal testing and experimentation only, 
created to avoid setting up new projects for each test. 

The homepage contains buttons that link to the websites corresponding to each 
training topic. 

Topics so far :

* A list of restaurant menu dishes, with options to filter them according to 
a certain attribute using Java Streams in the back-end.
* Changing the locale from English to Greek and replacing some of the homepage's
text, using "properties" files, MessageSource and LocaleResolver
* Simple CRUD project for managing customer data with Hibernate and MySQL.
To make asynchronous updates to the web page instead of re-rendering it, 
the Client (browser) sends Ajax requests with JSON data and the server responds
with JSON data as well. Jackson was used as a dependency for object mapping.
* REST endpoints :
    * Hello greeting message : /rest_proj1/hello
    * A small list of students and their information (no database)
        * /rest_proj1/students - all students
        * /rest_proj1/students/{student id} - specific student, error message 
        for invalid id included