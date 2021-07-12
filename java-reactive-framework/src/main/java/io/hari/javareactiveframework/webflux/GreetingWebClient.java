package io.hari.javareactiveframework.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.concurrent.CompletableFuture;

public class GreetingWebClient { // step 3 : create a webclient : https://spring.io/guides/gs/reactive-rest-service/
    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get()
                                                .uri("/hello")
                                                .accept(MediaType.TEXT_PLAIN)
                                                .exchange();

    public String getResult() {
        Mono<ClientResponse> clientResponseMono = result;
        Mono<String> monoResult = clientResponseMono
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));

        Mono<CompletableFuture<Integer>> completableFutureMono = Mono.deferContextual(contextView -> {
            ContextView contextView1 = contextView;
            System.out.println("contextView1 = " + contextView1);
            return Mono.just(CompletableFuture.completedFuture(1223));
        });

        String result = monoResult.block();//https://www.baeldung.com/java-string-from-mono
        return ">> result = " + result;
    }
}
