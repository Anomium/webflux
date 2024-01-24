package com.aprendizaje.webflux.router;

import com.aprendizaje.webflux.handler.ProductoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductoRouter {

    private static final String PATH = "producto";

    @Bean
    RouterFunction<ServerResponse> router(ProductoHandler handler) {
        return RouterFunctions.route()
                .GET(PATH, handler::getAll)
                .GET(PATH + "/{id}", handler::getOne)
                .POST(PATH, handler::save)
                .PUT(PATH, handler::update)
                .DELETE(PATH + "/{id}", handler::delete)
                .build();
    }

}
