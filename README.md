### Useful Maven Commands

```shell
mvn --version
mvn clean install
mvn spring-boot:run
```

```shell
docker build . -t duquejo/hotels
docker images
docker run -p 8080:8080 duquejo/hotels
```

#### Generating buildpacks with Maven

You must to preconfigurate in the pom.xml the following setup first:

```xml
<project>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    ...
                    <image>
                        <name>duquejo/${project.artifactId}</name>
                    </image>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Running the image generation service
```bash
mvn spring-boot:build-image -DskipTests
```

Publishing image in dockerhub
You must be logged in into dockerhub first. (Replace {} with the docker image name)
```bash
docker push docker.io/{docker-image-name}
```

### General config server configuration
- https://github.com/duquejo01/hotels-ms-configserver

### Traceability
We could use the following Spring Cloud Tools, __Sleuth__ and __Zipkin__.

For __Sleuth__, we need to know about the terms which are listed next:

- TraceId: General Microservice identification
- SpanId: Individual Microservice request identification call.
- Annotation: It will register general metrics such as request starting time, ending time, latency and healthiness for each service.
    - cs (Client Sent): The client starts a request.
    - sr (Server Received): The server gets and parses the request.
    - ss (Server Sent): Server sends a response to client.
    - cr (Client Received): The client gets the server response.

__Zipkin__ will be our server for all Sleuth traces and monitoring.
For Zipkin, we can configure a server directly, or we can also add a message broker for log queues handling.

```bash
docker run -d -p 9411:9411 openzipkin/zipkin
```


### Security
__Keycloak__: Authorization/Authentication server lib
- Authentication: Users veracity validation.
- Authorization: Users capability verification.
- Terms:
  - Realm: Credentials, roles and group sets. It's like a project.
  - Client: Keycloak Entity authorization for a user.

Standards:
- Oauth: Open authorization standard protocol
- OpenId: Identity layer for Oauth extension purposes.
- JWT: Json Web Token, open representation method for sharing data between two parts.

```bash
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.1 start-dev
```

Keycloak endpoint URLs
```text
http://localhost:8181/realms/booking/.well-known/openid-configuration
```

#### Token generation & Usage
1. (optional) Generate a project custom realm.
2. Generate a custom client for the application "booking".
   3. Client Type: OpenId Connect
   4. Client Authentication On
   5. Authentication Flow: Standard Flow, Service accounts rules
3. Generate the following POST request with this x-www-form-urlencoded body:
   - client_id: Custom client app name.
   - client_secret: You can retrieve it from Keycloak client credentials tab section.
   - grant_type: client_credentials.
