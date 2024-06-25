# HttpClientWrapper Library

## Overview

The HttpClientWrapper library provides a set of interfaces and classes to simplify HTTP communication in Java applications. It supports both GET and POST requests, and allows you to easily customize request parameters, headers, and bodies.

## Features

- **Flexible HTTP Requests**: Send GET and POST requests with custom paths, parameters, headers, and bodies.
- **Type-Safe Responses**: Cast responses to any type using the `CastResponse` interface.
- **Easy Integration**: Annotate your classes with `@GetExchange` and `@PostExchange` to integrate them with the library.

## Getting Started

### Prerequisites

- Java 8 or higher
- Spring Boot 2.0 or higher
- Maven

### Installation

1. Add the HttpClientWrapper library to your project's dependencies.
2. Import the necessary classes and interfaces from the `wrapper` and `util` packages.

`NB` Since the library hasn't yet been hosted on Maven central you would have to install it to your local maven repository before doing the above steps. Here is how to go about it
1. Clone the repository to your local machine
   ```bash

   git clone https://github.com/CpulsiveK/httpclientwrapper.git

   ```
2. Enter the command below in your terminal and run it to install the library on your local maven repository
   ```bash
   
   mvn clean install

   ```

### Usage
1. Add a config class to your project to configure the Client to your prefered choice.
2. Implement the `HttpClientWrapper` interface in your classes to enable HTTP communication.
3. Implement the `CastResponse` interface to cast HTTP responses to your desired types.

## Examples

Here are some examples of how to use the HttpClientWrapper library:
#### configuration
```java

    import com.fasterxml.jackson.databind.ObjectMapper;
    import java.net.URI;
    import java.net.URLDecoder;
    import java.nio.charset.StandardCharsets;
    import java.util.Objects;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.env.Environment;
    import org.springframework.web.reactive.function.client.ClientRequest;
    import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
    import org.springframework.web.reactive.function.client.WebClient;
    import org.springframework.web.reactive.function.client.support.WebClientAdapter;
    import org.springframework.web.service.invoker.HttpServiceProxyFactory;
    import reactor.core.publisher.Mono;
    import util.CastResponseImpl;
    import wrapper.HttpClientWrapper;
    
    @Configuration
    public class HttpClient {
      private final Environment env;
    
      @Autowired
      public HttpClient(Environment env) {
        this.env = env;
      }
    
      @Bean
      public WebClient client() {
        return WebClient.builder()
            .baseUrl(Objects.requireNonNull(env.getProperty("user.service.origin")))
            .filter(decodePath())
            .build();
      }
    
      @Bean
      HttpClientWrapper customerAuthService() {
        HttpServiceProxyFactory factory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client())).build();
        return factory.createClient(HttpClientWrapper.class);
      }
    
      @Bean
      public CastResponseImpl castResponseImpl() {
        return new CastResponseImpl<>(new ObjectMapper());
      }
    
      private ExchangeFilterFunction decodePath() {
        return ExchangeFilterFunction.ofRequestProcessor(
            clientRequest -> {
              String decodedUrl =
                  URLDecoder.decode(clientRequest.url().toString(), StandardCharsets.UTF_8);
              clientRequest = ClientRequest.from(clientRequest).url(URI.create(decodedUrl)).build();
              return Mono.just(clientRequest);
            });
      }
    }

```

#### implementation
```java

import util.CastResponse;
import wrapper.HttpClientWrapper;

@Service
public class WrapperExample {
  private final HttpClientWrapper<GetUserDto> httpClientWrapper;

  private final CastResponse<User> castResponse;

  @Autowired
  public UploadImpl(
      HttpClientWrapper<GetUserDto> httpClientWrapper,
      CastResponse<User> castResponse) {
    this.httpClientWrapper = httpClientWrapper;
    this.castResponse = castResponse;
  }

  private Long getUserId(HttpServletRequest httpServletRequest) {
    GetUserDto getUserDto =
        new GetUserDto(jwt.extractEmail(httpServletRequest.getHeader(AUTHORIZATION).substring(7)));

    headers.put(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));

    LinkedHashMap response =
        httpClientWrapper.getRequest("api/v1/user/profile", headers, getUserDto);

    User user = castResponse.castResponse(response, User.class);
    return user.id();
  }
}

```

