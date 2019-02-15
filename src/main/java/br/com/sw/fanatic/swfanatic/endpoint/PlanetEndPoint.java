package br.com.sw.fanatic.swfanatic.endpoint;


import br.com.sw.fanatic.swfanatic.model.Planet;
import br.com.sw.fanatic.swfanatic.service.PlanetsServiceLocal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping
public class PlanetEndPoint {

   /* private PlanetsServiceLocal planetsServiceLocal;

    public PlanetEndPoint(PlanetsServiceLocal planetsServiceLocal){
        this.planetsServiceLocal = planetsServiceLocal;
    }


    //@GetMapping
    //@RequestMapping("/planet")
    public ResponseEntity<List<Planet>> findAll() {
        return ResponseEntity.ok().body(this.planetsServiceLocal.findAll());
    }

    //@GetMapping
    //@RequestMapping(value = "/planet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Planet> findById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(this.planetsServiceLocal.findById(id));
    }

    //@GetMapping
    //@RequestMapping(value = "/planet", method = RequestMethod.GET)
    public ResponseEntity<Planet> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(this.planetsServiceLocal.findByName(name));
    }

    //@DeleteMapping
    //@RequestMapping(value = "/planet", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlanet(@RequestBody Planet planet) {
        this.planetsServiceLocal.delete(planet);
        return ResponseEntity.ok().build();
    }

    //@PostMapping
    //@RequestMapping(value = "/planet", method = RequestMethod.POST)
    public ResponseEntity<Planet> save(@RequestBody Planet planet) {
        return ResponseEntity.ok().body(this.planetsServiceLocal.save(planet));
    }*/
}
