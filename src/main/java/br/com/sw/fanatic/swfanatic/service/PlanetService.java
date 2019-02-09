package br.com.sw.fanatic.swfanatic.service;

import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.model.Planets;
import br.com.sw.fanatic.swfanatic.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlanetService {

    private PlanetsService planetsService;
    private PlanetRepository planetRepository;

    public PlanetService(PlanetsService planetsService, PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
        this.planetsService = planetsService;
    }

    public Planet save(Planet planet) {

        //Não consegui transformar um Flux<Planets> em uma List<Planets>
        //por isso deixei comentado aqui o mapeamento até o totalFilms
        /*
        Stream<Planets> planetsStream = planetsService.listPlanets().toStream();
        List<Integer> totalFilms = planetsStream.map(Planets::getPlanet)
                .flatMap(Collection::stream)
                .filter(p1 -> planet.getName().equals(p1.getName()))
                .map(Planet::getTotalFilms)
                .collect(Collectors.toList());

        if(!totalFilms.isEmpty()) {
            planet.setTotalFilms(totalFilms.get(0));
        }
        */
        return this.planetRepository.save(planet);
    }

    public List<Planet> findAll() {
        return this.planetRepository.findAll();
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
