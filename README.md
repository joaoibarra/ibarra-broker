# ibarra-broker

Ibarra Broker is a small app that uses a mock api provide by https://designer.mocky.io. I used the MVVM architecture with Koin dependency injection. Also, I created tests using JUnit and MockK.

### Checkout the project ###

For checkout the project, just clone the repo address:

```git clone https://github.com/joaoibarra/ibarra-broker.git```

### What this project do ###
- A list with properties;

### What's next ###
- Implement UI tests;
- Improve layout and interactions;
- Create a detail's page for properties;
- Used Room for database and Retrofit with a mock API, so will be easy to connect with any API or create a pagination;

### Run tests ###
Its possible to run unit tests directly in IDEA or using the command below:

``` ./gradlew test```

And for UI/Database tests:

``` ./gradlew connectedAndroidTest```
