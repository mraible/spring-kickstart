Download and install Maven 2.0.4 (download from http://maven.apache.org/download.html)

switch to the kickstart directory and run:
  mvn clean package
  mvn jetty:run
 
This project was originally created using 
  mvn archetype:create -DgroupId=spring.kickstart app -DartifactId=kickstart -DarchetypeArtifactId=maven-archetype-webapp