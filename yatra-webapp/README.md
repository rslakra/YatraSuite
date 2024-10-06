## Project Overview
This is the Java API backend implementation of the MovR ui_gateway application. 
The application demonstrates how to integrate a Java/Spring Boot 
implementation with other microservices. This application can run
standalone to provide a REST API, or in conjunction with a front
end implemented with React.js. 
However, this application requires the vehicles, rides, and users microservices to be running at the same time. Please see their individual README.md files.

## Requirements

- [OpenJDK 11](https://openjdk.org/projects/jdk/11/)
- [Install Sass](https://sass-lang.com/install/)


## Running the Application

To run the application you can execute the `runMaven.sh` script.

```
./runMaven.sh
```

This will compile the application with Maven, run the tests, and execute.

## References:

- [dart-sass-maven-plugin](https://github.com/cleydyr/dart-sass-maven-plugin)
- [How to Use Sass with CSS](https://www.freecodecamp.org/news/how-to-use-sass-with-css/)
