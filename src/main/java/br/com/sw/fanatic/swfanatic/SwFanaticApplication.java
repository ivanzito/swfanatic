package br.com.sw.fanatic.swfanatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwFanaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwFanaticApplication.class, args);

		//PlanetsComponentSwApi gwc = new PlanetsComponentSwApi("https://swapi.co/api/planets/");
		//System.out.println(gwc.getPlanet());
	}

}

