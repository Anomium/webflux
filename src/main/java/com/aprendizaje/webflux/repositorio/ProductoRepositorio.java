package com.aprendizaje.webflux.repositorio;

import com.aprendizaje.webflux.entidad.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends ReactiveCrudRepository<Producto, Integer> {
}
