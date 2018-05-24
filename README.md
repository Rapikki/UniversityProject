#Technologies which using :

Java 1.8, Maven, JUnit, Postgres;

#Plugins : 

Checkstyle

#To run this project locally :
1)At first u must clone it (-git clone http://git.foxminded.com.ua/Yaroslav/DomainLayer.git ).
2)Move to the home path "DomainLayer/".
3)Open by your IDEA : "File - Open"(IntelliJ IDEA)
                      "File - Import - General - File System" (Eclipse)
4)Choose in your workspace "Main.java" build and run (Shift + F10) -> IntelliJ IDEA.
                                                            (Ctrl + Shift + F11) -> Eclipse.
5)Well done.

The plugin "checkstyle" is in src/checkstyle folder.
It consists of 2 parts : jar and xml(configurated rules);

#Task description :

     Task 9 :
 
Create JAVA project based on University UML class diagram from the previous task.
Implement domain logic for University.
Write JUnit tests for the created application.

    Task 10 :

Create plain JDBC based DAO for decomposed domain objects using DriverManager.
    
    Task 11 :
    
Add custom exceptions and logging.

    Task 12 : 
    
Convert current project to Maven format.

    Task 13 : 
    
Create status pages (read data from dao - show it in JSP).    

    Task 14 :
    
Create CRUD pages for decomposed domain objects (servlets + JSP).    

    Task 15 : 

Create DataSource in web-project configuration files. Switch DAO layer to lookup DataSource by JNDI name and use it instead of simple JDBC connection.

    Task 16 : 
        
Rewrite DAO code to inject DataSource to all DAO objects using Spring IoC framework.
        