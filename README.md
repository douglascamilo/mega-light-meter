# Kneat Test - Instructions
The application was built with Java and Spring Boot.

You need Java 8 or newer installed before running the application.

The runnable jar will be in ${project_folder}/target/mega-light-meter-1.0.0.jar

## Running the application
The Application is able for running in two modes. Standalone or HTTP mode.

## Running in Standalone mode
Open terminal and navigate to project target folder and run the following command:

```bash
java -jar mega-light-meter-1.0.0.jar ${DISTANCE_IN_MGLT}
```

PS: Where ${DISTANCE_IN_MGLT} need to be replaced by desired distance for the calculation. For example: java -jar mega-light-meter-1.0.0.jar 900000

When running in this mode, the application will log all the requests to swapi.co and, in the end, logs all the starship's names and required stops for resupply.

## Running in HTTP mode

Open terminal and navigate to project target folder and run the following command:

```bash
java -jar mega-light-meter-1.0.0.jar
```

When the application is ready, it will log URL to make http requests. 

Just need to make a simple GET request, for the logged URL, adding the distance in MGLT at the end. For example: http://localhost:8080/calculate-stops/900000
