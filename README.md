To run the application using Gradle, run the following command from within the `complete` folder:

`gradlew bootRun`

To run the application using Maven, run the following command from within the `complete` folder:

`mvnw spring-boot:run`

If you want to build the JAR file instead and run it, use the following commands:

For Gradle: `gradlew build`

For Maven: `mvnw clean package`

Run the JAR file as follows:

`java -jar build/libs/log-sender-service-0.1.0.jar`