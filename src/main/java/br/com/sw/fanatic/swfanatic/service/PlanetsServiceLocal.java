package br.com.sw.fanatic.swfanatic.service;

import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.model.Result;
import br.com.sw.fanatic.swfanatic.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlanetsServiceLocal {

    private PlanetsServiceSwApi planetsServiceSwApi;
    private PlanetRepository    planetRepository;

    public PlanetsServiceLocal(PlanetsServiceSwApi planetsServiceSwApi, PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
        this.planetsServiceSwApi = planetsServiceSwApi;
    }

    public Mono<Planet> save(final Planet planet) {

        final List<Result> planets = new ArrayList<>();

        this.planetsServiceSwApi.listPlanets().log().subscribe(planets::add);

        final Optional<Planet> planetOptional = planets.stream()
                                                       .flatMap(p -> p.getPlanets().stream())
                                                       .filter(p1 -> p1.getName().equals(planet.getName()))
                                                       .findFirst();

        planetOptional.ifPresent(planet1 -> planet.setTotalFilms(planet1.getTotalFilms()));

        return Mono.just(this.planetRepository.save(planet));
    }

    public Flux<Planet> findAll() {
        return Flux.fromIterable(this.planetRepository.findAll());
    }

    public Planet findById(int id) {
        return this.planetRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("nao foi possivel encontrar o registro"));
    }


    public Planet findByName(String name) {
        return this.planetRepository.findByName(name);
    }

    public void delete(Planet planet) {
        this.planetRepository.delete(planet);
    }
}
