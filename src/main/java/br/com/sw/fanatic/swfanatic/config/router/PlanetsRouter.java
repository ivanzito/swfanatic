package br.com.sw.fanatic.swfanatic.config.router;

import br.com.sw.fanatic.swfanatic.endpoint.PlanetsHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlanetsRouter {

    @Bean
    @Qualifier(value = "planetsHandler")
    public RouterFunction<ServerResponse> route(PlanetsHandler PlanetsHandler) {

        return RouterFunctions
            .route(RequestPredicates.GET("/planets/{source}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), PlanetsHandler::listAll)
            .andRoute(RequestPredicates.GET("/planets/{page}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), PlanetsHandler::listByPage)
            .andRoute(RequestPredicates.POST("/planets/")
            .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), PlanetsHandler::save);

    }
}
