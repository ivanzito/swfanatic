package br.com.sw.fanatic.swfanatic.repository;

import br.com.sw.fanatic.swfanatic.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {

    Planet findByName(String name);
}
