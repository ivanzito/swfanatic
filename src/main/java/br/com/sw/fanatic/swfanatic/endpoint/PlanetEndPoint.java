package br.com.sw.fanatic.swfanatic.endpoint;


import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.service.PlanetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PlanetEndPoint {

    private PlanetService planetService;

    public PlanetEndPoint(PlanetService planetService){
        this.planetService = planetService;
    }


    @GetMapping
    @RequestMapping("/planet")
    public ResponseEntity<List<Planet>> findAll() {
        return ResponseEntity.ok().body(this.planetService.findAll());
    }

    @GetMapping
    @RequestMapping(value = "/planet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Planet> findById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(this.planetService.findById(id));
    }

    @GetMapping
    @RequestMapping(value = "/planet", method = RequestMethod.GET)
    public ResponseEntity<Planet> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(this.planetService.findByName(name));
    }

    @DeleteMapping
    @RequestMapping(value = "/planet", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlanet(@RequestBody Planet planet) {
        this.planetService.delete(planet);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequestMapping(value = "/planet", method = RequestMethod.POST)
    public ResponseEntity<Planet> save(@RequestBody Planet planet) {
        return ResponseEntity.ok().body(this.planetService.save(planet));
    }
}
