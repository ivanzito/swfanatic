package br.com.sw.fanatic.swfanatic.service.external;


import br.com.sw.fanatic.swfanatic.model.Planets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class PlanetsComponentSwApi {

    private WebClient webClient;

    public PlanetsComponentSwApi(@Value("${endpoint.swapi}") String uri) {
        webClient = WebClient.create(uri);
    }

    public Flux<Planets> getPlanets() {
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Planets.class);
    }

    public Flux<Planets> listPlanetById(final int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("page", String.valueOf(id)).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Planets.class);
    }
}
