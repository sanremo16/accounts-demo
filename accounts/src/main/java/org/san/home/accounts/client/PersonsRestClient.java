package org.san.home.accounts.client;

import org.san.home.accounts.dto.PersonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

/**
 * Persons Service invocation
 */
@Service
public class PersonsRestClient {
    @Value("${app.persons.service.port}")
    private String personsPort;
    @Value("${app.persons.service.host}")
    private String personsHost;

    private WebClient webClient;

    @PostConstruct
    private void initialize() {
        webClient = WebClient.create("http://" + personsHost + ":" + personsPort);
    }

    public PersonDto searchFirst(final PersonDto personDto) {
        return webClient.get()
                .uri("/persons?" + personDto.toUrlParams())
                .retrieve()
                .bodyToFlux(PersonDto.class)
                .blockFirst();
    }
}
