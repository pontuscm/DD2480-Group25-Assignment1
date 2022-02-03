# DD2480-Group25-Assignment1
## Assignment #1 - DECIDE
## A decision program for an anti-ballistic missile system
This program, known as DECIDE, generates a boolean signal to determine whether an interceptor missile should be launched.

The program receives input in the form of data points on a two-dimensional plane as well as some other parameters.
This input is measured against a set of Launch Interceptor Conditions (LICs). The boolean output of these conditions make up the Conditions Met Vector (CMV).

Not all LICs are taken into account at every run instance of the DECIDE function - the values of the Preliminary Unlocking Vector (PUV) represent which LICs actually matter in every run.

## Building & Testing
Prerequisites: [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Maven](https://maven.apache.org/)

Compiling the source code:
```
mvn compile
```

Running tests:
```
mvn test
```

## Project structure
Source files:
[src/main/java/se/kth/dd2480/group25/assignment1](src/main/java/se/kth/dd2480/group25/assignment1)

Test files:
[src/test/java/se/kth/dd2480/group25/assignment1](src/test/java/se/kth/dd2480/group25/assignment1)

## Contribution statement
Henrik - Implemented LICs 0, 7, and 12, including unittests. Wrote a ReadMe.

Patryk - Implemented LICs 3, 10, and 14, including unittests. Created initial project structure and added Maven (including build tool documentation in the ReadMe).

Emilia - Implemented LICs 2, 4, and 9, including unittests. Created classes for the various matrices used by the program.

Xinyi - Implemented LICs 1, 8, and 13, including unittests. Refractoring of helper functions.

Pontus - Implemented LICs 5, 11, and 6, including unittests.

Extra feature: We've adopted continuous integration with automated builds after each code push. Each automated build runs all tests and reports the outcome on any relevant pull request, giving us more confidence when merging code onto main. We've used GitHub actions together with maven to set this up. 
