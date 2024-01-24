package com.aprendizaje.webflux.repositorio;

import com.aprendizaje.webflux.entidad.Producto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepositorio extends ReactiveCrudRepository<Producto, Integer> {

    Mono<Producto> findByNombre(String nombre);

    @Query("SELECT * FROM PRODUCTO WHERE ID <> :id AND NAME = :name")
    Mono<Producto> repeatedNombre(int id, String nombre);

}
