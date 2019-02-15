package br.com.sw.fanatic.swfanatic.endpoint;

import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.model.Result;
import br.com.sw.fanatic.swfanatic.model.request.SourceType;
import br.com.sw.fanatic.swfanatic.service.PlanetsServiceLocal;
import br.com.sw.fanatic.swfanatic.service.PlanetsServiceSwApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PlanetsHandler {

    private final PlanetsServiceSwApi planetsServiceSwApi;
    private final PlanetsServiceLocal planetsServiceLocal;

    @Autowired
    public PlanetsHandler(PlanetsServiceSwApi planetsServiceSwApi, PlanetsServiceLocal planetsServiceLocal) {
        this.planetsServiceSwApi = planetsServiceSwApi;
        this.planetsServiceLocal = planetsServiceLocal;
    }

    public Mono<ServerResponse> listAll(ServerRequest request) {

        final String source = request.pathVariable("source").toUpperCase();
        if(SourceType.valueOf(source) == SourceType.REMOTE) {

            final Flux<Result> resultFlux = planetsServiceSwApi.listPlanets();
            return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(resultFlux, Result.class);
        } else {

            final Flux<Planet> planetFlux = planetsServiceLocal.findAll();
            return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(planetFlux, Planet.class);
        }



    }

    public Mono<ServerResponse> listByPage(ServerRequest request) {
        String page = request.pathVariable("page");
        final Flux<Result> planetFlux = planetsServiceSwApi.listPlanetById(Integer.valueOf(page));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(planetFlux, Result.class);

    }

    public Mono<ServerResponse> save(ServerRequest request) {
        //TODO
        return ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(planetsServiceLocal.save(null), Planet.class);

    }
}
