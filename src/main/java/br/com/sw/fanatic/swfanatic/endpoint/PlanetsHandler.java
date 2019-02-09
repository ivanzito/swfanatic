package br.com.sw.fanatic.swfanatic.endpoint;

import br.com.sw.fanatic.swfanatic.model.Planets;
import br.com.sw.fanatic.swfanatic.service.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PlanetsHandler {

    private final PlanetsService planetsService;

    @Autowired
    public PlanetsHandler(PlanetsService planetsService) {
        this.planetsService = planetsService;
    }

    public Mono<ServerResponse> listAllFromSwApi(ServerRequest request) {
        final Flux<Planets> planetFlux = planetsService.listPlanets();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(planetFlux, Planets.class);
    }


    public Mono<ServerResponse> listByPage(ServerRequest request) {
        String page = request.pathVariable("page");
        final Flux<Planets> planetFlux = planetsService.listPlanetById(Integer.valueOf(page));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(planetFlux, Planets.class);

    }
}
