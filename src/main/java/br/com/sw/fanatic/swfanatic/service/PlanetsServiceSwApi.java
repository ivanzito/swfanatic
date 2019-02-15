package br.com.sw.fanatic.swfanatic.service;

import br.com.sw.fanatic.swfanatic.model.Result;
import br.com.sw.fanatic.swfanatic.service.external.PlanetsComponentSwApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PlanetsServiceSwApi {

    private final PlanetsComponentSwApi planetsComponentSwApi;

    public PlanetsServiceSwApi(PlanetsComponentSwApi planetsComponentSwApi) {
        this.planetsComponentSwApi = planetsComponentSwApi;
    }

    public Flux<Result> listPlanets() {
        return planetsComponentSwApi.getPlanets();
    }

    public Flux<Result> listPlanetById(int id) {
        return planetsComponentSwApi.listPlanetById(id);
    }
}
