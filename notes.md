```
mvn clean install -pl javalin -am
mvn test -Dtest=TestSinglePageMode
```

# First failure TestSingelPageMode

- /script.js return /pubilc/html.html instead of /public/script.js