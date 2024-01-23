package com.aprendizaje.webflux.servicio;

import com.aprendizaje.webflux.entidad.Producto;
import com.aprendizaje.webflux.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public Flux<Producto> getAll() {
        return productoRepositorio.findAll();
    }

    public Mono<Producto> getById(int id){
        return productoRepositorio.findById(id);
    }

    public Mono<Producto> save(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public Mono<Producto> update(int id, Producto producto) {
        return productoRepositorio.save(new Producto(id, producto.getNombre(), producto.getPrecio()));
    }

    public Mono<Void> delete(int id) {
        return productoRepositorio.deleteById(id);
    }

}
