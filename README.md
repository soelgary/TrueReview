TrueReview
==========

True Review is an attempt to get real reviews/ratings of politicians

Database Migrations
===================

True Review uses liquibase to do all database migrations. Follow these steps in order to make a migration.

1) Modify the migrations.xml file to include the changes that you need

2) cd into the data project

3) run ```mvn clean install```. This will update the local maven repository witht he changes.

4) cd into the service project

5) run ```mvn clean compile assembly:single```. This will update the service jar with the changes made to the migrations.xml file.

6) run ```java -jar target/service-0.0.1-SNAPSHOT-jar-with-dependencies.jar db status service.yml```. This will show the current db status

7) run ```java -jar target/service-0.0.1-SNAPSHOT-jar-with-dependencies.jar db tag service.yml YYYY-MM-DD-pre-user-move```. This will tag the db at this instant.

8) run ```java -jar target/service-0.0.1-SNAPSHOT-jar-with-dependencies.jar db migrate service.yml```. Actually performs the migration.

9) run ```java -jar target/service-0.0.1-SNAPSHOT-jar-with-dependencies.jar db status service.yml```. Check the new status to make sure everything is up to date.
