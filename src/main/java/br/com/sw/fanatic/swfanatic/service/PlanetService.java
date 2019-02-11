package br.com.sw.fanatic.swfanatic.service;

import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.model.Planets;
import br.com.sw.fanatic.swfanatic.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlanetService {

    private PlanetsService planetsService;
    private PlanetRepository planetRepository;

    public PlanetService(PlanetsService planetsService, PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
        this.planetsService = planetsService;
    }

    public Planet save(final Planet planet) {

        final List<Planets> planets = new ArrayList<>();

        this.planetsService.listPlanets().log().subscribe(planets::add);

        final Optional<Planet> planetOptional = planets.stream()
                                                       .flatMap(p -> p.getPlanets().stream())
                                                       .filter(p1 -> p1.getName().equals(planet.getName()))
                                                       .findFirst();

        planetOptional.ifPresent(planet1 -> planet.setTotalFilms(planet1.getTotalFilms()));

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
