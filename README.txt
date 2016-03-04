Java 1
=============================================================================================
Everything in the task description(http://codeblog.dk/wordpress/?p=123) has been implemented in the project.

+ Everything is commented with Java DOC
+ UML Diagram added

Java 2
=============================================================================================
UnitTest:
I've added some unit tests for mostly Sets/Gets of Player and Monster.
CobatTest have a couple of unit tests for calculating damage.
I've used unit test to easily test DB connections, but it's only for the console output.

XML:
When creating a new player, the program will then automatically save the player to XML.

JDBC:
(Note: If you don't have a mysql server running with corresponding DB structure then the
console program will still run, but with a good amount of SQLExceptions)

When starting the game you have the option to list all players currently in the DB, and when you
create a new player, then the program will also automatically add your player to the DB.

While moving around on the map, you then have the option to type "Combat" and you will get
a combat history from the DB for specifically your playerName.

Java ServerFaces:
I've implemented the JSF lib to the project and been using Tomcat Version 9 to test the
project.

The webapp wil start with StartForm.xhtml and then you will have the option to either create
a new player or show players from the DB. 
