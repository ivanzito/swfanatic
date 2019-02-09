package br.com.sw.fanatic.swfanatic.service;

import br.com.sw.fanatic.swfanatic.model.Planets;
import br.com.sw.fanatic.swfanatic.service.external.PlanetsComponentSwApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PlanetsService {

    private final PlanetsComponentSwApi planetsComponentSwApi;

    public PlanetsService(PlanetsComponentSwApi planetsComponentSwApi) {
        this.planetsComponentSwApi = planetsComponentSwApi;
    }

    public Flux<Planets> listPlanets() {
        return planetsComponentSwApi.getPlanets();
    }

    public Flux<Planets> listPlanetById(int id) {
        return planetsComponentSwApi.listPlanetById(id);
    }
}
