package com.aprendizaje.webflux.handler;

import com.aprendizaje.webflux.entidad.Producto;
import com.aprendizaje.webflux.servicio.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoHandler {

    private final ProductoServicio productoServicio;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Producto> productos = productoServicio.getAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productos, Producto.class);
    }

    public Mono<ServerResponse> getOne(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Producto> producto = productoServicio.getById(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(producto, Producto.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Producto> producto = request.bodyToMono(Producto.class);
        return producto.flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productoServicio.save(p), Producto.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Producto> producto = request.bodyToMono(Producto.class);
        return producto.flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productoServicio.update(id, p), Producto.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productoServicio.delete(id), Producto.class);
    }

}
