package br.com.sw.fanatic.swfanatic.endpoint;

import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.model.Result;
import br.com.sw.fanatic.swfanatic.model.request.SourceType;
import br.com.sw.fanatic.swfanatic.service.PlanetsServiceLocal;
import br.com.sw.fanatic.swfanatic.service.PlanetsServiceSwApi;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
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
        if(SourceType.valueOf(source) == SourceType.ONLINE) {

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

        final CompletableFuture<Planet> planetMono = request.bodyToMono(Planet.class).toFuture();

        Mono<Planet> planetSaved = this.planetsServiceLocal.save(planetMono.getNow(new Planet()));
        return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(planetSaved, Planet.class);
    }
}
