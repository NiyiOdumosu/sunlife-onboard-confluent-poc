# sunlife-onboard-confluent-poc


## What is sunlife-onboard-confluent-poc?

This application provides a RESTful API layer to create a limited amount of Confluent components such as topics, schemas, and RBAC roles. 

## How does sunlife-onboard-confluent-poc work? 

The application ultimately outputs a config file which is then used to by [JulieOps](https://github.com/kafka-ops/julie) to provision new resources for the confluent cluster. JulieOps is an automation service that creates Confluent clusters by using yaml configs.

This poc uses the response payload to generate a descriptor yaml file that has the configs of the resources needed for the cluster.

## How to run sunlife-onboard-confluent-poc?

First clean the package and build, so that it deletes the jar file from the `target/` folder rebuilds it with the latest code.

```
mvn clean package
```

Next execute the jar file in the `target/` folder.

```
java -jar target/sunlife-poc-0.0.1-SNAPSHOT.jar
```


Once the spring boot app starts, visit the swagger site.

```localhost:8081/swagger-ui.html```

You can also visit at the h2 in-memory database.

```
localhost:8081/h2-console
```

Submit the topic config values as a post call to the `/topic` endpoint.

This will generate a file named `src/main/resources/descriptor.yml`.

