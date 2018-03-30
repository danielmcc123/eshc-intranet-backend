## eshc-intranet-backend

Backend REST service used to manage cooperative living. It's main functionality is to ensure productive coordination of the working groups within the Edinburgh Student Housing Cooperative.
## Tools Used

* Recommended IDE: Intellij Idea
* Framework: Spring Boot
* Build tool: Gradle
* Documentation: Swagger (OpenAPI)
* Testing: JUnit
* JSON de/serialisation: Jackson
* Server: Tomcat (built into Spring)
* Database: Postgres (H2 for in-memory testing)


## Setting up the environment
Download and install IntelliJ Idea, you can get Ultimate Edition for one year if you are a student but the free Community Edition will work perfectly fine. Clone this repository into a folder on your development machine. You can open it in IntelliJ by going to `File > Open` and selecting `eshc-intranet-backend`. IntelliJ will then load the project then you will be prompted by Gradle to download the dependencies, select `Import Changes`. 

Now you can run the project to confirm that the setup was successful, you can do this using one of two ways:

1.) Using the terminal: Navigate to `eshc-intranet-backend` in terminal or Command Prompt on Windows, then run the command `gradlew tasks` on Windows or `./gradlew tasks` on Linux and Mac. The tasks command will show you all of the available tasks in the project, to run you will want to use the `gradlew bootRun`. After a few seconds ESHC should launch and you will see something like this `Started EshcBackendApplication in 13.328 seconds (JVM running for 14.053)`. IntelliJ as a built in terminal and you can find it at the bottom left of the screen.

2.) Using the Gradle tab: On the right hand side of your screen in IntelliJ you will find the Gradle tab, navigate to `eshc-intranet-backend > Tasks > Application > bootRun` and double click.
